package com.china.bosh.mylibrary.retrofit.gson;

import java.util.List;

/**
 * @author lzq
 * @date 2021/7/12
 */
public class ResponseDataT<T, U, V, W, X> {
    private T entity;
    private U map;
    private String msg;
    private String code;
    private V qvo;
    private String ukey;
    private W vo;
    private List<X> rows;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public U getMap() {
        return map;
    }

    public void setMap(U map) {
        this.map = map;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public V getQvo() {
        return qvo;
    }

    public void setQvo(V qvo) {
        this.qvo = qvo;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public W getVo() {
        return vo;
    }

    public void setVo(W vo) {
        this.vo = vo;
    }

    public List<X> getRows() {
        return rows;
    }

    public void setRows(List<X> rows) {
        this.rows = rows;
    }
}
