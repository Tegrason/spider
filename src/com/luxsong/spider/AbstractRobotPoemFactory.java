package com.luxsong.spider;

public abstract class AbstractRobotPoemFactory implements PoemFactory {
    public abstract String acquireRebootPoem(String titleParam);
    @Override
    public String acquireCreatePoem(String placeHold) {
        return acquireRebootPoem(placeHold);
    }
}
