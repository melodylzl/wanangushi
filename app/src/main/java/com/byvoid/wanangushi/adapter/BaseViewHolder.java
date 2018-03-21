package com.byvoid.wanangushi.adapter;


import android.view.View;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/1
 */
public abstract class BaseViewHolder<T> implements IBaseViewHolder<T> {


    @Override
    public void findView(View view) {

    }

    @Override
    public void setViewData(List<T> data, int position) {

    }

    @Override
    public void setViewListener(List<T> data, int position) {

    }
}
