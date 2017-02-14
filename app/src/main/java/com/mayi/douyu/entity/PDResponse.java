package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 16/12/23.
 * ＊ 邮箱 784787081@qq.com
 */

public class PDResponse<T> {
    public enum DataType{
        ITEMS,ROOM
    }
    public static final int SUCCESS_CODE = 0;
    public int errno;
    public String errmsg;
    public T data;

    public boolean success(){
        return errno==SUCCESS_CODE;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PDResponse{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
