package com.china.bosh.mylibrary.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
        b = str.getBytes(StandardCharsets.UTF_8);
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
        b = str.getBytes(StandardCharsets.UTF_8);
        if(b != null) {
            s = new String(Base64.decode(b, Base64.DEFAULT));
        }
        return s;
    }

    public static void encodeFile(String from, String to) {
        ByteArrayOutputStream os1 = new ByteArrayOutputStream();
        InputStream file1 = null;
        try {
            file1 = new FileInputStream(from);
            byte[] byteBuf = new byte[3 * 1024 * 1024];
            byte[] base64ByteBuf;
            int count1; //每次从文件中读取到的有效字节数
            while ((count1 = file1.read(byteBuf)) != -1) {
                if (count1 != byteBuf.length) {//如果有效字节数不为3*1000，则说明文件已经读到尾了，不够填充满byteBuf了
                    byte[] copy = Arrays.copyOf(byteBuf, count1); //从byteBuf中截取包含有效字节数的字节段
                    base64ByteBuf = Base64.encode(copy, Base64.DEFAULT); //对有效字节段进行编码
                } else {
                    base64ByteBuf = Base64.encode(byteBuf, Base64.DEFAULT);
                }
                FileUtils.writeFile(to, base64ByteBuf, true); // 将转换后的数据写入文件中，该方法会自动创建文件
                os1.flush();
            }
            file1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
