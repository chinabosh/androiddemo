package com.china.bosh.mylibrary.utils;

import org.apache.commons.codec.Charsets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lzq
 * @date 2018/8/3
 */

public class StringUtils {

    /**
     * 从string中去除所有非数字
     *
     * @param value string
     * @return 只有数字的string
     */
    public static String getNumFromString(String value) {
        String res;
        String regex = "[^0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        res = m.replaceAll("").trim();
        return res;
    }

    /**
     * @param str       源字符串
     * @param character 要寻找的字符串
     * @param count     出现次数
     * @return str中character出现count次的位置
     */
    public static int getCharacterPosition(String str, String character, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must larger than 0");
        }
        Matcher matcher = Pattern.compile(character).matcher(str);
        int index = 0;
        while (matcher.find()) {
            index++;
            if (index == count) {
                break;
            }
        }
        index = -1;
        try {
            index = matcher.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return index;
    }

    /**
     * 替换str指定位置的字符串为replace
     *
     * @param start   包括
     * @param end     不包括
     * @param str     源字符串
     * @param replace 要替换的字符串
     * @return 替换完的字符串
     */
    public static String replaceString(int start, int end, String str, String replace) {
        StringBuilder sb = new StringBuilder(str);
        return sb.replace(start, end, replace).toString();
    }

    public static String test() {
        Charsets charsets = new Charsets();
        return "";
    }
}
