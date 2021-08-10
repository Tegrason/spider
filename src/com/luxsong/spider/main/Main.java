package com.luxsong.spider.main;

import com.luxsong.spider.service.SpiderService;

public class Main {
	public static void main(String[] args) {
		SpiderService spiderService = new SpiderService();
		spiderService.paresNewVideo();
		spiderService.replyContent();
	}
}
