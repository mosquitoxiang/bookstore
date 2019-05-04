package com.example.administrator.modelmall.base;

import android.content.Context;

import com.example.administrator.modelmall.base.impl.MainPageImpl;

/**
 *
 */
public class MainPageFactory implements Provider {

    Context context;

    public MainPageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new MainPageImpl(context);
    }
}
