package com.mayi.douyu.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mayi.douyu.util.AppManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 作者 by yugai 时间 16/10/31.
 * ＊ 邮箱 784787081@qq.com
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }

    protected abstract int getLayout();
}
