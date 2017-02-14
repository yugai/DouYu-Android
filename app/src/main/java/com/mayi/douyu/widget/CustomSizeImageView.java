package com.mayi.douyu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.mayi.douyu.R;

/**
 * 作者 by yugai 时间 16/11/25.
 * ＊ 邮箱 784787081@qq.com
 */

public class CustomSizeImageView extends ImageView{
    private static final String TAG = "CustomSizeImageView";
    enum ImageSizeType{
        HOME(16,9),OTHER(16,9);
        int w,h;
        ImageSizeType(int w,int h) {
            this.w=w;
            this.h=h;
        }
    }
    ImageSizeType mImageSizeType=ImageSizeType.HOME;
    public CustomSizeImageView(Context context) {
        super(context);
    }

    public CustomSizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSizeImageView, defStyleAttr, 0);
        switch (a.getInt(R.styleable.CustomSizeImageView_imageSizeType,0)){
            case 0:
                mImageSizeType=ImageSizeType.HOME;
                break;
            case 1:
                mImageSizeType=ImageSizeType.OTHER;
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSize>heightSize){
            heightSize=widthSize*mImageSizeType.h/mImageSizeType.w;
        }else{
            widthSize=heightSize*mImageSizeType.w/mImageSizeType.h;
        }
        Log.i(TAG, "onMeasure: "+widthSize+"-"+heightSize);
        setMeasuredDimension(widthSize, heightSize);
    }
}
