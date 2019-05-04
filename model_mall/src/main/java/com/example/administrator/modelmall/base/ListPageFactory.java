package com.example.administrator.modelmall.base;

import android.content.Context;

import com.example.administrator.modelmall.base.impl.ListPageImpl;

public class ListPageFactory implements Provider{

    Context context;

    public ListPageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new ListPageImpl(context);
    }
}
