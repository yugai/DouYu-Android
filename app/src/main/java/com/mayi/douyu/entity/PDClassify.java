package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 17/2/5.
 * ＊ 邮箱 784787081@qq.com
 */

public class PDClassify {

    /**
     * cname : ????
     * ename : lol
     * img : http://i9.pdim.gs/23e529ba353b33c2f70e6d60f6be4c29.jpeg
     * ext : 1000
     * status : 1
     * cdn_rate : 0
     */

    private String cname;
    private String ename;
    private String img;
    private String ext;
    private String status;
    private String cdn_rate;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCdn_rate() {
        return cdn_rate;
    }

    public void setCdn_rate(String cdn_rate) {
        this.cdn_rate = cdn_rate;
    }
}
