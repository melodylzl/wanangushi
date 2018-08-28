package com.byvoid.wanangushi.module.story.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.adapter.BaseRecyclerViewHolder;
import com.byvoid.wanangushi.adapter.OnItemClickListener;
import com.byvoid.wanangushi.module.story.model.Role;
import com.byvoid.wanangushi.utils.ResourceUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/8/21
 */
public class RoleRecyclerViewAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<Role> mList;
    private int mCurrentIndex = 0;


    public RoleRecyclerViewAdapter(Context context,List<Role> list){
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = View.inflate(mContext, R.layout.item_role,null);
        viewHolder = new RoleViewHolder(mContext,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Role role = mList.get(position);
        ((RoleViewHolder) holder).binData(role,position,mCurrentIndex);
        ((RoleViewHolder) holder).setListener(mList,position,onItemClickListener);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = position;
                notifyDataSetChanged();
                if (null != onItemClickListener){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    public int getmCurrentIndex() {
        return mCurrentIndex;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class RoleViewHolder extends BaseRecyclerViewHolder<Role> {

        private Context mContext;
        @BindView(R.id.roleHeadIv)
        RoundedImageView mRoleHeadIv;
        @BindView(R.id.roleNameTv)
        TextView mRoleNameTv;

        public RoleViewHolder(Context context,View itemView) {
            super(itemView);
            mContext = context;
        }


        public void binData(Role role, int position,int currentIndex) {
            super.binData(role, position);
            mRoleNameTv.setText(role.getName());
            mRoleNameTv.setTextColor(ResourceUtils.getColor(currentIndex == position ? R.color.green : R.color.cl_666));
            Glide.with(mContext).load(role.getAvatar()).into(mRoleHeadIv);
        }

        @Override
        public void setListener(final List<Role> roleList, final int position, final OnItemClickListener onItemClickListener) {
            super.setListener(roleList, position,onItemClickListener);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
