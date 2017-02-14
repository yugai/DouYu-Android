package com.mayi.douyu.ui.search;

import com.mayi.douyu.entity.DYSearchDetails;
import com.mayi.douyu.mvpbase.BasePresenter;
import com.mayi.douyu.mvpbase.BaseRequestView;
import com.mayi.douyu.mvpbase.BaseView;

import java.util.List;

/**
 * 作者 by yugai 时间 17/1/19.
 * ＊ 邮箱 784787081@qq.com
 */

public class SearchContract {
    interface View extends BaseRequestView{
        void onSearchRequest(List<DYSearchDetails.RoomEntity> roomEntities);
    }
    interface Presenter extends BasePresenter<View>{
        void search(String text);
    }
}
