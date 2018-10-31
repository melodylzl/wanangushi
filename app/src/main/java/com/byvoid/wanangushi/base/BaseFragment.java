package com.byvoid.wanangushi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byvoid.wanangushi.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author melody
 * @date 2018/1/24
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "BaseFragment";

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    private Unbinder unbinder;

    /**
     * 当前 Activity 渲染的视图 View
     */
    protected View contentView;


    protected BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        com.orhanobut.logger.Logger.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        contentView = inflater.inflate(getLayoutID(), null);
        unbinder = ButterKnife.bind(this, contentView);
        com.orhanobut.logger.Logger.d(TAG, "onCreateView: ");
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleArguments(getArguments());
        findView(contentView);
        LogUtils.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        bindData();
        setListener();
        getData();
        LogUtils.d(TAG, "onActivityCreated: ");
    }


    @Override
    public void onDestroyView() {
        if (contentView != null) {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        LogUtils.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    protected void handleArguments(Bundle bundle){

    }

    protected void findView(View layout){

    }

    protected void bindData(){

    }

    protected void setListener(){

    }

    protected void getData(){

    }

    @Override
    public void onClick(View view) {
        try {
            handleOnClick(view);
        } catch (Throwable t) {
            LogUtils.d(getClass().getSimpleName(),t);
        }
    }

    protected void handleOnClick(View view){

    }


    /**
     * 获取布局id
     * @return 布局id
     */
    public abstract int getLayoutID();
}
