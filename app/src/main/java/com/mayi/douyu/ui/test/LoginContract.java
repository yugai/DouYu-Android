package com.mayi.douyu.ui.test;

import android.content.Context;

import com.mayi.douyu.mvpbase.BasePresenter;
import com.mayi.douyu.mvpbase.BaseView;

/**
 * 作者 by yugai 时间 16/11/1.
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void onLoginSuccess();
        void onLoginFail();
    }

    interface  Presenter extends BasePresenter<View> {
        void login(Context context, String userName, String loginToken);
    }
}
