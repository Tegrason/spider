package com.luxsong.spider.robot;

import com.luxsong.spider.AbstractAcquireRebootReader;
import com.luxsong.spider.AbstractRobotPoemFactory;
import com.luxsong.spider.constatnt.Constants;
import com.luxsong.spider.utils.IntegerUtils;

import java.util.Objects;

public class RobotPoemFactory extends AbstractRobotPoemFactory {
    private static volatile RobotPoemFactory instance;
    public static RobotPoemFactory getInstance(){
        if(instance==null){
            synchronized(RobotPoemFactory.class){
                if(instance==null){
                    instance = new RobotPoemFactory();
                }
            }
        }
        return instance;
    }
    public String acquireRebootPoem(String titleParam){
        String keyWorlds = Objects.nonNull(titleParam)&&titleParam.trim().length()>0?titleParam:null;
        String key = keyWorlds.replaceAll(Constants.PLACEHOLDER,"");
            String result =  ((AbstractAcquireRebootReader)() -> {
               return new ShicimingjuRobotPoemImpl().acquireRebootPoem(IntegerUtils.charsetProcessor(key,11));
            }).acquireRebootPoemReader();
            if(result.indexOf("aside_title")>0){
                result = ((AbstractAcquireRebootReader)() -> {
                    return new Html6gameRobotPoemImpl().acquireRebootPoem(keyWorlds);
                }).acquireRebootPoemReader();
            }
            return result;
    }
}
