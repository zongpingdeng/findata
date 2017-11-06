package com.cimc.datahub.mining.hk.daily;

import org.apache.log4j.Logger;

import com.cimc.datahub.mining.text.HkToMainlandTextProcessor;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * run everyday
 * @author jeff
 *
 */
public class HkToMainlandDailyDataRequest {

	private static final String SH_URL = "http://www.hkexnews.hk/sdw/search/mutualmarket_c.aspx?t=sh";

	private static final String SZ_URL = "http://www.hkexnews.hk/sdw/search/mutualmarket_c.aspx?t=sz";

	private static final Logger log = Logger.getLogger(HkToMainlandDailyDataRequest.class);

	public static void main(String[] args) throws Exception {
		HkToMainlandDailyDataRequest req = new HkToMainlandDailyDataRequest();
		req.getData(SH_URL);
		req.getData(SZ_URL);
	}

	public HkToMainlandDailyDataRequest() {
	}

	public void getData(String url) throws Exception {

		HkToMainlandTextProcessor hkProcessor = new HkToMainlandTextProcessor();

		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setTimeout(35000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			final HtmlPage page = webClient.getPage(url);
			try {
				if (page != null) {
					DomElement el = page.getElementById("pnlResult");
					if (el != null) {
						final String pageText = el.asText();
						hkProcessor.processText(pageText);
					}
				}
			} catch (Exception e) {
				log.error("Error", e);
			}
			Thread.sleep(1000);
		}

	}

}
