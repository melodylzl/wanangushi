package com.byvoid.wanangushi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/1
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected Context mContext;
    protected List<T> mData;

    public BaseAdapter(Context context, List<T> data){
        mContext = context;
        mData = data;
    }


    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IBaseViewHolder<T> iBaseViewHolder;
        if (convertView == null){
            convertView = View.inflate(mContext,getLayoutId(),null);
            iBaseViewHolder = generateViewHolder();
            iBaseViewHolder.findView(convertView);
            convertView.setTag(iBaseViewHolder);
        }else{
            iBaseViewHolder = (IBaseViewHolder<T>)convertView.getTag();
        }
        iBaseViewHolder.setViewData(mData,position);
        iBaseViewHolder.setViewListener(mData,position);
        return convertView;
    }

    /**
     * 返回布局的id,继承该抽象类的类必须实现该方法
     * @return int 布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化BaseViewHolder,继承该抽象类的类必须实现该方法
     * @return IBaseViewHolder<T>
     */
    public abstract IBaseViewHolder<T> generateViewHolder();
}
