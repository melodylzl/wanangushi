package com.byvoid.wanangushi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.activity.PhotoViewerActivity;
import com.byvoid.wanangushi.model.StoryTalk;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/3/23
 */
public class StoryContentRecyclerViewAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<StoryTalk> mList;

    public StoryContentRecyclerViewAdapter(Context context,List<StoryTalk> list){
        mContext = context;
        mList = list;
    }

    public void setData(List<StoryTalk> list){
        if (mList != list){
            mList.clear();
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        switch (viewType){
            case StoryTalk.SENDER_ASIDE:
                view = View.inflate(mContext, R.layout.item_story_aside,null);
                viewHolder = new AsideViewHolder(view);
                break;
            case StoryTalk.SENDER_FIRST_PERSON:
                view = View.inflate(mContext, R.layout.item_story_first_person,null);
                viewHolder = new PersonViewHolder(mContext,view);
                break;
            case StoryTalk.SENDER_THIRD_PERSON:
                view = View.inflate(mContext, R.layout.item_story_third_person,null);
                viewHolder = new PersonViewHolder(mContext,view);
                break;

            default:
                view = View.inflate(mContext, R.layout.item_story_aside,null);
                viewHolder = new AsideViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StoryTalk storyTalk = mList.get(position);
        ((BaseRecyclerViewHolder) holder).binData(storyTalk,position);
        ((BaseRecyclerViewHolder) holder).setListener(mList,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getSender();
    }

    public static class AsideViewHolder extends BaseRecyclerViewHolder<StoryTalk>{

        @BindView(R.id.textTv) TextView mTextTv;

        public AsideViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void binData(StoryTalk storyTalk, int position) {
            super.binData(storyTalk,position);
            mTextTv.setText(storyTalk.getContent());
        }
    }

    public static class PersonViewHolder extends BaseRecyclerViewHolder<StoryTalk>{

        private Context mContext;
        @BindView(R.id.textTv) TextView mTextTv;
        @BindView(R.id.imageIv) ImageView mImageIv;
        @BindView(R.id.userHeadIv) RoundedImageView mUserHeadIv;
        @BindView(R.id.userNameTv) TextView mUserNameTv;


        public PersonViewHolder(Context context,View itemView) {
            super(itemView);
            mContext = context;
        }

        @Override
        public void binData(StoryTalk storyTalk, int position) {
            super.binData(storyTalk, position);
            if (storyTalk.getType() == StoryTalk.TYPE_TEXT){
                mTextTv.setVisibility(View.VISIBLE);
                mImageIv.setVisibility(View.GONE);
                mTextTv.setText(storyTalk.getContent());
            }else if (storyTalk.getType() == StoryTalk.TYPE_IMAGE){
                mTextTv.setVisibility(View.GONE);
                mImageIv.setVisibility(View.VISIBLE);
                Glide.with(mImageIv).load(storyTalk.getImageUrl()).into(mImageIv);
            }
            Glide.with(mUserHeadIv).load(storyTalk.getRole().getAvatar()).into(mUserHeadIv);
            mUserNameTv.setText(storyTalk.getRole().getName());
        }

        @Override
        public void setListener(final List<StoryTalk> storyTalks, final int position) {
            super.setListener(storyTalks, position);
            mImageIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> imageUrlList = new ArrayList<>();
                    int index = 0;
                    for (StoryTalk storyTalk : storyTalks) {
                        if (storyTalk.getType() == StoryTalk.TYPE_IMAGE){
                            imageUrlList.add(storyTalk.getImageUrl());
                            if (storyTalk.getId() == storyTalks.get(position).getId()){
                                index = imageUrlList.size() - 1;
                            }
                        }
                    } 
                    PhotoViewerActivity.show(mContext,imageUrlList,index);
                }
            });
        }
    }

}
