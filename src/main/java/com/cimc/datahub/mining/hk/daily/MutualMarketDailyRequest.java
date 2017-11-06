package com.cimc.datahub.mining.hk.daily;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.cimc.datahub.mining.jooq.tables.records.MutualMarketOverviewDailyRecord;
import com.cimc.datahub.mining.jooq.tables.records.MutualMarketTop10DailyRecord;
import com.cimc.datahub.mining.service.DatabaseService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MutualMarketDailyRequest {

	private static final Logger log = Logger.getLogger(MutualMarketDailyRequest.class);

	/**
	 * HK TO SSE
	 */
	private static final String SSE_Northbound_market = "SSE Northbound";

	/**
	 * SSE TO HK
	 */
	private static final String SSE_Southbound_market = "SSE Southbound";

	/**
	 * HK TO SZSE
	 */
	private static final String SZSE_Northbound_market = "SZSE Northbound";

	/**
	 * SZSE TO HK
	 */
	private static final String SZSE_Southbound_market = "SZSE Southbound";

	private static final String URL_TEMPLATE = "http://www.hkex.com.hk/chi/csm/DailyStat/data_tab_daily_%varDateStr%c.js?1507618370539";

	public void fetchData(int backDayCount) throws Exception {

		int i = 0;
		while (i < backDayCount) {
			LocalDate localDate = LocalDate.now();
			LocalDate yDate = localDate.plusDays(-i);
			String str = yDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			String url = URL_TEMPLATE.replaceAll("%varDateStr%", str);
			fetchDataImpl(url);
			i++;
		}

	}

	private void fetchDataImpl(String url) throws Exception {

		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			log.info("processing url [" + url + "]");
			final HtmlPage page = webClient.getPage(url);
			String pageContent = page.asText();
			String content = pageContent.substring(pageContent.indexOf("=") + 1);
			JSONArray array = (JSONArray) JSON.parse(content);
			JSONObject ssen = array.getJSONObject(0);
			processTradeEvent(ssen);
			JSONObject sses = array.getJSONObject(1);
			processTradeEvent(sses);
			JSONObject szsen = array.getJSONObject(2);
			processTradeEvent(szsen);
			JSONObject szses = array.getJSONObject(3);
			processTradeEvent(szses);
		}

	}

	public static void main(String[] args) throws Exception {
		MutualMarketDailyRequest req = new MutualMarketDailyRequest();
		req.fetchData(100);
	}

	public static String getValueByIndex(JSONArray array, Integer index) {
		JSONObject obj = array.getJSONObject(index);
		JSONArray gArray = obj.getJSONArray("td");
		JSONArray fArray = gArray.getJSONArray(0);
		String val = fArray.getString(0);
		return val;
	}

	public static Double toDouble(String val) {
		return Double.valueOf(val.trim().replaceAll(",", ""));
	}

	public static Long toLong(String val) {
		return Long.valueOf(val.trim().replaceAll(",", ""));
	}

	public static String getflowType(JSONObject obj) {
		String val = obj.getString("market");
		if (SSE_Northbound_market.equals(val))
			return "hktosh";
		else if (SSE_Southbound_market.equals(val))
			return "shtohk";
		else if (SZSE_Northbound_market.equals(val))
			return "hktosz";
		else if (SZSE_Southbound_market.equals(val))
			return "sztohk";
		else
			throw new RuntimeException("Should not reach here");
	}

	public static void processTradeEvent(JSONObject jObj) {

		String dateStr = jObj.getString("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			log.error(e);
		}
		String flowType = getflowType(jObj);

		try {
			JSONArray overviewArray = (JSONArray) JSONPath.eval(jObj, "$.content[0].table.tr");
			MutualMarketOverviewDailyRecord r1 = new MutualMarketOverviewDailyRecord();
			String oTotalAmount = getValueByIndex(overviewArray, 0);
			r1.setTotalAmount(toDouble(oTotalAmount));
			String oBuyAmount = getValueByIndex(overviewArray, 1);
			r1.setBuyAmount(toDouble(oBuyAmount));
			String oSellAmount = getValueByIndex(overviewArray, 2);
			r1.setSellAmount(toDouble(oSellAmount));
			String oTotalCount = getValueByIndex(overviewArray, 3);
			r1.setTotalCount(toLong(oTotalCount));
			String oBuyCount = getValueByIndex(overviewArray, 4);
			r1.setBuyCount(toLong(oBuyCount));
			String oSellCount = getValueByIndex(overviewArray, 5);
			r1.setSellCount(toLong(oSellCount));

			if (overviewArray.size() > 6) {
				String dqb = getValueByIndex(overviewArray, 6);
				r1.setDqb(toDouble(dqb));
				String dqbPercentage = getValueByIndex(overviewArray, 7);
				r1.setDqbPercentage(toDouble(dqbPercentage));
			} else {
				r1.setDqb(Double.valueOf(-1));
				r1.setDqbPercentage(Double.valueOf(-1));
			}

			r1.setEventDate(new java.sql.Date(date.getTime()));
			r1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			r1.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			r1.setFlowType(flowType);
			DatabaseService.getInstance().saveMutualMarketOverviewDailyRecord(r1);
		} catch (Exception e) {
			log.error("Error processing overview event", e);
		}

		JSONArray rArray = (JSONArray) JSONPath.eval(jObj, "$.content[1].table.tr");
		for (int i = 0; i < rArray.size(); i++) {
			try {
				JSONObject obj = rArray.getJSONObject(i);
				JSONArray gArray = obj.getJSONArray("td");
				JSONArray fArray = gArray.getJSONArray(0);
				MutualMarketTop10DailyRecord r2 = new MutualMarketTop10DailyRecord();
				String rank = fArray.getString(0);
				r2.setRank(Integer.valueOf(rank.trim()));
				String code = fArray.getString(1);
				r2.setStockCode(code.trim());
				String name = fArray.getString(2);
				r2.setStockName(name.trim());
				String buyAmount = fArray.getString(3);
				r2.setBuyAmount(Long.valueOf(buyAmount.trim().replaceAll(",", "")));
				String sellAmount = fArray.getString(4);
				r2.setSellAmount(Long.valueOf(sellAmount.replaceAll(",", "")));
				String totalAmount = fArray.getString(5);
				r2.setTotalAmount(Long.valueOf(totalAmount.trim().replaceAll(",", "")));
				r2.setEventDate(new java.sql.Date(date.getTime()));
				r2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				r2.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				r2.setFlowType(flowType);
				DatabaseService.getInstance().saveMutualMarketTop10DailyRecord(r2);
			} catch (Exception e) {
				log.error("Error processing top10 event",e);
			}

		}
	}

}
