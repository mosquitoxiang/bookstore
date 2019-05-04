package com.example.administrator.modelmall.net.listener;

/**
 *
 */
public interface DisposeDataListener {
    /**
     * 请求成功回调事件处理
     */
    void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(Object reasonObj);
}
