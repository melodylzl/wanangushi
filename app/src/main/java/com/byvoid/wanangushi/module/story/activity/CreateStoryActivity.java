package com.byvoid.wanangushi.module.story.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.adapter.RoleRecyclerViewAdapter;
import com.byvoid.wanangushi.module.story.adapter.StoryContentRecyclerViewAdapter;
import com.byvoid.wanangushi.base.TakePhotoActivity;
import com.byvoid.wanangushi.module.story.model.Role;
import com.byvoid.wanangushi.module.story.model.StoryTalk;
import com.byvoid.wanangushi.utils.ListUtils;
import com.byvoid.wanangushi.utils.TakePhotoUtils;

import org.devio.takephoto.model.TResult;

import java.util.ArrayList;
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
    @BindView(R.id.roleRecyclerView)
    protected RecyclerView mRoleRecyclerView;
    @BindView(R.id.inputEditText)
    protected EditText mInputEt;
    @BindView(R.id.photoIv)
    protected ImageView addPhotoView;
    @BindView(R.id.roleIv)
    protected ImageView addRoleView;
    @BindView(R.id.sendBtn)
    protected Button mSendBtn;

    private List<StoryTalk> mTalkList = new ArrayList<>();
    private StoryContentRecyclerViewAdapter mTalkAdapter;

    private List<Role> mRoleList = new ArrayList<>();
    private RoleRecyclerViewAdapter mRoleAdapter;

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
        mToolBar.inflateMenu(R.menu.create_story_menu);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mTalkAdapter = new StoryContentRecyclerViewAdapter(this, mTalkList);
        mRecyclerView.setAdapter(mTalkAdapter);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRoleRecyclerView.setLayoutManager(layoutManager);
        mRoleAdapter = new RoleRecyclerViewAdapter(this, mRoleList);
        mRoleRecyclerView.setAdapter(mRoleAdapter);
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
        addPhotoView.setOnClickListener(this);
        addRoleView.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_next:
                        CreateStoryDetailActivity.startToMe(getContext(),mTalkList);
                        return true;

                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void handleOnClick(View view) {
        super.handleOnClick(view);
        switch (view.getId()) {
            case R.id.photoIv:
                TakePhotoUtils.take(getTakePhoto());
                break;
            case R.id.roleIv:
                SelectRoleActivity.startToMe(getContext(), 1);
                break;
            case R.id.sendBtn:
                String content = mInputEt.getText().toString();
                Role role = ListUtils.getItem(mRoleList, mRoleAdapter.getmCurrentIndex());
                if (TextUtils.isEmpty(content) || null == role) {
                    return;
                }
                if (mainRoleId == 0 && role.getId() != Role.ASIDE_ID) {
                    mainRoleId = role.getId();
                }
                StoryTalk storyTalk = new StoryTalk();
                storyTalk.setRole(role);
                storyTalk.setRoleId(role.getId());
                storyTalk.setContent(content);
                storyTalk.setType(StoryTalk.TYPE_TEXT);
                storyTalk.setSender(role.getId() == Role.ASIDE_ID ? StoryTalk.SENDER_ASIDE :
                        (role.getId() == mainRoleId ? StoryTalk.SENDER_FIRST_PERSON : StoryTalk.SENDER_THIRD_PERSON));
                mTalkList.add(storyTalk);
                mTalkAdapter.notifyDataSetChanged();
                mInputEt.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Role role = (Role) data.getSerializableExtra("role");
            mRoleList.add(role);
            mRoleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (null == result || result.getImage() == null) {
            return;
        }
        Role role = ListUtils.getItem(mRoleList, mRoleAdapter.getmCurrentIndex());
        if (null == role || role.getId() == Role.ASIDE_ID) {
            return;
        }
        if (mainRoleId == 0) {
            mainRoleId = role.getId();
        }
        String imageUrl = result.getImage().getOriginalPath();
        StoryTalk storyTalk = new StoryTalk();
        storyTalk.setRole(role);
        storyTalk.setRoleId(role.getId());
        storyTalk.setImageUrl(imageUrl);
        storyTalk.setType(StoryTalk.TYPE_IMAGE);
        storyTalk.setSender(role.getId() == Role.ASIDE_ID ? StoryTalk.SENDER_ASIDE :
                (role.getId() == mainRoleId ? StoryTalk.SENDER_FIRST_PERSON : StoryTalk.SENDER_THIRD_PERSON));
        mTalkList.add(storyTalk);
        mTalkAdapter.notifyDataSetChanged();
    }


}
