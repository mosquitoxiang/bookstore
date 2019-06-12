package com.example.administrator.modelmall.application;

import android.app.Application;

import com.example.administrator.modelmall.icon.FontModel;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.kongzue.dialog.v2.DialogSettings;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_IOS;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;

public class LatteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);

        Logger.addLogAdapter(new AndroidLogAdapter());// Logger
        DialogSettings.style = STYLE_IOS;  //对话框
        DialogSettings.tip_theme = THEME_LIGHT;
        DialogSettings.use_blur = true;                 //设置是否启用模糊
        LitePal.initialize(this);// 数据库
        // 字体图标库
        Iconify.with(new FontAwesomeModule())
                .with(new IoniconsModule())
                .with(new FontModel());

        MobSDK.init(this); // shareSDK
    }
}
