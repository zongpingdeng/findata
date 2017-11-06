package com.cimc.datahub.mining;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * 该点位基于高德GPS
 * @author 00013894
 *
 */
public class FCboxRequest {

	public static void main(String[] args) throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httppost = new HttpPost("https://edms.fcbox.com/app/v2/availableBox/getNearbyAvailableBoxs");
		httppost.addHeader("FC_AUTHENTICATED_TOKEN", "APP:$2A$05$NGRW2MB5ZJXM5WKT5LT8W.G3JCUP425FJ1V07JUU1VPOPUSK9.BEO");
		httppost.addHeader("FC_DEVICE_TOKEN", "{platform=Android, os=5.1, resolution=1080*1920, deviceId=867570027775703, mac=68:3e:34:38:04:e8, ip=10.45.129.68, versionName=2.6.0, versionCode=27, channel=wandoujia}");
		httppost.addHeader("FC_USER_FLAG", "11267333");
		httppost.addHeader("Host", "edms.fcbox.com");
		httppost.addHeader("Connection:", "Keep-Alive");
		httppost.addHeader("Accept-Encoding", "gzip");
		httppost.addHeader("Cookie", "JSESSIONID=17F8D0CAD78E8B5C77E1C0D471A4CCC6; SVR_SSL=H; SVR_HTTP=C");
		httppost.addHeader("User-Agent", "okhttp/3.4.1");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");

		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("Longitude", "113.91137"));
		nvps.add(new BasicNameValuePair("startLatitude", ""));
		nvps.add(new BasicNameValuePair("endLongitude", ""));
		nvps.add(new BasicNameValuePair("startLongitude", ""));
		nvps.add(new BasicNameValuePair("Latitude", "22.537797"));
		nvps.add(new BasicNameValuePair("addressLike", ""));
		nvps.add(new BasicNameValuePair("endLatitude", ""));
		nvps.add(new BasicNameValuePair("pageNo", "1"));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse response = closeableHttpClient.execute(httppost);
		HttpEntity entity = response.getEntity();
		InputStream is =  entity.getContent();
		System.out.println(IOUtils.toString(is));
		

	}

}
