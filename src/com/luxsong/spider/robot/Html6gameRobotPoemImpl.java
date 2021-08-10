package com.luxsong.spider.robot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luxsong.spider.AbstractRobotPoemFactory;
import com.luxsong.spider.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Html6gameRobotPoemImpl extends AbstractRobotPoemFactory {
    private static Logger LOG = Logger.getLogger(ShicimingjuRobotPoemImpl.class);
    public static Map<String,String> form = new HashMap();
    private static final int SPLIT_CAPACITY = 7;
    @Override
    public String acquireRebootPoem(String titleParam) {
        String result = "";
        form.put("words",titleParam);
        form.put("length","7");
        try {
           JSONObject jsonObject = JSONObject.parseObject(HttpUtils.post("https://www.html6game.com/ajax.php",form).body().text());
           JSONArray array = jsonObject.getJSONArray("lists");
           if(StringUtil.isBlank(result = array.get(0).toString().replace("</b>",""))){
               return acquireRebootPoem(titleParam);
            }
            char[] charset = result.toCharArray();
            int i = 1;
            StringBuffer sb = new StringBuffer("\n");
            while (i<=charset.length){
                sb.append(charset[i-1]);
                if(i%SPLIT_CAPACITY==0){
                    sb.append("\n");
                }
                i++;
            }
            result = sb.toString();
        }catch (IOException e){
            LOG.error(e.getMessage());
            return acquireRebootPoem(titleParam);
        }
        return result;
    }
}
