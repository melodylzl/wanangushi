package com.byvoid.wanangushi.module.story.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.byvoid.wanangushi.base.LoadType;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.adapter.StoryHomeRecyclerViewAdapter;
import com.byvoid.wanangushi.base.BaseFragment;
import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.module.story.mvp.IStoryHomeFragment;
import com.byvoid.wanangushi.module.story.mvp.StoryHomePresenter;
import com.byvoid.wanangushi.utils.ListUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryHomeFragment extends BaseFragment implements IStoryHomeFragment.IView{

    @BindView(R.id.recyclerView)
    protected XRecyclerView mRecyclerView;
    private StoryHomeRecyclerViewAdapter mAdapter;
    private List<Story> mStoryList = new ArrayList<>();
    private StoryHomePresenter mStoryHomePresenter = new StoryHomePresenter(this);

    public static StoryHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        StoryHomeFragment fragment = new StoryHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_story_home;
    }


    @Override
    protected void bindData() {
        super.bindData();
        mAdapter = new StoryHomeRecyclerViewAdapter(getContext(),mStoryList);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mStoryHomePresenter.getStoryList(0);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mStoryHomePresenter.getStoryList(0);
            }

            @Override
            public void onLoadMore() {
                Story story = ListUtils.getLastItem(mStoryList);
                if (null == story){
                    return;
                }
                mStoryHomePresenter.getStoryList(story.getId());
            }
        });

    }

    @Override
    public void updateView(List<Story> storyList,int loadType) {
        if (loadType == LoadType.REFRESH){
            mStoryList.clear();
            mStoryList.addAll(storyList);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.refreshComplete();
        }else if (loadType == LoadType.LOAD_MORE){
            mStoryList.addAll(storyList);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.loadMoreComplete();
        }
    }
}
