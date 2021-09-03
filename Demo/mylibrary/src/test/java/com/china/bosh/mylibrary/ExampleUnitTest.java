package com.china.bosh.mylibrary;

import com.china.bosh.mylibrary.entity.TestGson;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        int[] a = new int[] {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
//                             79, 83, 89, 97, 101};
//        for(int i = 0; i < a.length; i++) {
//            for(int j = i + 1; j < a.length; j++) {
//                System.out.print(a[i] + a[j] + " ");
//            }
//            System.out.println();
//        }
        //12,18,24,28,30
        Pattern pattern = Pattern.compile("\\{(" + "[a-zA-Z][a-zA-Z0-9_-]*" + ")\\}");
        Matcher matcher = pattern.matcher("appCommon.action?act=getSystemUpdate/a2/{id}/");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void convertByRulesTest() {
        System.out.println(convertByRulesTest("242884817", "2698375e1717ab9c"));
//        "216bc19e98737a75e1717ab9c"
    }

    public static String convertByRulesTest(String rule, String content){
        StringBuilder sb =new StringBuilder(content);
        int v = 1;
        for(int i = 0; i < rule.length(); i++){
            String jg = content.substring(content.length() - v, content.length() - v + 1);
            sb.insert(Integer.parseInt(rule.substring(i, i + 1)), jg);
            v++;
        }

        return sb.toString();
    }

    @Test
    public void gson_test() {
        Gson gson = new Gson();
        String json = "{\n" +
                "    \"abc\": \"123\",\n" +
                "    \"test\": null" +
                "}";
        TestGson testGson = gson.fromJson(json, TestGson.class);
        System.out.println(testGson.getTest());
//        char[] out = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
//        for (int i = 0; i < 9; i++) {
//            System.out.println((int) out[i]);
//        }
    }










    @Test
    public void test31() {
        for (long i = 1; i < Integer.MAX_VALUE; i += 2) {
            long tmp = i;
            do {
                if (tmp % 2 == 0) {
                    tmp = tmp >> 1;
                } else {
                    tmp = tmp * 3 + 1;
                }
                //                System.out.print(tmp + " ");
            } while (tmp > i);
        }
    }
}