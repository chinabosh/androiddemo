package com.bosh.module_mvp.network;

/**
 * @author lzq
 * @date 2019-07-12
 */
public class ResponseData<V extends Object> {
    private String code;
    private String msg;
    private V data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }
}
