package com.cimc.datahub.mining.hk.his;

import org.apache.log4j.Logger;

import com.cimc.datahub.mining.text.HkToMainlandTextProcessor;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class HkToMainlandHisDataRequest {

	private static final Logger log = Logger.getLogger(HkToMainlandHisDataRequest.class);

	private static final String SH_URL = "http://www.hkexnews.hk/sdw/search/mutualmarket_c.aspx?t=sh";

	private static final String SZ_URL = "http://www.hkexnews.hk/sdw/search/mutualmarket_c.aspx?t=sz";

	public void getData(String url) throws Exception {

		HkToMainlandTextProcessor hkProcessor = new HkToMainlandTextProcessor();
		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setTimeout(35000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			final HtmlPage page = webClient.getPage(url);
			HtmlSelect yearSelect = (HtmlSelect) page.getElementById("ddlShareholdingYear");
			int yearSelectSize = yearSelect.getOptionSize();
			for (int i = 0; i < yearSelectSize; i++) {
				yearSelect.setSelectedIndex(i);

				HtmlOption yearOption = yearSelect.getOption(i);
				String yearVal = yearOption.asText();
				HtmlSelect monthSelect = (HtmlSelect) page.getElementById("ddlShareholdingMonth");
				int monthSelectSize = monthSelect.getOptionSize();
				for (int j = 0; j < monthSelectSize; j++) {
					monthSelect.setSelectedIndex(j);

					HtmlOption monthOption = monthSelect.getOption(j);
					String monthVal = monthOption.asText();

					HtmlSelect daySelect = (HtmlSelect) page.getElementById("ddlShareholdingDay");
					int daySelectSize = daySelect.getOptionSize();
					for (int k = 0; k < daySelectSize; k++) {
						daySelect.setSelectedIndex(k);

						HtmlOption dayOption = daySelect.getOption(k);
						String dayVal = dayOption.asText();

						log.info("Processing date " + yearVal + "/" + monthVal + "/" + dayVal);

						final HtmlImageInput button = (HtmlImageInput) page.getElementById("btnSearch");
						try {
							HtmlPage newPage = (HtmlPage) button.click();
							if (newPage != null) {
								DomElement el = newPage.getElementById("pnlResult");
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

		}

	}

	public static void main(String[] args) throws Exception {
		HkToMainlandHisDataRequest req = new HkToMainlandHisDataRequest();
		req.getData(SH_URL);
		req.getData(SZ_URL);

	}

}
