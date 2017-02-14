package com.mayi.douyu.ui.video.half;

import android.content.Context;
import android.content.Intent;

import com.mayi.douyu.R;
import com.mayi.douyu.mvpbase.MVPBaseActivity;
import com.mayi.douyu.ui.video.full.FullVideoActivity;

/**
 * 作者 by yugai 时间 16/12/13.
 * ＊ 邮箱 784787081@qq.com
 */

public class HalfVideoActivity extends MVPBaseActivity<HalfVideoContract.View,HalfVideoPresenter> implements  HalfVideoContract.View{


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HalfVideoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_half_video;
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
