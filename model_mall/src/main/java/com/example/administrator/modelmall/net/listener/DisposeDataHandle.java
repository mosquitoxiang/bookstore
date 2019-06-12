package com.example.administrator.modelmall.net.listener;

public class DisposeDataHandle {
    public DisposeDataListener listener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.listener = listener;
    }


    public DisposeDataHandle(Class<?> mClass ,DisposeDataListener listener ) {
        this.listener = listener;
        this.mClass = mClass;

    }
}
