package com.mayi.douyu.entity;


/**
 * 作者 by yugai 时间 16/11/12.
 * ＊ 邮箱 784787081@qq.com
 */

public class DYDetails {

    /**
     * inNA : 0
     * room_id : 27836
     * rtmp_cdn : ws
     * rtmp_live : 27836rUifPe3S7yA.flv?wsAuth=d00b8a38538822f6a90e8fae8dbf27ac&token=web-douyu-0-27836-f1d1f39a922ae773355a2d8bebe1fd48&logo=0&expire=0&did=91A11B18664C4F70901E41248E2595C0
     * rtmp_url : http://hdl3.douyucdn.cn/live
     * rateSwitch : 1
     * ggad : {"videop":"","play2":"","play3":"","play1":""}
     */

    private int inNA;
    private String room_id;
    private String rtmp_cdn;
    private String rtmp_live;
    private String rtmp_url;
    private int rateSwitch;
    /**
     * videop :
     * play2 :
     * play3 :
     * play1 :
     */

    private GgadEntity ggad;

    public int getInNA() {
        return inNA;
    }

    public void setInNA(int inNA) {
        this.inNA = inNA;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRtmp_cdn() {
        return rtmp_cdn;
    }

    public void setRtmp_cdn(String rtmp_cdn) {
        this.rtmp_cdn = rtmp_cdn;
    }

    public String getRtmp_live() {
        return rtmp_live;
    }

    public void setRtmp_live(String rtmp_live) {
        this.rtmp_live = rtmp_live;
    }

    public String getRtmp_url() {
        return rtmp_url;
    }

    public void setRtmp_url(String rtmp_url) {
        this.rtmp_url = rtmp_url;
    }

    public int getRateSwitch() {
        return rateSwitch;
    }

    public void setRateSwitch(int rateSwitch) {
        this.rateSwitch = rateSwitch;
    }

    public GgadEntity getGgad() {
        return ggad;
    }

    public void setGgad(GgadEntity ggad) {
        this.ggad = ggad;
    }

    public static class GgadEntity {
        private String videop;
        private String play2;
        private String play3;
        private String play1;

        public String getVideop() {
            return videop;
        }

        public void setVideop(String videop) {
            this.videop = videop;
        }

        public String getPlay2() {
            return play2;
        }

        public void setPlay2(String play2) {
            this.play2 = play2;
        }

        public String getPlay3() {
            return play3;
        }

        public void setPlay3(String play3) {
            this.play3 = play3;
        }

        public String getPlay1() {
            return play1;
        }

        public void setPlay1(String play1) {
            this.play1 = play1;
        }
    }
}
