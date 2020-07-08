package com.china.bosh.mylibrary.utils;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author lzq
 * @date 2020-06-30
 */
@RunWith(AndroidJUnit4.class)
public class ToMd5UtilTest {

    @Test
    public void md5() {
        String res = ToMd5Util.md5("a");
        Log.e("Test", res);
        Assert.assertEquals(res, "0cc175b9c0f1b6a831c399e269772661");
    }
}