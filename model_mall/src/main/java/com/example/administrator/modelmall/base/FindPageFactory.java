package com.example.administrator.modelmall.base;

import android.content.Context;

import com.example.administrator.modelmall.base.impl.FindPageImpl;
import com.example.administrator.modelmall.base.impl.MainPageImpl;

public class FindPageFactory implements Provider {

    Context context;

    public FindPageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new FindPageImpl(context);
    }
}
