package com.byvoid.wanangushi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.TakePhotoActivity;
import com.byvoid.wanangushi.eventbus.EventAddRole;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.BaseResponse;
import com.byvoid.wanangushi.model.StoryTalk;
import com.byvoid.wanangushi.utils.LogUtils;
import com.byvoid.wanangushi.utils.TakePhotoUtils;
import com.google.gson.Gson;

import org.devio.takephoto.model.TResult;
import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/8/21
 */
public class CreateStoryDetailActivity extends TakePhotoActivity{

    @BindView(R.id.toolBar)
    protected Toolbar mToolBar;
    @BindView(R.id.coverIv)
    protected ImageView mCoverIv;
    @BindView(R.id.nameEt)
    protected EditText mNameEt;
    @BindView(R.id.completeBtn)
    protected Button mCompleteBtn;

    private String mCoverUrl;

    private List<StoryTalk> mStoryTalkList;

    public static void startToMe(Context context, List<StoryTalk> storyTalkList){
        Intent intent = new Intent(context,CreateStoryDetailActivity.class);
        intent.putExtra("storyTalkList",(Serializable) storyTalkList);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntentData(Intent intent) {
        super.handleIntentData(intent);
        mStoryTalkList = (List<StoryTalk>) intent.getSerializableExtra("storyTalkList");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story_detail);
    }

    @Override
    protected void bindData() {
        super.bindData();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCoverIv.setOnClickListener(this);
        mCompleteBtn.setOnClickListener(this);
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()){
            case R.id.completeBtn:
                String name = mNameEt.getText().toString();
                if (TextUtils.isEmpty(mCoverUrl) || TextUtils.isEmpty(name)){
                    return;
                }
                Gson gson = new Gson();
                HttpService.createStory(name, mCoverUrl, gson.toJson(mStoryTalkList), new BaseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse data, String msg) {

                    }

                    @Override
                    public void onFail(String msg, int code) {

                    }
                });

                break;
            case R.id.coverIv:
                TakePhotoUtils.take(getTakePhoto());
                break;
            default:
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (null == result || result.getImage() == null){
            return;
        }
        mCoverUrl = result.getImage().getOriginalPath();
        Glide.with(this).load(mCoverUrl).into(mCoverIv);
    }
}
