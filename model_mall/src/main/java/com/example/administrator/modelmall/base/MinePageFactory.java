package com.example.administrator.modelmall.base;

import android.content.Context;

import com.example.administrator.modelmall.base.impl.MainPageImpl;
import com.example.administrator.modelmall.base.impl.MinePageImpl;

/**
 *
 */
public class MinePageFactory implements Provider {

    Context context;

    public MinePageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new MinePageImpl(context);
    }
}
