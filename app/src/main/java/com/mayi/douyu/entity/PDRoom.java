package com.mayi.douyu.entity;

import java.util.List;

/**
 * 作者 by yugai 时间 16/12/23.
 * ＊ 邮箱 784787081@qq.com
 */

public class PDRoom {
    private List<Room> items;

    public List<Room> getItems() {
        return items;
    }

    public void setItems(List<Room> items) {
        this.items = items;
    }

    public static class Room {

        /**
         * id : 27337
         * ver : 1
         * createtime : 2015-10-24 14:28:27
         * updatetime : 2016-12-23 17:21:43
         * name : 国服第一吗？那就先上一个
         * hostid : 3252620
         * person_num : 419741
         * announcement :
         * classification : lol
         * pictures : {"img":"http://i6.pdim.gs/45/9b3c0197960b1715a3cc995902c56ab5/w338/h190.jpg"}
         * status : 2
         * start_time : 1482472954
         * end_time : 1482404667
         * duration : 11967
         * schedule : 0
         * remind_switch : 1
         * remind_content :
         * level : 9
         * stream_status : 1
         * classify_switch : 1
         * reliable : 1
         * banned_reason :
         * unlock_time : 0
         * speak_interval : 2
         * person_num_thres :
         * reduce_ratio :
         * person_switch :
         * watermark_switch : 2
         * watermark_loc : 1
         * account_status : 1
         * person_src : 2
         * display_type : 1
         * tag : 百强王者
         * tag_switch : 4
         * tag_color : 4
         * rcmd_ratio :
         * show_pos : 1
         * rtype_usable : 1
         * room_type : 1
         * rtype_value :
         * style_type : 1
         * room_key : 4b0904bbdb295b039405d8a7879ae43b
         * fans : 0
         * userinfo : {"nickName":"熊丶猫TV小新","rid":"3252620","avatar":"http://i9.pdim.gs/d2e3c59e0a97721a8c06f2e4ab5bd087.jpeg","userName":"PandaTv3252620"}
         */

        private String id;
        private String ver;
        private String createtime;
        private String updatetime;
        private String name;
        private String hostid;
        private String person_num;
        private String announcement;
        private String classification;
        /**
         * img : http://i6.pdim.gs/45/9b3c0197960b1715a3cc995902c56ab5/w338/h190.jpg
         */

        private PicturesEntity pictures;
        private String status;
        private String start_time;
        private String end_time;
        private String duration;
        private String schedule;
        private String remind_switch;
        private String remind_content;
        private String level;
        private String stream_status;
        private String classify_switch;
        private String reliable;
        private String banned_reason;
        private String unlock_time;
        private String speak_interval;
        private String person_num_thres;
        private String reduce_ratio;
        private String person_switch;
        private String watermark_switch;
        private String watermark_loc;
        private String account_status;
        private String person_src;
        private String display_type;
        private String tag;
        private String tag_switch;
        private String tag_color;
        private String rcmd_ratio;
        private String show_pos;
        private String rtype_usable;
        private String room_type;
        private String rtype_value;
        private String style_type;
        private String room_key;
        private String fans;
        /**
         * nickName : 熊丶猫TV小新
         * rid : 3252620
         * avatar : http://i9.pdim.gs/d2e3c59e0a97721a8c06f2e4ab5bd087.jpeg
         * userName : PandaTv3252620
         */

        private UserinfoEntity userinfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHostid() {
            return hostid;
        }

        public void setHostid(String hostid) {
            this.hostid = hostid;
        }

        public String getPerson_num() {
            return person_num;
        }

        public void setPerson_num(String person_num) {
            this.person_num = person_num;
        }

