package com.luxsong.spider.robot;

import com.luxsong.spider.AbstractRobotPoemFactory;
import com.luxsong.spider.constatnt.Constants;
import com.luxsong.spider.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShicimingjuRobotPoemImpl extends AbstractRobotPoemFactory {
    private static Logger LOG = Logger.getLogger(ShicimingjuRobotPoemImpl.class);
    public static Map<String,String> form = new HashMap();
     @Override
    public String acquireRebootPoem(String titleParam) {
        String result = "";
        Double d;
         d.compareTo()
        form.put("kw",titleParam);
        form.put("zishu","7");
        form.put("position","0");
        form.put("_csrf","YBhgFuIjdiQng1YPdPBPG8uOIOtt44aC57-NOV_KWHYMUw5PjkJEFmGxZz4DxC5dm9EWqVuBs-uQ5-dPHYEuMg==");
        try {
            Document dom = HttpUtils.post("https://www.shicimingju.com/cangtoushi/index.html",form, Constants.convertCookie(Constants.Cookie));
            result = dom.select(".card").eq(2).html().replaceAll("[<br> ]","");
        }catch (IOException e){
            LOG.error(e.getMessage());
        }
        return result;
    }
}
