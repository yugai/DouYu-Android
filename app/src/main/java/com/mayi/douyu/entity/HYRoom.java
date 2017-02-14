package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public class HYRoom {

    /**
     * liveUid : 904391051
     * yyid : 868310139
     * sex : 1
     * gameId : 7
     * gameName : DOTA2
     * limit : 999999
     * aid : 20609694
     * users : 13784
     * livePortait : http://img.huya.com/avatar/1056/64/4d874d8f3e951809c2aa79a6a1bf62_180_135.jpg
     * startTime : 1484718558
     * liveNick : 90583 老叶子
     * recommendStatus : 0
     * liveName : |     ＂ 老 叶 ＂     7000分老年吹比蘸酱 |
     * subSid : 2236239667
     * contentIntro :
     * scoreIntro : 0
     * cameraOpen : false
     * sid : 24710374
     * snapshot : http://screenshot.dwstatic.com/yysnapshot/1ac424336ac75d2e8690f736bed82bb3ec369a17?size=240
     * isPlatinum : 1
     * live_source_type : 0
     */

    private int liveUid;
    private int yyid;
    private int sex;
    private int gameId;
    private String gameName;
    private int limit;
    private int aid;
    private int users;
    private String livePortait;
    private int startTime;
    private String liveNick;
    private int recommendStatus;
    private String liveName;
    private long subSid;
    private String contentIntro;
    private int scoreIntro;
    private boolean cameraOpen;
    private int sid;
    private String snapshot;
    private int isPlatinum;
    private int live_source_type;

    public int getLiveUid() {
        return liveUid;
    }

    public void setLiveUid(int liveUid) {
        this.liveUid = liveUid;
    }

    public int getYyid() {
        return yyid;
    }

    public void setYyid(int yyid) {
        this.yyid = yyid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public String getLivePortait() {
        return livePortait;
    }

    public void setLivePortait(String livePortait) {
        this.livePortait = livePortait;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getLiveNick() {
        return liveNick;
    }

    public void setLiveNick(String liveNick) {
        this.liveNick = liveNick;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public long getSubSid() {
        return subSid;
    }

    public void setSubSid(long subSid) {
        this.subSid = subSid;
    }

    public String getContentIntro() {
        return contentIntro;
    }

    public void setContentIntro(String contentIntro) {
        this.contentIntro = contentIntro;
    }

    public int getScoreIntro() {
        return scoreIntro;
    }

    public void setScoreIntro(int scoreIntro) {
        this.scoreIntro = scoreIntro;
    }

    public boolean isCameraOpen() {
        return cameraOpen;
    }

    public void setCameraOpen(boolean cameraOpen) {
        this.cameraOpen = cameraOpen;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public int getIsPlatinum() {
        return isPlatinum;
    }

    public void setIsPlatinum(int isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    public int getLive_source_type() {
        return live_source_type;
    }

    public void setLive_source_type(int live_source_type) {
        this.live_source_type = live_source_type;
    }

    @Override
    public String toString() {
        return "HYRoom{" +
                "liveUid=" + liveUid +
                ", yyid=" + yyid +
                ", sex=" + sex +
                ", gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", limit=" + limit +
                ", aid=" + aid +
                ", users=" + users +
                ", livePortait='" + livePortait + '\'' +
                ", startTime=" + startTime +
                ", liveNick='" + liveNick + '\'' +
                ", recommendStatus=" + recommendStatus +
                ", liveName='" + liveName + '\'' +
                ", subSid=" + subSid +
                ", contentIntro='" + contentIntro + '\'' +
                ", scoreIntro=" + scoreIntro +
                ", cameraOpen=" + cameraOpen +
                ", sid=" + sid +
                ", snapshot='" + snapshot + '\'' +
                ", isPlatinum=" + isPlatinum +
                ", live_source_type=" + live_source_type +
                '}';
    }
}
