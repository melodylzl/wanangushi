package com.byvoid.wanangushi.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 查看大图activity
 * @author melody
 * @date 2018/3/28
 */
public class PhotoViewerActivity extends BaseActivity{

    private int mPosition;
    private List<String> mPhotoUrlList;
    @BindView(R.id.viewPager)
    protected ViewPager mViewPager;

    public static void show(Context context,String photoUrl){
        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add(photoUrl);
        show(context,photoUrlList,0);
    }

    public static void show(Context context,List<String> photoUrlList,int position){
        Intent intent = new Intent(context,PhotoViewerActivity.class);
        intent.putExtra("photoUrlList",(Serializable) photoUrlList);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntentData(Intent intent) {
        super.handleIntentData(intent);
        mPhotoUrlList = (List<String>)intent.getSerializableExtra("photoUrlList");
        mPosition = intent.getIntExtra("position",0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
    }

    @Override
    protected void bindData() {
        super.bindData();
        mViewPager.setAdapter(new ViewPagerAdapter(this,mPhotoUrlList));
        mViewPager.setCurrentItem(mPosition);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }


    static class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private List<String> mPhotoUrlList;

        public ViewPagerAdapter(Context context,List<String> photoUrlList){
            mContext = context;
            mPhotoUrlList = photoUrlList;
        }

        @Override
        public int getCount() {
            return mPhotoUrlList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext,R.layout.item_photo_view,null);
            PhotoView photoView = view.findViewById(R.id.photoView);
            Glide.with(mContext).load(mPhotoUrlList.get(position)).into(photoView);

            TextView orderTv = view.findViewById(R.id.photoOrderTv);
            orderTv.setText(String.format(Locale.getDefault(),"%d/%d",position + 1,getCount()));

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)mContext).finish();
                }
            });

            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
