package com.byvoid.wanangushi.module.story.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.module.story.model.Role;
import com.byvoid.wanangushi.utils.ResourceUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author melody
 * @date 2018/8/21
 */
public class RoleAdapter extends BaseQuickAdapter<Role,BaseViewHolder>{

    private int mCurrentIndex = 0;

    public RoleAdapter(int layoutResId, @Nullable List<Role> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Role item) {
        helper.setText(R.id.roleNameTv,item.getName());
        helper.setTextColor(R.id.roleNameTv,ResourceUtils.getColor(mCurrentIndex == helper.getAdapterPosition() ? R.color.green : R.color.cl_666));
        Glide.with(mContext).load(item.getAvatar()).into((RoundedImageView)helper.getView(R.id.roleHeadIv));
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if (mCurrentIndex == currentIndex){
            return;
        }
        mCurrentIndex = currentIndex;
        notifyDataSetChanged();
    }

}
