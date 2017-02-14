package com.mayi.douyu.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mayi.douyu.ui.main.CustomErrorActivity;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * 作者 by yugai 时间 16/10/26.
 * ＊ 邮箱 784787081@qq.com
 */

public class App extends Application {
    private static final String TAG = "App";
    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
//        CustomActivityOnCrash.setErrorActivityClass(CustomErrorActivity.class);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
