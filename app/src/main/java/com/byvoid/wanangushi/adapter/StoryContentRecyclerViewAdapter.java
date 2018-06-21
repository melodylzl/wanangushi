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
import com.byvoid.wanangushi.model.StoryContent;
import com.github.chrisbanes.photoview.PhotoView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/3/23
 */
public class StoryContentRecyclerViewAdapter extends RecyclerView.Adapter{

    private static final int TYPE_ASIDE = 0;
    private static final int TYPE_FIRST_PERSON = 1;
    private static final int TYPE_THIRD_PERSON = 2;

    private Context mContext;
    private List<StoryContent> mList;

    public StoryContentRecyclerViewAdapter(Context context,List<StoryContent> list){
        mContext = context;
        mList = list;
    }

    public void setData(List<StoryContent> list){
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
            case TYPE_ASIDE:
                view = View.inflate(mContext, R.layout.item_story_aside,null);
                viewHolder = new AsideViewHolder(view);
                break;
            case TYPE_FIRST_PERSON:
                view = View.inflate(mContext, R.layout.item_story_first_person,null);
                viewHolder = new PersonViewHolder(mContext,view);
                break;
            case TYPE_THIRD_PERSON:
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
        StoryContent storyContent = mList.get(position);
        ((BaseRecyclerViewHolder) holder).binData(storyContent,position);
        ((BaseRecyclerViewHolder) holder).setListener(mList,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    public static class AsideViewHolder extends BaseRecyclerViewHolder<StoryContent>{

        @BindView(R.id.textTv) TextView mTextTv;

        public AsideViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void binData(StoryContent storyContent,int position) {
            super.binData(storyContent,position);
            mTextTv.setText(storyContent.getContent());
        }
    }

    public static class PersonViewHolder extends BaseRecyclerViewHolder<StoryContent>{

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
        public void binData(StoryContent storyContent, int position) {
            super.binData(storyContent, position);
            if (storyContent.getContentType() == StoryContent.TYPE_TEXT){
                mTextTv.setVisibility(View.VISIBLE);
                mImageIv.setVisibility(View.GONE);
                mTextTv.setText(storyContent.getContent());
            }else if (storyContent.getContentType() == StoryContent.TYPE_IMAGE){
                mTextTv.setVisibility(View.GONE);
                mImageIv.setVisibility(View.VISIBLE);
                Glide.with(mImageIv).load(storyContent.getImageUrl()).into(mImageIv);
            }
            Glide.with(mUserHeadIv).load(storyContent.getRole().getAvatar()).into(mUserHeadIv);
            mUserNameTv.setText(storyContent.getRole().getName());
        }

        @Override
        public void setListener(final List<StoryContent> storyContents, final int position) {
            super.setListener(storyContents, position);
            mImageIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> imageUrlList = new ArrayList<>();
                    int index = 0;
                    for (StoryContent storyContent: storyContents) {
                        if (storyContent.getContentType() == StoryContent.TYPE_IMAGE){
                            imageUrlList.add(storyContent.getImageUrl());
                            if (storyContent.getId() == storyContents.get(position).getId()){
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
