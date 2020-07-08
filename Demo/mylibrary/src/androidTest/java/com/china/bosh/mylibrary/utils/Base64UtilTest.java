package com.china.bosh.mylibrary.utils;


import android.util.Log;

import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;


import static junit.framework.TestCase.assertEquals;

/**
 * @author lzq
 * @date 2019-10-10
 */
@RunWith(AndroidJUnit4.class)
public class Base64UtilTest {

    @Test
    public void encode() {
        String res = Base64Util.encode("this is a example");
        assertEquals(res, "dGhpcyBpcyBhIGV4YW1wbGU=");
        System.out.println("success");
    }

    @Test
    public void decode() {
        String res = Base64Util.decode("dGhpcyBpcyBhIGV4YW1wbGU=");
        assertEquals(res, "this is a example");
    }

    @Test
    public void encode1() {
    }

    @Test
    public void decode1() {
    }
}