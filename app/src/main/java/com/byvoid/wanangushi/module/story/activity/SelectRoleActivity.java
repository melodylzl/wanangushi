package com.byvoid.wanangushi.module.story.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.module.story.adapter.RoleAdapter;
import com.byvoid.wanangushi.module.story.eventbus.EventAddRole;
import com.byvoid.wanangushi.module.story.model.Role;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/8/21
 */
public class SelectRoleActivity extends BaseActivity{

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.toolBar)
    protected Toolbar mToolBar;
    @BindView(R.id.completeTv)
    protected TextView mCompleteTv;
    @BindView(R.id.createRoleBtn)
    protected Button mCreateRoleBtn;
    private List<Role> mRoleList = new ArrayList<>();
    private RoleAdapter mRoleAdapter;

    public static void startToMe(Context context,int requestCode){
        Intent intent = new Intent(context,SelectRoleActivity.class);
        ((Activity)context).startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
    }

    @Override
    protected void bindData() {
        super.bindData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRoleAdapter = new RoleAdapter(R.layout.item_role,mRoleList);
        mRecyclerView.setAdapter(mRoleAdapter);
        getData();
    }

    @Override
    protected void setListener() {
        super.setListener();
        EventBus.getDefault().register(this);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCreateRoleBtn.setOnClickListener(this);
        mCompleteTv.setOnClickListener(this);
        mRoleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Role role = (Role) adapter.getItem(position);
                if (role != null){
                    role.setSelected(!role.isSelected());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()){
            case R.id.createRoleBtn:
                CreateRoleActivity.startToMe(getContext());
                break;
            case R.id.completeTv:
                List<Role> selectedRoleList = new ArrayList<>();
                for (Role role : mRoleList){
                    if (role.isSelected()){
                        role.setSelected(false);
                        selectedRoleList.add(role);
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("roles",(Serializable) selectedRoleList);
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;
            default:
                break;
        }
    }

    protected void getData(){
        HttpService.getRoleList(0, new BaseCallBack<List<Role>>() {
            @Override
            public void onSuccess(List<Role> data, String msg) {
                mRoleList.clear();
                mRoleList.addAll(data);
                mRoleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg, int code) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventAddRole event) {
        getData();
    }

}
