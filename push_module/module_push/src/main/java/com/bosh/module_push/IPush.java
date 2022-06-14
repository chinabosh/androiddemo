package com.bosh.module_push;

/**
 * @author lzq
 * @date 2020/7/8
 */
public interface IPush {
    /**
     * 自定义消息
     * @param title 标题
     * @param content 内容
     * @param extra 其他
     * @param msgId id
     */
    void onMessageReceive(String title, String content, String extra, String msgId);
}
