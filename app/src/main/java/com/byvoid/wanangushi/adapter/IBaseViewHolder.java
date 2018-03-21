package com.byvoid.wanangushi.adapter;

import android.view.View;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/1
 */
public interface IBaseViewHolder<T> {

    /**
     * findView
     * @param view 父视图
     */
    void findView(View view);

    /**
     * 设置视图内容
     * @param data 数据列
     * @param position 索引
     */
    void setViewData(List<T> data, int position);

    /**
     * 设置监听器
     * @param data 数据列
     * @param position 索引
     */
    void setViewListener(List<T> data,int position);

}
