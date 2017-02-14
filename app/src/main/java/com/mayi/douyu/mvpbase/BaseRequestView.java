package com.mayi.douyu.mvpbase;

/**
 * 作者 by yugai 时间 16/10/31.
 * ＊ 邮箱 784787081@qq.com
 */

public interface BaseRequestView extends BaseView{
    void onRequestError(String msg);
    void onRequestEnd();
}
