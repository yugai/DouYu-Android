package com.mayi.douyu.ui.video.full;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lianjiatech.infrastructure.ProgressLayout;
import com.mayi.douyu.R;
import com.mayi.douyu.ijkplayer.widget.media.IjkVideoView;
import com.mayi.douyu.mvpbase.MVPBaseActivity;
import com.mayi.douyu.util.ViewUtils;
import com.yugai.danmu.message.ChatMsg;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 作者 by yugai 时间 16/11/29.
 * ＊ 邮箱 784787081@qq.com
 */

public class FullVideoActivity extends MVPBaseActivity<FullVideoContract.View, FullVideoPresenter> implements FullVideoContract.View {
    private static final String TAG = "FullVideoActivity";
    public final static int RC_MULTI = 1234;
    public final static String ROOM_ID = "roomid";
    public final static String ROOM_PLATFORM = "platform";
    public final static String ROOM_SID = "sid";
    public final static String ROOM_SUSID = "subsid";
    public final static String MULTI="multi";
    public final static int DOUYU = 0x000001;
    public final static int PADAN = 0x000002;
    public final static int HUYA = 0x000003;
    public static final int HANDLER_HIDE_CONTROLLER = 100;//隐藏MediaController
    public static final int HANDLER_CONTROLLER_DURATION = 5 * 1000;//MediaController显示时间
    private boolean isControllerHiden = true;
    private int platform;
    private String roomId;
    private int sid;
    private long subsid;
    boolean mBackPressed;
    boolean isShowDanmu = true;
    DanmakuContext danmakuContext;
    BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    Handler controllerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLER_HIDE_CONTROLLER) {
                //hide controller
                mLayoutPortrait.setVisibility(View.GONE);
                isControllerHiden = true;
            }
        }
    };

    @Bind(R.id.iv_back_portrait)
    ImageView mIvBackPortrait;
    @Bind(R.id.iv_fullscreen_portrait)
    ImageView mIvFullscreenPortrait;
    @Bind(R.id.iv_play)
    ImageView mIvPlay;
    @Bind(R.id.iv_danmu_visible_portrait)
    ImageView mIvDanmuVisiblePortrait;
    @Bind(R.id.layout_portrait)
    RelativeLayout mLayoutPortrait;
    @Bind(R.id.sv_danmaku)
    DanmakuView mSvDanmaku;
    @Bind(R.id.ijk_view)
    IjkVideoView mIjkView;
    @Bind(R.id.progress)
    ProgressLayout mProgress;
    @Bind(R.id.ly_control)
    FrameLayout mLyControl;

    @OnClick({R.id.iv_back_portrait, R.id.iv_fullscreen_portrait, R.id.iv_play, R.id.iv_danmu_visible_portrait, R.id.ly_control})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_control:
                if (isControllerHiden) {
                    mLayoutPortrait.setVisibility(View.VISIBLE);
                    controllerHandler.removeMessages(HANDLER_HIDE_CONTROLLER);
                    controllerHandler.sendEmptyMessageDelayed(HANDLER_HIDE_CONTROLLER, HANDLER_CONTROLLER_DURATION);
                    isControllerHiden = false;
                } else {
                    controllerHandler.removeMessages(HANDLER_HIDE_CONTROLLER);
                    controllerHandler.sendEmptyMessage(HANDLER_HIDE_CONTROLLER);
                }
                break;
            case R.id.iv_back_portrait:
                finish();
                break;
            case R.id.iv_fullscreen_portrait:
                setResult(RC_MULTI, getIntent());
                finish();
                break;
            case R.id.iv_play:
                mIjkView.stopPlayback();
                mIjkView.release(true);
                mIjkView.stopBackgroundPlay();
                loadVideo(platform);
                break;
            case R.id.iv_danmu_visible_portrait:
                isShowDanmu = !isShowDanmu;
                mIvDanmuVisiblePortrait.setImageResource(isShowDanmu ? R.drawable.selector_btn_danmu_on : R.drawable.selector_btn_danmu_off);
                break;
        }
    }


    @IntDef(value = {DOUYU, PADAN, HUYA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Platfrom {
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_full_video;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mProgress.showLoading();
        platform = getIntent().getIntExtra(ROOM_PLATFORM, 0);
        roomId = getIntent().getStringExtra(ROOM_ID);
        sid = getIntent().getIntExtra(ROOM_SID, 0);
        subsid = getIntent().getLongExtra(ROOM_SUSID, 0);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mSvDanmaku.enableDanmakuDrawingCache(true);//打开绘图缓存，提升绘制效率
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDuplicateMergingEnabled(true);//设置合并重复弹幕
        mSvDanmaku.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                try {
                    mSvDanmaku.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        mSvDanmaku.prepare(parser, danmakuContext);
        loadVideo(platform);
    }

    /**
     * 加载视频
     *
     * @param platform
     */
    private void loadVideo(int platform) {
        switch (platform) {
            case DOUYU:
                if (roomId == null) roomId = getIntent().getStringExtra(ROOM_ID);
                mPresenter.getDouyuVideo(roomId);
                mPresenter.connectDanMu(roomId);
                break;
            case PADAN:
                if (roomId == null) roomId = getIntent().getStringExtra(ROOM_ID);
                mPresenter.getPandaVideo(roomId);
                break;
            case HUYA:
                if (sid == 0) sid = getIntent().getIntExtra(ROOM_SID, 0);
                if (subsid == 0) subsid = getIntent().getLongExtra(ROOM_SUSID, 0);
                mPresenter.getHuYaVideo(sid, subsid);
                break;
        }
    }

    @Override
    public void onRequestData(String url) {
        Log.d(TAG, "onRequestData() called with: url = [" + url + "]");
        if (platform == PADAN) {
            mIjkView.setVideoPath(url);
        } else {
            Uri uri = Uri.parse(url);
            mIjkView.setVideoURI(uri);
        }

        mIjkView.start();
    }

    @Override
    public void receiveMessage(ChatMsg message) {
        if (isShowDanmu == false) {
            return;
        }
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = message.getMessage().replace("[弹幕]", "");
        danmaku.textColor = Color.WHITE;
        danmaku.textSize = ViewUtils.dip2px(this, 10);
        danmaku.setTime(mSvDanmaku.getCurrentTime());
        mSvDanmaku.addDanmaku(danmaku);

    }

    @Override
    public void onRequestError(String msg) {
        mProgress.showNetError(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadVideo(platform);
            }
        });
    }

    @Override
    public void onRequestEnd() {
        mProgress.showContent();
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSvDanmaku != null && mSvDanmaku.isPrepared() && mSvDanmaku.isPaused()) {
            mSvDanmaku.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSvDanmaku != null && mSvDanmaku.isPrepared()) {
            mSvDanmaku.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBackPressed || !mIjkView.isBackgroundPlayEnabled()) {
            mIjkView.stopPlayback();
            mIjkView.release(true);
            mIjkView.stopBackgroundPlay();
        } else {
            mIjkView.enterBackground();
        }
        mPresenter.closeDanMu();
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSvDanmaku != null) {
            mSvDanmaku.release();
            mSvDanmaku = null;
        }
    }
}
