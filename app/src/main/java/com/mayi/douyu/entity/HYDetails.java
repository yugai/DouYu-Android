package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 17/1/19.
 * ＊ 邮箱 784787081@qq.com
 */

public class HYDetails {

    /**
     * liveUid : 1595104404
     * yyid : 1804483386
     * sex : 0
     * gameId : 1
     * gameName : 英雄联盟
     * limit : 999999
     * aid : 29370938
     * users : 15963
     * livePortait : http://img.huya.com/avatar/1018/87/2182a93a6a94cc3f0c962c5a6a4cbe_180_135.jpg
     * startTime : 1484795080
     * liveNick : LD-欣欣
     * recommendStatus : 6
     * liveName : ┃　 «欣  欣»  ：EWG深圳區冠軍ADC
     * subSid : 2527169660
     * contentIntro :
     * scoreIntro : 800
     * cameraOpen : false
     * sid : 90883382
     * snapshot : http://screenshot.dwstatic.com/yysnapshot/975b1a1043850f614b07bfdd8bc88291654a8bd9?size=240
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
}
