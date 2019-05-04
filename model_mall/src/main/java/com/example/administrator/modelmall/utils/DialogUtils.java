package com.example.administrator.modelmall.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

    public interface OnRunningListenter{
        void onRunning(ProgressDialog dialog);
    }

    public static void showProgressDialog(Context context, String message, OnRunningListenter listenter){
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
        listenter.onRunning(dialog);
    }
}
