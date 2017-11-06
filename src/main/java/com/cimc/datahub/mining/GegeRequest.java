package com.cimc.datahub.mining;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 该点位基于百度GPS
 * @author 00013894
 *
 */
public class GegeRequest {

	public static void main(String[] args) throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet("http://api.ebox.gegebox.com/v2/operator/terminal/near?longitude=113.923355&latitude=22.498608");
		httpGet.addHeader("Charset", "UTF-8");
		httpGet.addHeader("Cookie", "sid=58d86ed358fffbd3ef80ce7dd3a89b35c29d67aa85d4311e31ee8915885a8408919bd8408947bfb581ff52d5; Domain=.gegebox.com; expires=Wed, 26 Apr 2017 01:50:55 GMT; httponly; Path=/");
		httpGet.addHeader("User-Agent", "gegekdy/2.3.0 (Linux; Android 5.1; zh-cn; m2 note Build/LMY47D)");
		httpGet.addHeader("Connection", "Keep-Alive");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate");
		httpGet.addHeader("Host", "api.ebox.gegebox.com");

		HttpResponse response = closeableHttpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		System.out.println(IOUtils.toString(is));

	}

}
