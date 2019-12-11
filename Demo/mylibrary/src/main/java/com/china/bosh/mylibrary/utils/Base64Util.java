package com.china.bosh.mylibrary.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author lzq
 * @date 2019-09-05
 */
public class Base64Util {

    public static String encode(String str) {
        byte[] b = null;
        String s = "";
        if(TextUtils.isEmpty(str)) {
            return s;
        }
        try {
            b = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b != null) {
            s = Base64.encodeToString(b, Base64.NO_WRAP);
        }
        return s;
    }

    public static String decode(String str) {
        byte[] b = null;
        String s = "";
        if(TextUtils.isEmpty(str)) {
            return s;
        }
        try {
            b = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b != null) {
            s = new String(Base64.decode(b, Base64.DEFAULT));
        }
        return s;
    }
}
