package com.cimc.datahub.mining;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class AMapRequest {

	public static void main(String[] args) throws Exception {

		/**
		 * HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); //
		 * HttpClient CloseableHttpClient closeableHttpClient =
		 * httpClientBuilder.build();
		 * 
		 * String url =
		 * "http://restapi.amap.com/v3/place/around?key=a965d6898ee7a3d3b9e214978a0eab47&location=116.481488,39.990464&types=12&offset=20&page=1&extensions=all";
		 * HttpGet httpGet = new HttpGet(url); HttpResponse response =
		 * closeableHttpClient.execute(httpGet); HttpEntity entity =
		 * response.getEntity(); InputStream is = entity.getContent();
		 * System.out.println(IOUtils.toString(is));
		 **/

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		String url = "http://restapi.amap.com/v3/geocode/regeo?key=a965d6898ee7a3d3b9e214978a0eab47&location=103.812841,30.662014&poitype=12&radius=100&extensions=all&roadlevel=0";
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = closeableHttpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		Object obj = (JSONObject) JSON.parse(IOUtils.toString(is));
		String province = JSONPath.eval(obj, "$.regeocode.addressComponent.province").toString();
		String city = JSONPath.eval(obj, "$.regeocode.addressComponent.city").toString();
		String district = JSONPath.eval(obj, "$.regeocode.addressComponent.district").toString();
		String township = JSONPath.eval(obj, "$.regeocode.addressComponent.township").toString();
		String val = province + city + district + township;
		System.out.println(val);

	}

}
