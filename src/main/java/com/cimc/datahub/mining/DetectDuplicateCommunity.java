package com.cimc.datahub.mining;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apdplat.word.analysis.SimpleTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

public class DetectDuplicateCommunity {

	public static final String[] REPLACEMENTS = { "·", "东区$", "南区$", "西区$", "北区$" };

	static class Info {

		private String commName;

		private String commNameNormalize;

		private double lng;

		private double lat;

		public String getCommName() {
			return commName;
		}

		public void setCommName(String commName) {
			this.commName = commName;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public String getCommNameNormalize() {
			return commNameNormalize;
		}

		public void setCommNameNormalize(String commNameNormalize) {
			this.commNameNormalize = commNameNormalize;
		}

	}

	private static final Logger log = LoggerFactory.getLogger(DetectDuplicateCommunity.class);

	public static void main(String[] args) throws Exception {

		TextSimilarity textSimilarity = new SimpleTextSimilarity();
		DataSource ds = new SimpleDriverDataSource(new Driver(), "jdbc:mysql://119.29.113.161:9976/cimc_basedata", "cimc_stat", "cimc_stat@1234");
		JdbcTemplate tpl = new JdbcTemplate();
		tpl.setDataSource(ds);
		List<Map<String, Object>> list = tpl.queryForList("select  DISTINCT t.district , t.city  from community t ");
		for (Map<String, Object> map : list) {
			log.info(map.toString());
			String city = (String) map.get("city");
			String district = (String) map.get("district");
			String sql = "select  t.comm_name , t.gdlng , t.gdlat  from community t  where t.city='%city%' and t.district='%district%'";
			sql = sql.replace("%city%", city);
			sql = sql.replace("%district%", district);
			log.info(sql);
			List<Map<String, Object>> l = tpl.queryForList(sql);
			int size = l.size();
			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					try {
						Map<String, Object> map1 = l.get(i);
						Map<String, Object> map2 = l.get(j);
						Double lng1 = Double.valueOf(map1.get("gdlng").toString());
						Double lat1 = Double.valueOf(map1.get("gdlng").toString());
						String commName1 = map1.get("comm_name").toString();

						Double lng2 = Double.valueOf(map2.get("gdlng").toString());
						Double lat2 = Double.valueOf(map2.get("gdlng").toString());
						String commName2 = map2.get("comm_name").toString();

						double dis = GPSMatcher.getDistance(lat1, lng1, lat2, lng2);
						if (dis < 100) {
							double similarScore = textSimilarity.similarScore(commName1, commName2);
							if (similarScore > 0.7) {
								log.info(commName1 + "||" + commName2 + "||" + dis + "||" + similarScore);
							}
						}

					} catch (Exception e) {
						// log.error("====" + e.getMessage());
					}
				}
			}

		}
	}

}
