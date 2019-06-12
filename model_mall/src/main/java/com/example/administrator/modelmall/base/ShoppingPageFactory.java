package com.example.administrator.modelmall.base;

import android.content.Context;

import com.example.administrator.modelmall.base.impl.MainPageImpl;
import com.example.administrator.modelmall.base.impl.ShoppingPageImpl;

public class ShoppingPageFactory implements Provider {

    Context context;

    public ShoppingPageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new ShoppingPageImpl(context);
    }
}
