package com.luxsong.spider.service;

import java.io.IOException;

import com.luxsong.spider.constatnt.Constants;
import com.luxsong.spider.robot.RobotPoemFactory;
import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luxsong.spider.utils.HttpUtils;
import com.luxsong.spider.utils.IntegerUtils;
import com.luxsong.spider.utils.MessageUtils;
import com.luxsong.spider.utils.PropertiesUtil;

public class SpiderService {
	private static Logger LOG = Logger.getLogger(SpiderService.class);
	public int paresHeadCount(String url,String tag){
		Response res = null;
		try {
			res = HttpUtils.execute(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return paresHeadCount(url,tag);
		}
		return JSONObject.parseObject(res.body()).getJSONObject("data").getIntValue(tag);
	}
	public JSONArray paresVideoList(String url){
		Response res = null;
		try {
			res = HttpUtils.execute(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return paresVideoList(url);
		}
		return JSONObject.parseObject(res.body()).getJSONObject("data").getJSONObject("list").getJSONArray("vlist");
	}
	
	public void paresNewVideo(){
		long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		int videoCount = paresHeadCount(Constants.personVideoUrl,"video");
		int oldVideoCount = IntegerUtils.stringToInt(PropertiesUtil.getProperty("videoCount"));
		if(videoCount<=oldVideoCount)
			return ;
		LOG.info("�ϴ���Ƶ����:"+oldVideoCount);
		LOG.info("��ǰ��Ƶ����:"+videoCount);
		PropertiesUtil.setProper("videoCount", String.valueOf(videoCount));
		videoCount = videoCount-oldVideoCount;
		int pageSize = videoCount;
		int avaliablePage = 1;	
		if(videoCount>=Constants.pageSize){
			avaliablePage = videoCount/Constants.pageSize;
			pageSize = Constants.pageSize;
		}
		
		for(int i=0;i<avaliablePage;i++){
			sb.delete(0, sb.length());
			sb.append(Constants.pageChangeUrlFix)
			.append(pageSize)
			.append(Constants.pageChangeUrlPage)
			.append(i+1)
			.append(Constants.pageChangeUrlPuff);
			Constants.videoUrlList.addAll(paresVideoList(sb.toString()));
		}
		System.out.println("����ҳ�������ܹ���ʱ(ms)��"+String.valueOf(System.currentTimeMillis()-startTime));
	}
	public void replyContent(){
		int stat = 0,likes = 0;
		int subStat = IntegerUtils.stringToInt(PropertiesUtil.getProperty("statCount"));
		int subLikes = IntegerUtils.stringToInt(PropertiesUtil.getProperty("likesCount"));
		StringBuffer sb = new StringBuffer();
		for(int i = Constants.videoUrlList.size() - 1; i >= 0; i--){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stat = paresHeadCount(Constants.statUrl, "follower");
			likes = paresHeadCount(Constants.likeUrl, "likes");
			JSONObject video = (JSONObject) Constants.videoUrlList.get(i);
			sb.delete(0, sb.length());
			sb.append("����ű���")
			.append("\n")
			.append("��Ƶ���⣺��"+video.getString("title"))
			.append("��")
			.append("\n")
			.append("�ű�������ʫһ��")
			.append("\n")
			.append(RobotPoemFactory.getInstance().acquireRebootPoem(video.getString("title")))
			.append("\n")
			.append("ʱ����"+video.getString("length"))
			.append("\n")
			.append("��ǰ��˿��"+stat)
			.append("\n")
			.append("��ǰ���ޣ�"+likes)
			.append("\n")
			.append("�����ϸ���Ƶ��˿���仯��"+(stat-subStat))
			.append("\n")
			.append("�����ϸ���Ƶ�������仯��"+(likes-subLikes));
			subStat = stat;
			subLikes = likes;
			MessageUtils.reply(Constants.contentUrl, video.getString("aid"), sb.toString());
			LOG.info("��ǰaid:"+video.getString("aid"));
			LOG.info("��ǰִ����:"+i);
		}
		PropertiesUtil.setProper("likesCount", String.valueOf(subLikes));
		PropertiesUtil.setProper("statCount", String.valueOf(subStat));
	}
}
