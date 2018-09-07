package com.byvoid.wanangushi.module.story.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.model.Story;
import com.byvoid.wanangushi.utils.SizeUtils;
import com.byvoid.wanangushi.utils.ViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/21
 */
public class StoryHomeAdapter extends BaseQuickAdapter<Story,BaseViewHolder>{


    public StoryHomeAdapter(int layoutResId, @Nullable List<Story> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Story item) {
        int position = helper.getAdapterPosition();
        ViewUtils.refreshHeight(helper.itemView, SizeUtils.dp2px(200 + (position % 5) * 60));

        helper.setText(R.id.storyNameTv,item.getName());
        Glide.with(mContext).load(item.getCoverUrl()).into((RoundedImageView)helper.getView(R.id.storyCoverIv));
    }

}
