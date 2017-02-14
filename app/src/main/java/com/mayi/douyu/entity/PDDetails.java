package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 17/1/17.
 * ＊ 邮箱 784787081@qq.com
 */

public class PDDetails {

    /**
     * rid : 5290260
     * name : Misaya若风lol
     * avatar : http://i1.pdim.gs/t019b937b40d8b95c41.jpg
     * bamboos : 434562658
     */

    private HostinfoEntity hostinfo;
    /**
     * stream_addr : {"HD":"1","OD":"0","SD":"1"}
     * room_key : 92d768563027d708b0cb81793d93b3bf
     * plflag_list : {"auth":{"rid":"-78979045","sign":"ae711bf1c1dd446389c1226f05920e62","time":"587df13e"},"backup":["2_4","5_9"],"current":"","default":false,"https":false,"main":"4_8","update_time":""}
     * plflag : 4_8
     * status : 2
     * display_type : 1
     */

    private VideoinfoEntity videoinfo;
    /**
     * id : 666666
     * name : 若风：如果没超神，那你们看了假的若风
     * type : 1
     * bulletin : 每天直播五点半到十点半 周六日休息


     * details : <p style="text-align: center;" title=""><br></p><p title=""><img src="http://i9.pdim.gs/c7f1f81db2a9fc4d23adfd2190e46ba3.jpeg"></p><p title="" style="text-align: center;"><img src="http://www.panda.tv/component/ueditor/themes/default/images/spacer.gif"><a href="http://weibo.com/u/1140554177?refer_flag=1001030001_&amp;nick=Misaya%E8%8B%A5%E9%A3%8Elol&amp;is_all=1"><img src="http://i9.pdim.gs/42f7673b6c5782cedf0eda188e6060ee.jpeg"></a></p><p style="text-align: center;" title=""><br title=""></p><div></div><div></div>
     * person_num : 574831
     * classification : 英雄联盟
     * banned_reason :
     * status : 2
     * unlock_time : 0
     * watermark_switch : 1
     * watermark_loc : 1
     * cover_status : 2
     * cover_timestamp : 1484648766
     * cover_reason :
     * mild_remind_status : 2
     * mild_remind_timestamp : 1484648766
     * mild_remind_reason :
     * account_status : 1
     * pictures : {"img":"http://i9.pdim.gs/45/9e4a4cf4434a0db2e2d372fdc973ab5d/w338/h190.jpg","qrcode":"http://i5.pdim.gs/7c78736d466218fd005eb59709d72528.png"}
     * start_time : 1484648206
     * end_time : 1484648195
     * room_type : 1
     * rtype_value :
     * limitage : 0
     * cate : lol
     */

    private RoominfoEntity roominfo;
    /**
     * rid : -78979045
     * sp_identity : 0
     */

    private UserinfoEntity userinfo;
    /**
     * min_level : 1
     * speak_interval : 3
     * all_forbid : 0
     */

    private ChatconfigEntity chatconfig;

    public HostinfoEntity getHostinfo() {
        return hostinfo;
    }

    public void setHostinfo(HostinfoEntity hostinfo) {
        this.hostinfo = hostinfo;
    }

    public VideoinfoEntity getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(VideoinfoEntity videoinfo) {
        this.videoinfo = videoinfo;
    }

    public RoominfoEntity getRoominfo() {
        return roominfo;
    }

    public void setRoominfo(RoominfoEntity roominfo) {
        this.roominfo = roominfo;
    }

