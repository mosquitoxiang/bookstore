package com.example.administrator.modelmall.entity;

import java.util.List;

public class CommentMessageEvent {

    private List<FindPageBean> findPageBeanList;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CommentMessageEvent(List<FindPageBean> findPageBeanList, int position) {
        this.findPageBeanList = findPageBeanList;
        this.position = position;
    }

    public List<FindPageBean> getFindPageBeanList() {
        return findPageBeanList;
    }

    public void setFindPageBeanList(List<FindPageBean> findPageBeanList) {
        this.findPageBeanList = findPageBeanList;
    }
}
