package com.byvoid.wanangushi.module.story.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.RequestOptions;
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

    public RoleAdapter(int layoutResId, @Nullable List<Role> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Role item) {
        helper.setText(R.id.roleNameTv,item.getName());
        helper.setTextColor(R.id.roleNameTv,ResourceUtils.getColor(item.isSelected() ? R.color.green : R.color.cl_666));
        Glide.with(mContext)
             .load(item.getAvatar())
             .apply(new RequestOptions().placeholder(R.mipmap.icon_avatar))
             .into((RoundedImageView)helper.getView(R.id.roleHeadIv));
    }

}
