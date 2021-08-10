package com.luxsong.spider.utils;

import java.io.IOException;
import java.util.Map;

import com.luxsong.spider.constatnt.Constants;
import org.apache.log4j.Logger;
public class MessageUtils {

	
	
	
	private static Logger LOG = Logger.getLogger(MessageUtils.class);
	public static void reply(String url,Map<String,String> data){
		try {
			LOG.info("url:{"+url+"},data:{"+data.toString()+"}");
			HttpUtils.post(url, data);
		} catch (IOException e) {
			LOG.error("url:{"+url+"},data:{"+data.toString()+"}");
			reply(url,data);
		}
	}
	public static void reply(String url,String oid){
		reply( url ,oid,"");
	}
	public static void reply(String url,String oid,String message){
		Map<String,String> data = Constants.createMapInstance();
		data.put("oid", oid);
		data.put("message", message);
		reply(url,data);
	}
}
