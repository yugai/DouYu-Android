package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 17/2/5.
 * ＊ 邮箱 784787081@qq.com
 */

public class HYClassify {

    /**
     * gameId : 1
     * gameName : 英雄联盟
     * imgUrl : http://img.huya.com/cdnimage/game/1.jpg
     * imgSmallUrl : http://img.huya.com/cdnimage/game/phone1.jpg
     * imgBigUrl : http://img.huya.com/cdnimage/game/1-S.jpg
     * liveCount : 680
     * userCount : 1442516
     */

    private int gameId;
    private String gameName;
    private String imgUrl;
    private String imgSmallUrl;
    private String imgBigUrl;
    private int liveCount;
    private int userCount;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgSmallUrl() {
        return imgSmallUrl;
    }

    public void setImgSmallUrl(String imgSmallUrl) {
        this.imgSmallUrl = imgSmallUrl;
    }

    public String getImgBigUrl() {
        return imgBigUrl;
    }

    public void setImgBigUrl(String imgBigUrl) {
        this.imgBigUrl = imgBigUrl;
    }

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
