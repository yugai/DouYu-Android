package com.mayi.douyu.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.mayi.douyu.R;
import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.ijkplayer.widget.media.IjkVideoView;
import com.mayi.douyu.util.ViewUtils;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * 作者 by yugai 时间 17/1/24.
 * ＊ 邮箱 784787081@qq.com
 */

public class FloatWindow {
    private WindowManager.LayoutParams mLayoutParams;
    private DisplayMetrics mDisplayMetrics;
    private WindowManager mWindowManager;
    private Context mContext;
    private IjkVideoView mIjkView;
    private View mContentView;
    private RelativeLayout mRvFloat;

    private int height = 90;
    private int width = 160;
    private int heightStep = -height / 2;
    private int widthStep = -width / 2;

    private static final int WHAT_HIDE = 0x275;
    private final float DISTANCE = 15.0f;  //  点击偏移量   在上、下、左、右这个范围之内都会触发点击事件
    private float offsetX, offsetY;

    private long lastTouchTimeMillis;
    private long downTimeMillis;  //  按下事件  暂未使用，可以拓展长按事件

    private boolean mIsShowing;
    private float downX, downY;

    private View mFloatView;

    private static final String TAG = "FloatWindow";

    /**
     * 不带布局参数的构造方法
     */
    public FloatWindow(Context context) {
        this(context, null);
    }

    /**
     * 带布局参数的构造方法
     */
    public FloatWindow(Context context, View floatView) {
        this.mContext = context;
        setContentView(floatView);
        initWindowManager();
        initLayoutParams();
    }

    /**
     * 设置窗口当前布局
     */
    public void setContentView(View contentView) {
        if (contentView != null) {
            this.mFloatView = contentView;
            initPlay();
            if (isShowing()) {
                getWindowManager().removeView(mContentView);
                createContentView(contentView);
                getWindowManager().addView(mContentView, getLayoutParams());
                updateLocation(getDisplayMetrics().widthPixels / 2, getDisplayMetrics().heightPixels / 2, true);
            } else {
                createContentView(contentView);
            }
        }
    }

    private void initPlay() {
        mIjkView = (IjkVideoView) mFloatView.findViewById(R.id.ijk_view);
        mRvFloat= (RelativeLayout) mFloatView.findViewById(R.id.rv_float);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }


