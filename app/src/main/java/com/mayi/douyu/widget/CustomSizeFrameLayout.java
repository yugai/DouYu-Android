package com.mayi.douyu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 作者 by yugai 时间 16/12/13.
 * ＊ 邮箱 784787081@qq.com
 */

public class CustomSizeFrameLayout extends FrameLayout {
    private static final String TAG = "CustomSizeFrameLayout";
    public CustomSizeFrameLayout(Context context) {
        super(context);
    }

    public CustomSizeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSizeFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        WindowManager manager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        Log.i(TAG, "onMeasure: "+display.getHeight()+"---------"+display.getWidth());

        setMeasuredDimension(widthSize, widthSize*display.getWidth()/display.getHeight());
    }
}
