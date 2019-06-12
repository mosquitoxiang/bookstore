package com.example.administrator.modelmall.events;

public class GoodInfoMsg {
    private Integer goodCount;

    public GoodInfoMsg(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }
}
