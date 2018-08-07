package com.byvoid.wanangushi.widget.imageview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


/**
 * 图片根据控件的宽度拉伸，直到图片在宽度方向上填充整个控件；
 * 图片的高根据宽的拉伸比例拉伸，但高度自适应
 * @author melody
 * @date 2018/8/7
 */
public class ResizeImageView extends AppCompatImageView{

    public ResizeImageView(Context context) {
        super(context);
    }

    public ResizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable drawable = getDrawable();
        if (drawable != null){
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
