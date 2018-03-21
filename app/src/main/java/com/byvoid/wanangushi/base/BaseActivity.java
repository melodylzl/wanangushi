package com.byvoid.wanangushi.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.byvoid.wanangushi.utils.LogUtils;


/**
 * @author melody
 * @date 2018/1/24
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    public static void startToMe(Context context,Class cl){
        Intent intent = new Intent(context,cl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        handleIntentData(getIntent());
        findView();
        bindData();
        setListener();
    }

    protected void handleIntentData(Intent intent){

    }

    protected void findView(){

    }

    protected void bindData(){

    }

    protected void setListener(){

    }


    @Override
    public void onClick(View view) {
        try {
            handleOnClick(view);
        } catch (Throwable t) {
            LogUtils.d(getClass().getSimpleName(),t);
        }
    }

    protected void handleOnClick(View view){

    }

    protected Context getContext(){
        return this;
    }


}
