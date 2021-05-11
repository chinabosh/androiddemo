package com.bosh.module_mvvm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void convertByRulesTest() {
        System.out.println(convertByRulesTest("242884817", "2698375e1717ab9c"));
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
}