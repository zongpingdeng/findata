package com.cimc.datahub.mining;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

public class EasyGoRequest {

	public static void main(String[] args) throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httppost = new HttpPost("http://c.easygo.qq.com/api/egc/linedata");
		httppost.addHeader("Host", "c.easygo.qq.com");
		httppost.addHeader("Accept", "application/json");
		httppost.addHeader("Connection", "keep-alive");
		httppost.addHeader("X-Requested-With", "XMLHttpRequest");
		httppost.addHeader("Accept-Language:", "zh-cn");
		httppost.addHeader("Accept-Encoding", "gzip, deflate");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.addHeader("Origin", "http://c.easygo.qq.com");
		httppost.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Mobile/14E304 MicroMessenger/6.5.7 NetType/WIFI Language/zh_CN");
		httppost.addHeader("Connection", "keep-alive");
		httppost.addHeader("Referer", "http://c.easygo.qq.com/eg_toc/map.html?code=011135U12dnznZ0BjpX12A5YT12135Uf&state=1");
		httppost.addHeader("Cookie","php_session=eyJpdiI6IkF5VTRmcDBXMVcyZUJFTDFwOUgyVHc9PSIsInZhbHVlIjoiaTJPQnNCSGw3SjczTStGZXBrZHE1bFVzWW15M0E2aGJ1TnJ3K0kxcnpvenNFaUs2YytHNHpEXC9ES093dHo5clJwOWVvXC9EUCs1aFF3alJyVDBsNDVOQT09IiwibWFjIjoiZjk5MWExN2E3M2U3NzgwODAzYmY0OTM4MzIzYmVlYzA5NzNlMWM1ZTIzMjU4MzMzZmNiMTcyYzFjNmE3ZmY0NiJ9; pgv_pvid=9575950850");

		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("lat", "22.491057777365242"));
		nvps.add(new BasicNameValuePair("lng", "113.91897439956665"));
		nvps.add(new BasicNameValuePair("_token", ""));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse response = closeableHttpClient.execute(httppost);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		System.out.println(IOUtils.toString(is));

	}

}
