package com.mayi.douyu.ui.main.panda;


import com.mayi.douyu.entity.PDRoom;
import com.mayi.douyu.mvpbase.BasePresenter;
import com.mayi.douyu.mvpbase.BaseRequestView;

import java.util.List;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveContract {
    interface View extends BaseRequestView{
        void onRequestData(List<PDRoom.Room> pdRooms);
        void onRefreshData(List<PDRoom.Room> pdRooms);
    }
    interface  Presenter extends BasePresenter<View> {
        void getListData(boolean forceUpdate);
    }
}
