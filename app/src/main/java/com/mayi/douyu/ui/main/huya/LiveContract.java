package com.mayi.douyu.ui.main.huya;

import com.mayi.douyu.entity.DYRoom;
import com.mayi.douyu.entity.HYRoom;
import com.mayi.douyu.mvpbase.BasePresenter;
import com.mayi.douyu.mvpbase.BaseRequestView;

import java.util.List;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveContract {
    interface View extends BaseRequestView {
        void onRequestData(List<HYRoom> rooms);
        void onRefreshData(List<HYRoom> rooms);
    }
    interface  Presenter extends BasePresenter<View> {
        void getListData(boolean forceUpdate);
    }
}