        public String getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(String announcement) {
            this.announcement = announcement;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public PicturesEntity getPictures() {
            return pictures;
        }

        public void setPictures(PicturesEntity pictures) {
            this.pictures = pictures;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public String getRemind_switch() {
            return remind_switch;
        }

        public void setRemind_switch(String remind_switch) {
            this.remind_switch = remind_switch;
        }

        public String getRemind_content() {
            return remind_content;
        }

        public void setRemind_content(String remind_content) {
            this.remind_content = remind_content;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getStream_status() {
            return stream_status;
        }

        public void setStream_status(String stream_status) {
            this.stream_status = stream_status;
        }

        public String getClassify_switch() {
            return classify_switch;
        }

        public void setClassify_switch(String classify_switch) {
            this.classify_switch = classify_switch;
        }

        public String getReliable() {
            return reliable;
        }

        public void setReliable(String reliable) {
            this.reliable = reliable;
        }

        public String getBanned_reason() {
            return banned_reason;
        }

        public void setBanned_reason(String banned_reason) {
            this.banned_reason = banned_reason;
        }

        public String getUnlock_time() {
            return unlock_time;
        }

        public void setUnlock_time(String unlock_time) {
            this.unlock_time = unlock_time;
        }

        public String getSpeak_interval() {
            return speak_interval;
        }

        public void setSpeak_interval(String speak_interval) {
            this.speak_interval = speak_interval;
        }

        public String getPerson_num_thres() {
            return person_num_thres;
        }

        public void setPerson_num_thres(String person_num_thres) {
            this.person_num_thres = person_num_thres;
        }

        public String getReduce_ratio() {
            return reduce_ratio;
        }

        public void setReduce_ratio(String reduce_ratio) {
            this.reduce_ratio = reduce_ratio;
        }

        public String getPerson_switch() {
            return person_switch;
        }

        public void setPerson_switch(String person_switch) {
            this.person_switch = person_switch;
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

        public String getAccount_status() {
            return account_status;
        }

        public void setAccount_status(String account_status) {
            this.account_status = account_status;
        }

        public String getPerson_src() {
            return person_src;
        }

        public void setPerson_src(String person_src) {
            this.person_src = person_src;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag_switch() {
            return tag_switch;
        }

        public void setTag_switch(String tag_switch) {
            this.tag_switch = tag_switch;
        }

        public String getTag_color() {
            return tag_color;
        }

        public void setTag_color(String tag_color) {
            this.tag_color = tag_color;
        }

        public String getRcmd_ratio() {
            return rcmd_ratio;
        }

        public void setRcmd_ratio(String rcmd_ratio) {
            this.rcmd_ratio = rcmd_ratio;
        }

        public String getShow_pos() {
            return show_pos;
        }

        public void setShow_pos(String show_pos) {
            this.show_pos = show_pos;
        }

        public String getRtype_usable() {
            return rtype_usable;
        }

        public void setRtype_usable(String rtype_usable) {
            this.rtype_usable = rtype_usable;
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

        public String getStyle_type() {
            return style_type;
        }

        public void setStyle_type(String style_type) {
            this.style_type = style_type;
        }

        public String getRoom_key() {
            return room_key;
        }

        public void setRoom_key(String room_key) {
            this.room_key = room_key;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public UserinfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public static class PicturesEntity {
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Override
            public String toString() {
                return "PicturesEntity{" +
                        "img='" + img + '\'' +
                        '}';
            }
        }

        public static class UserinfoEntity {
            private String nickName;
            private String rid;
            private String avatar;
            private String userName;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getRid() {
                return rid;
            }

            public void setRid(String rid) {
                this.rid = rid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            @Override
            public String toString() {
                return "UserinfoEntity{" +
                        "nickName='" + nickName + '\'' +
                        ", rid='" + rid + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", userName='" + userName + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "PDRoom{" +
                    "id='" + id + '\'' +
                    ", ver='" + ver + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    ", name='" + name + '\'' +
                    ", hostid='" + hostid + '\'' +
                    ", person_num='" + person_num + '\'' +
                    ", announcement='" + announcement + '\'' +
                    ", classification='" + classification + '\'' +
                    ", pictures=" + pictures +
                    ", status='" + status + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", end_time='" + end_time + '\'' +
                    ", duration='" + duration + '\'' +
                    ", schedule='" + schedule + '\'' +
                    ", remind_switch='" + remind_switch + '\'' +
                    ", remind_content='" + remind_content + '\'' +
                    ", level='" + level + '\'' +
                    ", stream_status='" + stream_status + '\'' +
                    ", classify_switch='" + classify_switch + '\'' +
                    ", reliable='" + reliable + '\'' +
                    ", banned_reason='" + banned_reason + '\'' +
                    ", unlock_time='" + unlock_time + '\'' +
                    ", speak_interval='" + speak_interval + '\'' +
                    ", person_num_thres='" + person_num_thres + '\'' +
                    ", reduce_ratio='" + reduce_ratio + '\'' +
                    ", person_switch='" + person_switch + '\'' +
                    ", watermark_switch='" + watermark_switch + '\'' +
                    ", watermark_loc='" + watermark_loc + '\'' +
                    ", account_status='" + account_status + '\'' +
                    ", person_src='" + person_src + '\'' +
                    ", display_type='" + display_type + '\'' +
                    ", tag='" + tag + '\'' +
                    ", tag_switch='" + tag_switch + '\'' +
                    ", tag_color='" + tag_color + '\'' +
                    ", rcmd_ratio='" + rcmd_ratio + '\'' +
                    ", show_pos='" + show_pos + '\'' +
                    ", rtype_usable='" + rtype_usable + '\'' +
                    ", room_type='" + room_type + '\'' +
                    ", rtype_value='" + rtype_value + '\'' +
                    ", style_type='" + style_type + '\'' +
                    ", room_key='" + room_key + '\'' +
                    ", fans='" + fans + '\'' +
                    ", userinfo=" + userinfo +
                    '}';
        }
    }
}
