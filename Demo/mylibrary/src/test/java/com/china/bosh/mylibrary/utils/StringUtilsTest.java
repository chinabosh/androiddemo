package com.china.bosh.mylibrary.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lzq
 * @date 2019-10-11
 */
public class StringUtilsTest {

    @Test
    public void getNumFromString() {
        String res = StringUtils.getNumFromString("123dajlk203");
        Assert.assertEquals(res, "123203");
        res = StringUtils.getNumFromString("123 di0 2 da");
        Assert.assertEquals(res, "12302");
        res = StringUtils.getNumFromString("sdjfkl\1sf\nsdf2");
        Assert.assertEquals(res, "2");
    }

    @Test(timeout = 100)
    public void getCharacterPosition() {
        int count = StringUtils.getCharacterPosition("jasldjfoiwj", "j", 2);
        Assert.assertEquals(count, 5);
        count = StringUtils.getCharacterPosition("jasldjfoiwj", "j", 4);
        Assert.assertEquals(count, -1);
        count = StringUtils.getCharacterPosition("jasldjfoiwj", "q", 1);
        Assert.assertEquals(count, -1);
    }

    @Test
    public void replaceString() {
    }
}