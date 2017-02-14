package com.mayi.douyu.entity;

/**
 * 作者 by yugai 时间 16/11/24.
 * ＊ 邮箱 784787081@qq.com
 */

public class DYResponse<T> {
    public static final int SUCCESS_CODE = 0;
    public int error;
    public String msg="请求失败";
    public T data;

    public boolean success(){
        return error==SUCCESS_CODE;
    }

    public int getError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DYResponse{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
