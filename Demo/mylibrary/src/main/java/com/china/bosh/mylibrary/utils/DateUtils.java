package com.china.bosh.mylibrary.utils;

import com.china.bosh.mylibrary.constant.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzq
 * @date 2020/7/6
 */
public class DateUtils {

    public static String getDateDaysAgo(int day) {
        Date date = new Date();
        date.setTime(date.getTime() - day * 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Constants.CURRENT_LOCALE);
        return sdf.format(date);
    }
}
