package com.byvoid.wanangushi.module.story.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.module.photo.PhotoViewerActivity;
import com.byvoid.wanangushi.module.story.adapter.StoryContentAdapter;
import com.byvoid.wanangushi.module.story.model.StoryDetail;
import com.byvoid.wanangushi.module.story.model.StoryTalk;
import com.byvoid.wanangushi.module.story.mvp.IStoryActivity;
import com.byvoid.wanangushi.module.story.mvp.StoryDetailPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryActivity extends BaseActivity implements IStoryActivity{

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.toolBar)
    protected Toolbar mToolBar;

    protected int mId;
    protected StoryContentAdapter mAdapter;
    protected StoryDetailPresenter mStoryDetailPresenter = new StoryDetailPresenter(this);

    public static void startToMe(Context context,int id){
        Intent intent = new Intent(context,StoryActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntentData(Intent intent) {
        mId = intent.getIntExtra("id",0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
    }

    @Override
    protected void bindData() {
        super.bindData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mStoryDetailPresenter.getStoryDetail(mId);
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
    }

    @Override
    public void setData(StoryDetail storyDetail) {
        mToolBar.setTitle(storyDetail.getName());
        if (mAdapter == null){
            mAdapter = new StoryContentAdapter(storyDetail.getStoryTalkList());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    List<StoryTalk> storyTalks = (List<StoryTalk>) adapter.getData();
                    switch (view.getId()){
                        case R.id.imageIv:
                            List<String> imageUrlList = new ArrayList<>();
                            int index = 0;
                            for (StoryTalk storyTalk : storyTalks) {
                                if (storyTalk.getType() == StoryTalk.TYPE_IMAGE){
                                    imageUrlList.add(storyTalk.getImageUrl());
                                    if (storyTalk.getId() == storyTalks.get(position).getId()){
                                        index = imageUrlList.size() - 1;
                                    }
                                }
                            }
                            PhotoViewerActivity.show(getContext(),imageUrlList,index);
                            break;
                        default:
                            break;
                    }

                }
            });
        }else{
            mAdapter.setNewData(storyDetail.getStoryTalkList());
        }
    }
}
