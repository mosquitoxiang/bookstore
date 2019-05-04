package com.example.administrator.modelmall.entity;

public class FindPageBean {

    private int headResId;

    private String userName;

    private String time;

    private int findBigResId;

    public int getFindBigResId() {
        return findBigResId;
    }

    public void setFindBigResId(int findBigResId) {
        this.findBigResId = findBigResId;
    }

    public int getHeadResId() {
        return headResId;
    }

    public void setHeadResId(int headResId) {
        this.headResId = headResId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public FindPageBean(int headResId, String userName, String time, int findBigResId) {
        this.headResId = headResId;
        this.userName = userName;
        this.time = time;
        this.findBigResId = findBigResId;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
