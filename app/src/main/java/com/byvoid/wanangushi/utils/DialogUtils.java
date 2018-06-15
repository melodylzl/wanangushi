package com.byvoid.wanangushi.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @author melody
 * @date 2018/6/14
 */
public class DialogUtils {

    public static void show(Context context, String title, String content, String positiveText,
                            MaterialDialog.SingleButtonCallback positiveCallBack, String negativeText,
                            MaterialDialog.SingleButtonCallback negativeCallBack){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .onPositive(positiveCallBack)
                .negativeText(negativeText)
                .onNegative(negativeCallBack);
        MaterialDialog materialDialog = builder.build();
        materialDialog.show();
    }
}
