package com.cimc.datahub.mining;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cimc.datahub.mining.jooq.tables.records.SzMarketOverviewRecord;

public class SZMarketOverallRequest {
	public static final String URL_TEMPLATE = "http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=excel&CATALOGID=1803&txtQueryDate=2017-10-16&ENCODE=1&TABKEY=tab1";

	public static void main(String[] args) throws Exception {
		Document doc = Jsoup.parse(IOUtils.toString(new URL(URL_TEMPLATE), Charset.forName("GBK")));
		Elements trs = doc.getElementsByTag("tr");
		int size = trs.size();
		if (size == 16) {
			SzMarketOverviewRecord r = new SzMarketOverviewRecord();
			r.setSzczIndex(getDoubleValue(trs.get(1).text()));
			r.setSzzzIndex(getDoubleValue(trs.get(2).text()));
			r.setZxbIndex(getDoubleValue(trs.get(3).text()));
			r.setCybIndex(getDoubleValue(trs.get(4).text()));
			r.setListedCompanies(getIntegerValue(trs.get(5).text()));
			r.setListedStocks(getIntegerValue(trs.get(6).text()));

		}
		System.out.println(trs.size());
		for (Element link : trs) {
			String linkText = link.text();
			System.out.println(linkText.replaceAll(" ", "||"));
		}

	}

	public static Double getDoubleValue(String txt) {
		return Double.valueOf("1");
	}

	public static Integer getIntegerValue(String txt) {
		return Integer.valueOf("1");
	}

}