    public UserinfoEntity getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoEntity userinfo) {
        this.userinfo = userinfo;
    }

    public ChatconfigEntity getChatconfig() {
        return chatconfig;
    }

    public void setChatconfig(ChatconfigEntity chatconfig) {
        this.chatconfig = chatconfig;
    }

    public static class HostinfoEntity {
        private int rid;
        private String name;
        private String avatar;
        private String bamboos;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBamboos() {
            return bamboos;
        }

        public void setBamboos(String bamboos) {
            this.bamboos = bamboos;
        }
    }

    public static class VideoinfoEntity {
        /**
         * HD : 1
         * OD : 0
         * SD : 1
         */

        private StreamAddrEntity stream_addr;
        private String room_key;
        private String plflag_list;
        private String plflag;
        private String status;
        private String display_type;

        public StreamAddrEntity getStream_addr() {
            return stream_addr;
        }

        public void setStream_addr(StreamAddrEntity stream_addr) {
            this.stream_addr = stream_addr;
        }

        public String getRoom_key() {
            return room_key;
        }

        public void setRoom_key(String room_key) {
            this.room_key = room_key;
        }

        public String getPlflag_list() {
            return plflag_list;
        }

        public void setPlflag_list(String plflag_list) {
            this.plflag_list = plflag_list;
        }

        public String getPlflag() {
            return plflag;
        }

        public void setPlflag(String plflag) {
            this.plflag = plflag;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public static class StreamAddrEntity {
            private String HD;
            private String OD;
            private String SD;

            public String getHD() {
                return HD;
            }

            public void setHD(String HD) {
                this.HD = HD;
            }

            public String getOD() {
                return OD;
            }

            public void setOD(String OD) {
                this.OD = OD;
            }

            public String getSD() {
                return SD;
            }

            public void setSD(String SD) {
                this.SD = SD;
            }
        }
    }

    public static class RoominfoEntity {
        private String id;
        private String name;
        private String type;
        private String bulletin;
        private String details;
        private String person_num;
        private String classification;
        private String banned_reason;
        private String status;
        private String unlock_time;
        private String watermark_switch;
        private String watermark_loc;
        private String cover_status;
        private int cover_timestamp;
        private String cover_reason;
        private int mild_remind_status;
        private int mild_remind_timestamp;
        private String mild_remind_reason;
        private String account_status;
        /**
         * img : http://i9.pdim.gs/45/9e4a4cf4434a0db2e2d372fdc973ab5d/w338/h190.jpg
         * qrcode : http://i5.pdim.gs/7c78736d466218fd005eb59709d72528.png
         */

        private PicturesEntity pictures;
        private String start_time;
        private String end_time;
        private String room_type;
        private String rtype_value;
        private int limitage;
        private String cate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBulletin() {
            return bulletin;
        }

        public void setBulletin(String bulletin) {
            this.bulletin = bulletin;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPerson_num() {
            return person_num;
        }

        public void setPerson_num(String person_num) {
            this.person_num = person_num;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public String getBanned_reason() {
            return banned_reason;
        }

        public void setBanned_reason(String banned_reason) {
            this.banned_reason = banned_reason;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUnlock_time() {
            return unlock_time;
        }

        public void setUnlock_time(String unlock_time) {
            this.unlock_time = unlock_time;
        }

        public String getWatermark_switch() {
            return watermark_switch;
        }

        public void setWatermark_switch(String watermark_switch) {
            this.watermark_switch = watermark_switch;
        }

        public String getWatermark_loc() {
            return watermark_loc;
        }

        public void setWatermark_loc(String watermark_loc) {
            this.watermark_loc = watermark_loc;
        }

        public String getCover_status() {
            return cover_status;
        }

        public void setCover_status(String cover_status) {
            this.cover_status = cover_status;
        }

        public int getCover_timestamp() {
            return cover_timestamp;
        }

        public void setCover_timestamp(int cover_timestamp) {
            this.cover_timestamp = cover_timestamp;
        }

        public String getCover_reason() {
            return cover_reason;
        }

        public void setCover_reason(String cover_reason) {
            this.cover_reason = cover_reason;
        }

        public int getMild_remind_status() {
            return mild_remind_status;
        }

        public void setMild_remind_status(int mild_remind_status) {
            this.mild_remind_status = mild_remind_status;
        }

        public int getMild_remind_timestamp() {
            return mild_remind_timestamp;
        }

        public void setMild_remind_timestamp(int mild_remind_timestamp) {
            this.mild_remind_timestamp = mild_remind_timestamp;
        }

        public String getMild_remind_reason() {
            return mild_remind_reason;
        }

        public void setMild_remind_reason(String mild_remind_reason) {
            this.mild_remind_reason = mild_remind_reason;
        }

        public String getAccount_status() {
            return account_status;
        }

        public void setAccount_status(String account_status) {
            this.account_status = account_status;
        }

        public PicturesEntity getPictures() {
            return pictures;
        }

        public void setPictures(PicturesEntity pictures) {
            this.pictures = pictures;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getRtype_value() {
            return rtype_value;
        }

        public void setRtype_value(String rtype_value) {
            this.rtype_value = rtype_value;
        }

        public int getLimitage() {
            return limitage;
        }

        public void setLimitage(int limitage) {
            this.limitage = limitage;
        }

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public static class PicturesEntity {
            private String img;
            private String qrcode;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
    }

    public static class UserinfoEntity {
        private int rid;
        private String sp_identity;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getSp_identity() {
            return sp_identity;
        }

        public void setSp_identity(String sp_identity) {
            this.sp_identity = sp_identity;
        }
    }

    public static class ChatconfigEntity {
        private int min_level;
        private int speak_interval;
        private int all_forbid;

        public int getMin_level() {
            return min_level;
        }

        public void setMin_level(int min_level) {
            this.min_level = min_level;
        }

        public int getSpeak_interval() {
            return speak_interval;
        }

        public void setSpeak_interval(int speak_interval) {
            this.speak_interval = speak_interval;
        }

        public int getAll_forbid() {
            return all_forbid;
        }

        public void setAll_forbid(int all_forbid) {
            this.all_forbid = all_forbid;
        }
    }
}
