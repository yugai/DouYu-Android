package com.mayi.douyu.ui.video.full;

import com.mayi.douyu.mvpbase.BasePresenter;
import com.mayi.douyu.mvpbase.BaseRequestView;
import com.yugai.danmu.message.ChatMsg;

/**
 * 作者 by yugai 时间 16/11/29.
 * ＊ 邮箱 784787081@qq.com
 */

public class FullVideoContract {
    interface View extends BaseRequestView{
        void onRequestData(String url);
        void receiveMessage(ChatMsg message);
    }
    interface Presenter extends BasePresenter<View>{
        void getDouyuVideo(String roomId);
        void getPandaVideo(String roomId);
        void getHuYaVideo(int sid,long subsid);
        void connectDanMu(String roomId);
        void closeDanMu();
    }
}
