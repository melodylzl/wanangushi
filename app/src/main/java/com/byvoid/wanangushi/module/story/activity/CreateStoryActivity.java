package com.byvoid.wanangushi.module.story.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.adapter.RoleAdapter;
import com.byvoid.wanangushi.module.story.adapter.StoryContentAdapter;
import com.byvoid.wanangushi.base.TakePhotoActivity;
import com.byvoid.wanangushi.module.story.model.Role;
import com.byvoid.wanangushi.module.story.model.StoryTalk;
import com.byvoid.wanangushi.utils.DialogUtils;
import com.byvoid.wanangushi.utils.InputMethodUtils;
import com.byvoid.wanangushi.utils.ListUtils;
import com.byvoid.wanangushi.utils.TakePhotoUtils;
import com.byvoid.wanangushi.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.devio.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/8/21
 */
public class CreateStoryActivity extends TakePhotoActivity {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.toolBar)
    protected Toolbar mToolBar;
    @BindView(R.id.nextTv)
    protected TextView mNextTv;
    @BindView(R.id.roleRecyclerView)
    protected RecyclerView mRoleRecyclerView;
    @BindView(R.id.inputEditText)
    protected EditText mInputEt;
    @BindView(R.id.photoIv)
    protected ImageView mAddPhotoView;
    @BindView(R.id.addRoleBtn)
    protected Button mAddRoleView;
    @BindView(R.id.sendBtn)
    protected Button mSendBtn;

    private List<StoryTalk> mTalkList = new ArrayList<>();
    private StoryContentAdapter mTalkAdapter;

    private List<Role> mRoleList = new ArrayList<>();
    private Role mSelectedRole;
    private RoleAdapter mRoleAdapter;

    private int mainRoleId;


    public static void startToMe(Context context) {
        Intent intent = new Intent(context, CreateStoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);
    }

    @Override
    protected void bindData() {
        super.bindData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mTalkAdapter = new StoryContentAdapter(mTalkList);
        mRecyclerView.setAdapter(mTalkAdapter);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRoleRecyclerView.setLayoutManager(layoutManager);
        Role asideRole = Role.getAsideRole();
        asideRole.setSelected(true);
        mRoleList.add(asideRole);
        mRoleAdapter = new RoleAdapter(R.layout.item_role, mRoleList);
        mRoleRecyclerView.setAdapter(mRoleAdapter);
        mSelectedRole = asideRole;
    }

    @Override
    protected void setListener() {
        super.setListener();
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAddPhotoView.setOnClickListener(this);
        mAddRoleView.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
        mTalkAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext())
                        .items("删除")
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int index, CharSequence text) {
                                adapter.remove(position);
                            }
                        });
                MaterialDialog materialDialog = builder.build();
                materialDialog.show();
                return true;
            }
        });
        mRoleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Role selectedRole = ListUtils.getItem(mRoleList,position);
                if (null == selectedRole){
                    return;
                }
                mSelectedRole = selectedRole;
                for (Role role : mRoleList){
                    role.setSelected(role.getId() ==selectedRole.getId());
                }
                adapter.notifyDataSetChanged();
            }
        });
        mInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (TextUtils.isEmpty(s)){
                    mAddPhotoView.setVisibility(View.VISIBLE);
                    mSendBtn.setVisibility(View.GONE);
                }else{
                    mAddPhotoView.setVisibility(View.GONE);
                    mSendBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()) {
            case R.id.nextTv:
                if (mTalkList.isEmpty()){
                    ToastUtils.show("请输入故事内容");
                    return;
                }
                CreateStoryDetailActivity.startToMe(getContext(),mTalkList,2);
                break;
            case R.id.photoIv:
                if (null == mSelectedRole){
                    return;
                }
                if (mSelectedRole.getId() == Role.ASIDE_ID){
                    ToastUtils.show("旁白不能发图片");
                    return;
                }
                TakePhotoUtils.take(getTakePhoto());
                break;
            case R.id.addRoleBtn:
                SelectRoleActivity.startToMe(getContext(), 1);
                break;
            case R.id.sendBtn:
                String content = mInputEt.getText().toString();
                if (TextUtils.isEmpty(content) || null == mSelectedRole) {
                    return;
                }
                if (mainRoleId == 0 && mSelectedRole.getId() != Role.ASIDE_ID) {
                    mainRoleId = mSelectedRole.getId();
                }
                StoryTalk storyTalk = new StoryTalk();
                storyTalk.setRole(mSelectedRole);
                storyTalk.setRoleId(mSelectedRole.getId());
                storyTalk.setContent(content);
                storyTalk.setType(StoryTalk.TYPE_TEXT);
                storyTalk.setSender(mSelectedRole.getId() == Role.ASIDE_ID ? StoryTalk.SENDER_ASIDE :
                        (mSelectedRole.getId() == mainRoleId ? StoryTalk.SENDER_FIRST_PERSON : StoryTalk.SENDER_THIRD_PERSON));
                mTalkList.add(storyTalk);
                mTalkAdapter.notifyDataSetChanged();
                mInputEt.setText("");
                InputMethodUtils.hideSoftKeyBoard(mInputEt);
                scrollToRecyclerViewBottom();
                break;
            default:
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            List<Role> roleList = (List<Role>) data.getSerializableExtra("roles");
            if (roleList != null && !roleList.isEmpty()){
                for (Role role : roleList){
                    boolean isAdd = false;
                    for (Role addRole : mRoleList){
                        if (role.getId() == addRole.getId()){
                            isAdd = true;
                            break;
                        }
                    }
                    if (!isAdd){
                        mRoleList.add(role);
                    }
                }
                mRoleAdapter.notifyDataSetChanged();
            }
        }else if (requestCode == 2 && resultCode == Activity.RESULT_OK){
            finish();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (null == result || result.getImage() == null) {
            return;
        }
        if (null == mSelectedRole || mSelectedRole.getId() == Role.ASIDE_ID) {
            return;
        }
        if (mainRoleId == 0) {
            mainRoleId = mSelectedRole.getId();
        }
        String imageUrl = result.getImage().getOriginalPath();
        StoryTalk storyTalk = new StoryTalk();
        storyTalk.setRole(mSelectedRole);
        storyTalk.setRoleId(mSelectedRole.getId());
        storyTalk.setImageUrl(imageUrl);
        storyTalk.setType(StoryTalk.TYPE_IMAGE);
        storyTalk.setSender(mSelectedRole.getId() == Role.ASIDE_ID ? StoryTalk.SENDER_ASIDE :
                (mSelectedRole.getId() == mainRoleId ? StoryTalk.SENDER_FIRST_PERSON : StoryTalk.SENDER_THIRD_PERSON));
        mTalkList.add(storyTalk);
        mTalkAdapter.notifyDataSetChanged();
        scrollToRecyclerViewBottom();
    }

    private void scrollToRecyclerViewBottom(){
        if (null == mTalkAdapter || mTalkAdapter.getItemCount() == 0){
            return;
        }
        mRecyclerView.smoothScrollToPosition(mTalkAdapter.getItemCount() - 1);
    }

}
