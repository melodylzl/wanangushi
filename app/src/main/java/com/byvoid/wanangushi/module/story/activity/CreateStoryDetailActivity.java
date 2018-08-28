package com.byvoid.wanangushi.module.story.activity;

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
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.base.BaseResponse;
import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.module.story.model.StoryTalk;
import com.byvoid.wanangushi.module.qiniu.QiniuUpload;
import com.byvoid.wanangushi.utils.LogUtils;
import com.byvoid.wanangushi.utils.TakePhotoUtils;
import com.byvoid.wanangushi.utils.ToastUtils;
import com.google.gson.Gson;

import org.devio.takephoto.model.TResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
                Story story = new Story();
                story.setCoverUrl(mCoverUrl);
                story.setName(name);
                uploadStory(story,mStoryTalkList);
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

    public void uploadStory(final Story story, final List<StoryTalk> storyTalkList){
        List<String> uploadImageList = new ArrayList<>();
        uploadImageList.add(story.getCoverUrl());
        for (StoryTalk storyTalk : storyTalkList){
            if (!TextUtils.isEmpty(storyTalk.getImageUrl())){
                uploadImageList.add(storyTalk.getImageUrl());
            }
        }
        QiniuUpload.uploadFile(uploadImageList, new QiniuUpload.IUploadCallBack() {
            @Override
            public void onSuccess(HashMap<String, String> urlMaps) {
                String imageUrl = urlMaps.get(story.getCoverUrl());
                story.setCoverUrl(imageUrl != null ? imageUrl : "");
                for (StoryTalk storyTalk : storyTalkList){
                    imageUrl = urlMaps.get(storyTalk.getImageUrl());
                    storyTalk.setImageUrl(imageUrl != null ? imageUrl : "");
                }
                Gson gson = new Gson();
                HttpService.createStory(story.getName(), story.getCoverUrl(), gson.toJson(storyTalkList), new BaseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse data, String msg) {
                        ToastUtils.show(msg);
                    }

                    @Override
                    public void onFail(String msg, int code) {
                        ToastUtils.show(msg);
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.show(msg);
                LogUtils.i("UploadStoryHelper","onFail msg=" + msg);
            }
        });
    }
}
