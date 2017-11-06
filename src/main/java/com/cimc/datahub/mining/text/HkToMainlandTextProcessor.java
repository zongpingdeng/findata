package com.cimc.datahub.mining.text;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.cimc.datahub.mining.jooq.Tables;
import com.cimc.datahub.mining.jooq.tables.records.HkToMainlandRecord;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.spreada.utils.chinese.ZHConverter;

/**
 * 
 * @author jeff
 *
 */
public class HkToMainlandTextProcessor {

	private static final Logger log = Logger.getLogger(HkToMainlandTextProcessor.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private DSLContext dslContext;

	public HkToMainlandTextProcessor() {
		super();
		this.dslContext = getDSLContext();
	}

	public static void main(String[] args) throws Exception {
		String val = Files.toString(new File("D:\\workspace\\data-mining\\src\\test\\resources\\test_data.txt"),
				Charsets.UTF_8);
		System.out.println(val);
		new HkToMainlandTextProcessor().processText(val);
	}

	private DSLContext getDSLContext() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/basedata", "netment",
					"sf123456");
			return DSL.using(connection, SQLDialect.MYSQL);
		} catch (Exception e) {
			log.error("Failed to create connection!!", e);
		}
		return null;
	}

	public void processText(String pageText) throws ParseException {
		String[] strs = pageText.split("\n");
		Date date = null;
		for (int f = 0; f < strs.length; f++) {
			final String val = strs[f].replaceAll("\r", "");
			if (f == 0) {
				String[] fVals = val.split(":");
				date = sdf.parse(fVals[1]);
				System.out.println(date);
			}
			if (f > 3) {
				String[] tVals = val.split("\\t");
				if (tVals.length == 4) {
					try {
						String hk_code = tVals[0].trim();
						String hk_name = tVals[1].trim();
						Long amount = Long.valueOf(tVals[2].trim().replaceAll(",", ""));
						Double percentage = Double.valueOf(tVals[3].substring(0, tVals[3].indexOf("%")));
						HkToMainlandRecord r = new HkToMainlandRecord();
						r.setCode(hk_code);
						r.setName(ZHConverter.convert(hk_name, ZHConverter.SIMPLIFIED));
						r.setHkCode(hk_code);
						r.setHkName(hk_name);
						r.setAmount(amount);
						r.setPercentage(percentage);
						r.setEventDate(new java.sql.Date(date.getTime()));
						r.setCreatedDate(new Timestamp(System.currentTimeMillis()));
						r.setModifiedDate(new Timestamp(System.currentTimeMillis()));
						HkToMainlandRecord hkRecord = dslContext.newRecord(Tables.HK_TO_MAINLAND, r);
						hkRecord.insert();
					} catch (Exception e) {
						log.error("Error processing event ", e);
					}
				}

			}
		}
	}

}
