package com.byvoid.wanangushi.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.adapter.StoryContentRecyclerViewAdapter;
import com.byvoid.wanangushi.adapter.StoryHomeRecyclerViewAdapter;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.model.Story;
import com.byvoid.wanangushi.model.StoryContent;
import com.byvoid.wanangushi.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryActivity extends BaseActivity{

    private RecyclerView mRecyclerView;

    public static void startToMe(Context context,int id){
        Intent intent = new Intent(context,StoryActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
    }

    @Override
    protected void findView() {
        super.findView();
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void bindData() {
        super.bindData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add("https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg");
        photoUrlList.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180208080314_FhzuAJ_Screenshot.jpeg");
        photoUrlList.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg");

        List<StoryContent> storyContentList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; ++i){
            StoryContent storyContent = new StoryContent();
            storyContent.setId(i);
            storyContent.setType(random.nextInt(3));
            if (storyContent.getType() != 0){
                storyContent.setContentType(random.nextInt(2));
            }
            storyContent.setContent("We take a minute to have a crush on some");
            storyContent.setImageUrl(photoUrlList.get(new Random().nextInt(3)));
            User user = new User();
            user.setUid(1000);
            user.setName("melody");
            user.setAvatar("https://upload.jianshu.io/users/upload_avatars/2086682/50e1b40bb523.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240");
            storyContent.setUser(user);
            storyContentList.add(storyContent);
        }

        mRecyclerView.setAdapter(new StoryContentRecyclerViewAdapter(getContext(),storyContentList));
    }

    @Override
    protected void setListener() {
        super.setListener();
    }
}
