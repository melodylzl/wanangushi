package com.byvoid.wanangushi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.adapter.StoryHomeRecyclerViewAdapter;
import com.byvoid.wanangushi.base.BaseFragment;
import com.byvoid.wanangushi.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryHomeFragment extends BaseFragment{

    private RecyclerView mRecyclerView;

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
    protected void findView(View layout) {
        super.findView(layout);
        mRecyclerView = layout.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        List<Story> storyList = new ArrayList<>();
        for (int i = 0; i < 20; ++i){
            Story story = new Story();
            story.setName(i + "");
            storyList.add(story);
        }

        mRecyclerView.setAdapter(new StoryHomeRecyclerViewAdapter(getContext(),storyList));



    }

    @Override
    protected void bindData() {
        super.bindData();
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

}
