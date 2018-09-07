package com.byvoid.wanangushi.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.byvoid.wanangushi.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/9/7
 */
public abstract class BasePullRecyclerViewFragment extends BaseFragment{

    @BindView(R.id.smartRefreshLayout)
    protected SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    @Override
    protected void bindData() {
        super.bindData();
        mRecyclerView.setLayoutManager(initLayoutManager());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pullDownToRefresh();
    }

    /**
     * 初始化LayoutManager
     * @return RecyclerView.LayoutManager
     */
    protected abstract RecyclerView.LayoutManager initLayoutManager();

    @Override
    protected void setListener() {
        super.setListener();
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pullUpToLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pullDownToRefresh();
            }
        });
    }

    /**
     * 下拉刷新
     */
    protected void pullDownToRefresh(){

    }

    /**
     * 上拉加载
     */
    protected void pullUpToLoadMore(){

    }

    protected void finishRefresh(){
        mSmartRefreshLayout.finishRefresh();
    }

    protected void finishLoadMore(){
        mSmartRefreshLayout.finishLoadMore();
    }
}
