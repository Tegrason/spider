package com.luxsong.spider.robot;

import com.alibaba.fastjson.JSONObject;
import com.luxsong.spider.AbstractRobotPoemFactory;
import com.luxsong.spider.service.SpiderService;
import com.luxsong.spider.utils.HttpUtils;
import com.luxsong.spider.utils.Md5Utils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JiugeRobotPoemImpl extends AbstractRobotPoemFactory {
    private static Logger LOG = Logger.getLogger(JiugeRobotPoemImpl.class);
    public static Map<String,String> form = new HashMap();
    public static String getKeyWorld(String titleParam) throws IOException, InterruptedException {
        form.put("level","1");
        form.put("genre","2");
        form.put("keywords",titleParam);
        Document dom = HttpUtils.post("http://jiuge.thunlp.org/getKeyword",form);
        JSONObject jsonObject  = JSONObject.parseObject(dom.body().text());
        String code = jsonObject.getString("code");
        String data = jsonObject.getString("data");
        if(!Objects.deepEquals(code,"0")){
            LOG.error("request url failed");
            return getKeyWorld(titleParam);
        }
        return sendPoem(Md5Utils.md5("a6aaf9c3bd0f1bdsaddsds"),data);
    }
    public static String sendPoem(String userId,String dataJson) throws IOException, InterruptedException {
        form.clear();
        form.put("style","0");
        form.put("genre","2");
        form.put("yan","7");
        form.put("user_id",userId);
        form.put("keywords",dataJson);
        JSONObject jsonObject  = JSONObject.parseObject(HttpUtils.post("http://jiuge.thunlp.org/sendPoem",form).body().text());
        String code = jsonObject.getString("code");
        if(!Objects.deepEquals(code,"0")){
            return sendPoem(userId,dataJson);
        }
        return getPoem(userId,dataJson);
    }
    public static String getPoem(String userId,String dataJson) throws IOException, InterruptedException {
        form.clear();
        form.put("style","0");
        form.put("genre","2");
        form.put("yan","7");
        form.put("user_id",userId);
        form.put("keywords",dataJson);
        JSONObject jsonObject  = JSONObject.parseObject(HttpUtils.post("http://jiuge.thunlp.org/getPoem",form).body().text());
        String code = jsonObject.getString("code");
        if(!Objects.deepEquals(code,"0")){
            Thread.sleep(1000);
            return getPoem(userId,dataJson);
        }
        String poem = jsonObject.getJSONObject("data").getString("poem");
        poem = poem.replaceAll("[\\[\\]\"]","").replace(",","\n");
        return poem;
    }
    @Override
    public String acquireRebootPoem(String titleParam) {
        try{
            return getKeyWorld(titleParam);
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
        return null;
    }
}
