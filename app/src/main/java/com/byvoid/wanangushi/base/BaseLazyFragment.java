package com.byvoid.wanangushi.base;

/**
 * 懒加载fragment
 * @author melody
 * @date 2018/10/31
 */
public abstract class BaseLazyFragment extends BaseFragment{

    protected boolean mIsLoadData = false;

    /**
     * 懒加载fragment获取页面数据
     */
    protected abstract void getLazyData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && contentView != null && !mIsLoadData){
            getLazyData();
            mIsLoadData = true;
        }
    }

    @Override
    protected void getData() {
        if (getUserVisibleHint()){
            getLazyData();
            mIsLoadData = true;
        }
    }
}
