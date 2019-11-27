package com.china.bosh.mylibrary.utils;


import android.util.Log;

import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

/**
 * @author lzq
 * @date 2019-10-10
 */
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
        assertEquals("res", "this is example");
    }
}