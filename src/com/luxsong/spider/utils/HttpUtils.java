package com.luxsong.spider.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.luxsong.spider.constatnt.Constants;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpUtils {

	public static Document get(String url){
		try {
			return Jsoup.connect(url)
					.timeout(5000)
					.header("user-agent",Constants.header)
					.header("referer", Constants.referer)
					.ignoreContentType(true)
					.cookies(Constants.cookiesMap).get();
		} catch (IOException e) {
			System.out.println("url resource pares filed!");
			e.printStackTrace();
		}
		return null;
	}
	public static Document post(String url,Map<String,String> data,Map<String,String> cookiesMap) throws IOException{
		try {
			return Jsoup.connect(url)
					.timeout(5000)
					.ignoreContentType(true)
					.cookies(cookiesMap)
					.header("user-agent",Constants.header)
					.header("referer", Constants.referer)
					.data(data)
					.post();
		} catch (IOException e) {
			System.out.println("url resource pares filed!");
			throw e;
		}
	}
	public static Document post(String url,Map<String,String> data) throws IOException{
		return post(url,data,Constants.cookiesMap);
	}
	public static Response execute(String url,Map<String,String> data) throws IOException{
		Response res = null;
		 try {
			 res = Jsoup.connect(url)
			            .header("Accept", "*/*")
			            .header("Accept-Encoding", "gzip, deflate")
			            .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
			            .header("Content-Type", "application/json;charset=UTF-8")
			            .header("User-Agent",Constants.header)
			            .header("referer", Constants.referer)
			            .cookies(Constants.cookiesMap)
					 	.data(data)
			            .timeout(10000).ignoreContentType(true).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		 return res;
	}
	public static Response execute(String url) throws IOException{
		return execute(url,new HashMap<>());
	}
}
