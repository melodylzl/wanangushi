package com.byvoid.wanangushi.module.story.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.byvoid.lib.utils.ListUtils;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BasePullRecyclerViewFragment;
import com.byvoid.wanangushi.base.LoadType;
import com.byvoid.wanangushi.module.story.activity.StoryActivity;
import com.byvoid.wanangushi.module.story.adapter.StoryHomeAdapter;
import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.module.story.mvp.IStoryHomeFragment;
import com.byvoid.wanangushi.module.story.mvp.StoryHomePresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryHomeFragment extends BasePullRecyclerViewFragment implements IStoryHomeFragment.IView{

    private StoryHomeAdapter mAdapter;
    private List<Story> mStoryList = new ArrayList<>();
    private StoryHomePresenter mStoryHomePresenter = new StoryHomePresenter(this);

    public static StoryHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        StoryHomeFragment fragment = new StoryHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_story_home;
    }

    @Override
    protected void bindData() {
        super.bindData();
        mAdapter = new StoryHomeAdapter(R.layout.item_story_home_recycler_view,mStoryList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManager() {
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Story story = (Story) adapter.getItem(position);
                if (story != null){
                    StoryActivity.startToMe(getContext(),story.getId());
                }
            }
        });
    }

    @Override
    protected void pullDownToRefresh() {
        super.pullDownToRefresh();
        mStoryHomePresenter.getStoryList(0);
    }

    @Override
    protected void pullUpToLoadMore() {
        super.pullUpToLoadMore();
        Story story = ListUtils.getLastItem(mStoryList);
        if (null == story){
            return;
        }
        mStoryHomePresenter.getStoryList(story.getId());
    }

    @Override
    public void updateView(List<Story> storyList,int loadType) {
        if (loadType == LoadType.REFRESH){
            mStoryList.clear();
            mStoryList.addAll(storyList);
            mAdapter.notifyDataSetChanged();
            finishRefresh();
        }else if (loadType == LoadType.LOAD_MORE){
            mStoryList.addAll(storyList);
            mAdapter.notifyDataSetChanged();
            finishLoadMore();
        }
    }
}
