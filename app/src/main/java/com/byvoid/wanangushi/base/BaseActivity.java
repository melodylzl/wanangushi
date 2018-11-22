package com.byvoid.wanangushi.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


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
        ButterKnife.bind(this);
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
            t.printStackTrace();
        }
    }

    protected void handleOnClick(View view){

    }

    protected Context getContext(){
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
