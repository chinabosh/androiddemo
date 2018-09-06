package com.china.bosh.mylibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lzq
 * @date 2018/8/3
 */

public class StringUtils {

    /**
     * 从string中去除所有非数字
     * @param value string
     * @return 只有数字的string
     */
    public static String getNumFromString(String value){
        String res;
        String regex = "[^0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        res = m.replaceAll("").trim();
        return res;
    }
}
