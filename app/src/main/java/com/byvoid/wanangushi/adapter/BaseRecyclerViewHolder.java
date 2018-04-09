package com.byvoid.wanangushi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author melody
 * @date 2018/3/23
 */
public class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder{


    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void binData(T t,int position){

    }

    public void setListener(T t,int position){

    }

    public void setListener(List<T> tList, int position){

    }

}
