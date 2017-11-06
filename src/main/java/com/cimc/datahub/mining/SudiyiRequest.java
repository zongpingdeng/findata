package com.cimc.datahub.mining;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *  该点位基于高德GPS
 * @author 00013894
 *
 */
public class SudiyiRequest {

	public static void main(String[] args) throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet("http://couriers.sudiyi.cn/api/v1/devices/surrounding?page=1&lat=22.493&lng=113.917");
		httpGet.addHeader("If-None-Match", "NONE");
		httpGet.addHeader("TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aWNrZXQiOiJlYjc2ZWNiYjQ3NjVjNGQ5ZTkyZDUwYTBiNzJkZWE2MzNlODY2Y2MzIiwidXNlcl9pZCI6ODU4NjExfQ==.x89fJNFfLVL+fm801J8+/Ro4cyQdIIEs55WJzeKuGnc=");
		httpGet.addHeader("Host", "couriers.sudiyi.cn");
		httpGet.addHeader("Connection", "Keep-Alive");
		httpGet.addHeader("Accept-Encoding", "gzip");
		httpGet.addHeader("User-Agent", "okhttp/3.3.1");


		HttpResponse response = closeableHttpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream is =  entity.getContent();
		System.out.println(IOUtils.toString(is));
		

	}

}
