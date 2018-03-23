package com.byvoid.wanangushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.activity.StoryActivity;
import com.byvoid.wanangushi.model.Story;
import com.byvoid.wanangushi.utils.SizeUtils;
import com.byvoid.wanangushi.utils.ViewUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryHomeRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private Context mContext;
    private List<Story> mList;

    public StoryHomeRecyclerViewAdapter(Context context, List<Story> list){
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_story_home_recycler_view,null);
        view.setId(R.id.id_item_story_home);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Story story = mList.get(position);
        viewHolder.mStoryNameTv.setText(story.getName());
        ViewUtils.refreshHeight(viewHolder.itemView, SizeUtils.dp2px(200 + (position % 5) * 60));
        Glide.with(mContext).load(R.mipmap.flipbg10).into(viewHolder.mStoryCoverIv);

        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_item_story_home:
                StoryActivity.startToMe(mContext,StoryActivity.class);
                break;
            default:
                break;
        }

    }

    private static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mStoryNameTv;
        private RoundedImageView mStoryCoverIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mStoryNameTv = itemView.findViewById(R.id.storyNameTv);
            mStoryCoverIv = itemView.findViewById(R.id.storyCoverIv);
        }
    }

}