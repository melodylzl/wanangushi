package com.byvoid.wanangushi.module.story.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.model.StoryTalk;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author melody
 * @date 2018/3/23
 */
public class StoryContentAdapter extends BaseMultiItemQuickAdapter<StoryTalk,BaseViewHolder>{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public StoryContentAdapter(List<StoryTalk> data) {
        super(data);
        addItemType(StoryTalk.SENDER_ASIDE,R.layout.item_story_aside);
        addItemType(StoryTalk.SENDER_FIRST_PERSON,R.layout.item_story_first_person);
        addItemType(StoryTalk.SENDER_THIRD_PERSON,R.layout.item_story_third_person);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoryTalk item) {
        switch (helper.getItemViewType()){
            case StoryTalk.SENDER_ASIDE:
                setAside(helper,item);
                break;
            case StoryTalk.SENDER_FIRST_PERSON:
            case StoryTalk.SENDER_THIRD_PERSON:
                setPerson(helper,item);
                break;
            default:
                break;
        }
    }

    private void setAside(BaseViewHolder helper, StoryTalk item){
        helper.setText(R.id.textTv,item.getContent());
    }

    private void setPerson(BaseViewHolder helper, StoryTalk item){
        helper.setGone(R.id.textTv,item.getType() == StoryTalk.TYPE_TEXT)
              .setGone(R.id.imageIv,item.getType() == StoryTalk.TYPE_IMAGE)
              .setText(R.id.textTv,item.getContent())
              .setText(R.id.userNameTv,item.getRole().getName())
              .addOnClickListener(R.id.imageIv);
        Glide.with(mContext).load(item.getRole().getAvatar()).into((RoundedImageView)helper.getView(R.id.userHeadIv));
        if (item.getType() == StoryTalk.TYPE_IMAGE){
            Glide.with(mContext).load(item.getImageUrl()).into((ImageView) helper.getView(R.id.imageIv));
        }
    }

}
