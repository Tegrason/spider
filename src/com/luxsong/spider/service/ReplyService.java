package com.luxsong.spider.service;

import com.luxsong.spider.utils.MessageUtils;

public class ReplyService {
	public void autoMationReply(String url,String oid,String msg){
		
	}
	public void autoMationReply(String url,String oid){
		MessageUtils.reply(url, oid);
	}
}