    /**
     * 配置布局View， 需要在此处获得View的宽、高，并由此获得偏移量
     */
    private void createContentView(final View contentView) {
        this.mContentView = contentView;
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED); // 主动计算视图View的宽高信息
        offsetY = getStatusBarHeight(getContext()) + contentView.getMeasuredHeight() / 2;
        offsetX = contentView.getMeasuredWidth() / 2;
        contentView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                stop();
            }
        });
        contentView.findViewById(R.id.iv_zoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (width==320||width==160){
                    heightStep=-heightStep;
                    widthStep=-widthStep;
                }
                height=height+heightStep;
                width=width+widthStep;
                ViewGroup.LayoutParams layoutParams=mRvFloat.getLayoutParams();
                layoutParams.height=ViewUtils.dip2px(getContext(),height);
                layoutParams.width=ViewUtils.dip2px(getContext(),width);
                mRvFloat.setLayoutParams(layoutParams);
            }
        });

        contentView.setOnTouchListener(new WindowTouchListener());
    }

    /**
     * 获得上下文信息
     */
    public Context getContext() {
        return this.mContext;
    }

    public WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 获得WindowManager.LayoutParams参数
     */
    public WindowManager.LayoutParams getLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
            initLayoutParams();
        }
        return mLayoutParams;
    }

    /**
     * 获得显示信息
     */
    public DisplayMetrics getDisplayMetrics() {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = getContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics;
    }

    /**
     * 获得当前视图
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 初始化窗口管理器
     */
    private void initWindowManager() {
        mWindowManager = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    /**
     * 初始化WindowManager.LayoutParams参数
     */
    private void initLayoutParams() {
        getLayoutParams().flags = getLayoutParams().flags
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        getLayoutParams().dimAmount = 0.2f;
        getLayoutParams().type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getLayoutParams().gravity = Gravity.LEFT | Gravity.TOP;
        getLayoutParams().format = PixelFormat.RGBA_8888;
        getLayoutParams().alpha = 1.0f;  //  设置整个窗口的透明度
        offsetX = 0;
        offsetY = getStatusBarHeight(getContext());
        getLayoutParams().x = (int) (mDisplayMetrics.widthPixels - offsetX);
        getLayoutParams().y = (int) (mDisplayMetrics.heightPixels * 1 / 4 - offsetY);
    }

    /**
     * 获取状态栏的高度
     */
    public int getStatusBarHeight(Context context) {
        int height = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = context.getResources().getDimensionPixelSize(resId);
        }
        return height;
    }

    /**
     * 更新窗口的位置
     */
    private void updateLocation(float x, float y, boolean offset) {
        if (getContentView() != null) {
            if (offset) {
                getLayoutParams().x = (int) (x - offsetX);
                getLayoutParams().y = (int) (y - offsetY);
            } else {
                getLayoutParams().x = (int) x;
                getLayoutParams().y = (int) y;
            }
            getWindowManager().updateViewLayout(mContentView, getLayoutParams());
        }
    }

    /**
     * 显示窗口
     */
    public void show() {
        if (getContentView() != null && !isShowing()) {
            getWindowManager().addView(getContentView(), getLayoutParams());
            mIsShowing = true;
            handler.sendEmptyMessage(WHAT_HIDE);
        }
    }


    /**
     * 隐藏当前显示窗口
     */
    public void dismiss() {
        if (getContentView() != null && isShowing()) {
            getWindowManager().removeView(getContentView());
            mIsShowing = false;
        }
    }

    /**
     * 开始播放
     *
     * @param url
     */
    public void start(String url) {
        Log.d(TAG, "start() called with: url = [" + url + "]");
        if (isShowing()) {
            Uri uri = Uri.parse(url);
            mIjkView.setVideoURI(uri);
            mIjkView.start();
        }
    }

    /**
     * 停止播放器
     */
    public void stop() {
        if (!mIjkView.isBackgroundPlayEnabled()) {
            mIjkView.stopPlayback();
            mIjkView.release(true);
            mIjkView.stopBackgroundPlay();
        } else {
            mIjkView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }


    /**
     * 判断当前是否有显示窗口
     */
    public boolean isShowing() {
        return mIsShowing;
    }

    class WindowTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    down(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    move(event);
                    break;
                case MotionEvent.ACTION_UP:
                    up(event);
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    /**
     * 按下事件处理
     */
    private void down(MotionEvent event) {
        downX = event.getRawX();
        downY = event.getRawY();
        getLayoutParams().alpha = 1.0f;
        downTimeMillis = System.currentTimeMillis();
        lastTouchTimeMillis = System.currentTimeMillis();
        getWindowManager().updateViewLayout(getContentView(), getLayoutParams());
        updateLocation(event.getRawX(), event.getRawY(), true);
    }

    /**
     * 移动事件处理
     */
    private void move(MotionEvent event) {
        lastTouchTimeMillis = System.currentTimeMillis();
        updateLocation(event.getRawX(), event.getRawY(), true);
    }

    /**
     * 抬起事件处理
     */
    private void up(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        if (x >= downX - DISTANCE && x <= downX + DISTANCE && y >= downY - DISTANCE && y <= downY + DISTANCE) {
            if (System.currentTimeMillis() - downTimeMillis > 1200) {
                //  长按
            }
        }
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_HIDE:
                    if (System.currentTimeMillis() - lastTouchTimeMillis >= 3500) {
                        getLayoutParams().alpha = 1.0f;
                        getWindowManager().updateViewLayout(getContentView(), getLayoutParams());

                    } else {
                        handler.sendEmptyMessageDelayed(WHAT_HIDE, 200);
                    }
                    break;
            }
        }
    };
}
