package com.luxsong.spider.constatnt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public class Constants {
	public static HashMap<String, String> cookiesMap;
	public static Map<String,String> data ;
	//url class
	public static final String contentUrl = "https://api.bilibili.com/x/v2/reply/add";
	public static final String header="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
	public static final String referer = "https://space.bilibili.com/";
	public static final String cookies = "_uuid=7A74A0D4-8E4B-4383-6459-96FE2E618B8D38668infoc; buvid3=DC5B99BA-AB3C-4CEA-8AD8-584F0884667D34778infoc; bfe_id=61a513175dc1ae8854a560f6b82b37af; fingerprint=ccda491f196691f51134fe8850278c23; buvid_fp=DC5B99BA-AB3C-4CEA-8AD8-584F0884667D34778infoc; buvid_fp_plain=DC5B99BA-AB3C-4CEA-8AD8-584F0884667D34778infoc; SESSDATA=393944b6%2C1639790951%2Caeb07%2A61; bili_jct=36f692b0fc1acdf961520dd8eda630f9; DedeUserID=13157259; DedeUserID__ckMd5=63bf1a3a6805c729; sid=b2it5h8s; PVID=1; CURRENT_FNVAL=80; blackside_state=1; rpdid=|(mmkRYkkY|0J'uYkRkJmJum";
	public static final String personHomeUrl = "https://space.bilibili.com/396470490/video";
	public static final String personVideoUrl = "https://api.bilibili.com/x/space/navnum?mid=396470490&jsonp=jsonp&callback=";
	public static final String statUrl = "https://api.bilibili.com/x/relation/stat?vmid=396470490&jsonp=jsonp";
	public static final String likeUrl = "https://api.bilibili.com/x/space/upstat?mid=396470490&jsonp=jsonp";
	public static final String pageChangeUrlFix = "https://api.bilibili.com/x/space/arc/search?mid=396470490&ps=";
	public static final String pageChangeUrlPage = "&tid=0&pn=";
	public static final String pageChangeUrlPuff = "&keyword=&order=pubdate&jsonp=jsonp";
	public static final String Cookie = "_csrf=9a02e00c27f28e955ac699cb8037ac46aa3d9ece1645838d973f921efe14071ea%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22lKnYla22F211w4aFP_6B6b5iwXjvBKvD%22%3B%7D;Hm_lvt_649f268280b553df1f778477ee743752=1624246446;Hm_lpvt_649f268280b553df1f778477ee743752=1624246467";


	public static final String PLACEHOLDER = "[`\\\\~!@#$%^&*()+=|{}':;',\\[\\].<>/?~??@#??%??&*????????+|{}????????????????????????0123456789?vlog????]";
	//number
	public static final int pageSize = 100;
	//array

	public static JSONArray videoUrlList = new JSONArray();
	static{
		cookiesMap = convertCookie(cookies);
	}
	public static HashMap<String, String> convertCookie(String cookie) {
		HashMap<String, String> cookiesMap = new HashMap<String, String>();
		String[] items = cookie.trim().split(";");
		for (String item:items) cookiesMap.put(item.split("=")[0], item.split("=")[1]);
		return cookiesMap;
	}
	public static Map<String,String> createMapInstance(){
		Map<String,String> data = new HashMap<String,String>();
		data.put("type", "1");
		data.put("plat", "1");
		data.put("ordering", "time");
		data.put("jsonp", "jsonp");
		data.put("csrf", "36f692b0fc1acdf961520dd8eda630f9");
		return data;
	}
}
