package com.byvoid.wanangushi.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

    public static SweetAlertDialog showProgressDialog(Context context,String titleText){
        SweetAlertDialog progressDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText(titleText);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
}
