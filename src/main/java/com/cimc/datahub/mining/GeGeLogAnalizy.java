package com.cimc.datahub.mining;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
//import com.cimc.datahub.mining.jooq.tables.records.AllGgPickupMachinesRecord;
import com.google.common.io.Files;

public class GeGeLogAnalizy {

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("C:\\log\\log\\2017-03-27.log");
		BufferedReader reader = Files.newReader(file, Charset.forName("Utf-8"));
		while (true) {
			String val = reader.readLine();
			if (val != null && val.indexOf("GegeboxApplication") != -1) {
				String[] timeStrs = val.split("\\[");
				String[] vArrays = val.split("json = ");
				if (vArrays.length == 2) {
					JSONObject obj = JSON.parseObject(vArrays[1]);
					JSONArray array = obj.getJSONArray("data");
					int size = array.size();
					int i = 0;
					while (i < size) {
						Object obj1 = array.get(i);
						System.out.println(obj1);
						String terminal_addr = getValue(obj1, "$.terminal_addr");
						String lng = getValue(obj1, "$.geo.geo[0]");
						String lat = getValue(obj1, "$.geo.geo[1]");
						String rname = getValue(obj1, "$.geo.rname");
						String updated_at = getValue(obj1, "$.geo.updated_at");
						String terminal_code = getValue(obj1, "$.geo.terminal_code");
						String pname = getValue(obj1, "$.geo.pname");
						String cname = getValue(obj1, "$.geo.cname");
						String created_at = getValue(obj1, "$.geo.created_at");
						String _id = getValue(obj1, "$.geo._id");
						String terminal_name = getValue(obj1, "$.terminal_name");
						String smallbox_count = getValue(obj1, "$.box.small.box_count");
						String middlebox_count = getValue(obj1, "$.box.middle.box_count");
						String bigbox_count = getValue(obj1, "$.box.big.box_count");
						String microbox_count = getValue(obj1, "$.box.micro.box_count");
						/**
						AllGgPickupMachinesRecord r = new AllGgPickupMachinesRecord();

						r.setBdlng(getDoubleValue(lng));
						r.setBdlat(getDoubleValue(lat));
						r.setDistrict(rname);
						r.setCity(cname);
						r.setProvince(pname);
						r.setBigBoxCount(getIntegerValue(bigbox_count));
						r.setTerminalAddr(terminal_addr);
						r.setTerminalName(terminal_name);
						r.setSmallBoxCount(getIntegerValue(smallbox_count));
						r.setMicroBoxCount(getIntegerValue(microbox_count));
						r.setMiddleBoxCount(getIntegerValue(middlebox_count));
						r.setGeoId(_id);
						r.setGeoCreatedAt(getTimestamp(created_at));
						r.setGeoUpdatedAt(getTimestamp(updated_at));
						r.setStatDate(getTimestamp(timeStrs[0]));
						r.setTerminalCode(terminal_code);
						r.setCreateDate(new Timestamp(System.currentTimeMillis()));
						r.setUpdateDate(new Timestamp(System.currentTimeMillis()));
						QueryAddrByGPSWithAMap.getDSLContext().executeInsert(r);
						**/
						i++;
					}
				}
			} else {
				break;
			}
		}

	}

	public static Timestamp getTimestamp(String v) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = sdf.parse(v);
			return new Timestamp(date.getTime());
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getIntegerValue(String val) {
		try {
			if (val != null) {
				return Integer.valueOf(val);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public static Double getDoubleValue(String val) {
		try {
			if (val != null) {
				return Double.valueOf(val);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static String getValue(Object obj, String val) {
		Object rs = JSONPath.eval(obj, val);
		if (rs != null)
			return rs.toString();
		return null;

	}

}
