package com.byvoid.wanangushi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.adapter.StoryContentRecyclerViewAdapter;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.mvp.IStoryActivity;
import com.byvoid.wanangushi.mvp.StoryDetailPresenter;

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
    protected StoryContentRecyclerViewAdapter mAdapter;
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
    }

    @Override
    public void setData(StoryDetail storyDetail) {
        mToolBar.setTitle(storyDetail.getName());
        if (mAdapter == null){
            mAdapter = new StoryContentRecyclerViewAdapter(this,storyDetail.getStoryTalkList());
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setData(storyDetail.getStoryTalkList());
        }
    }
}
