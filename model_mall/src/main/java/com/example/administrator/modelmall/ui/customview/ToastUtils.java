package com.example.administrator.modelmall.ui.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.modelmall.R;

public class ToastUtils {

    public static final int LENGTH_LONG = Toast.LENGTH_LONG;
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;


    public static void showToast(Context context, CharSequence text,int during) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
