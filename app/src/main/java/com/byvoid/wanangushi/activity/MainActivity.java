package com.byvoid.wanangushi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.http.HttpRequestUtil;
import com.byvoid.wanangushi.model.TestModel;
import com.byvoid.wanangushi.utils.LogUtils;
import com.lzy.callback.JsonCallback;
import com.lzy.model.LzyResponse;
import com.lzy.okgo.model.Response;


/**
 * @author melody
 * @date 2018/1/24
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpRequestUtil.getTextData(1, new JsonCallback<LzyResponse<TestModel>>() {
            @Override
            public void onSuccess(Response<LzyResponse<TestModel>> response) {
                LogUtils.d(response.body().data.getDes());
            }
        });


        ImageView imageView = (ImageView) findViewById(R.id.image);

        Glide.with(this).load("http://goo.gl/gEgYUd").
                into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.startToMe(getContext(),HomeActivity.class);
            }
        });

    }



}
