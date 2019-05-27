package com.china.bosh.demo.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    int[] prime = new int[100000000];
    int[] a = new int[100000000];
    private void init(){
        int i,j,len;
        len = 0;
        for(i = 2 ; i < 100000000; i++) {
            if(prime[i] == 0) {
                a[len++] = i;
            }
            for(j = 0; j < len && (a[j] * i < 100000000); j++){
                prime[a[j] * i] = 1;
                if(i % a[j] == 0) {
                    break;
                }
            }
        }
    }

    @Test
    public void test() throws Exception {
        System.out.println(System.currentTimeMillis());
        init();
        System.out.println(System.currentTimeMillis());
        int i =0;
        int[] count = new int[9];
        int index = 0;
        int tmp = 10;
        do {
            if(a[i] < tmp) {
                count[index] ++;
            } else {
                index++;
                tmp *= 10;
                count[index] ++;
            }
            i++;
        }while (a[i] != 0);
        for (int j =0 ; j < 9; j++) {
            System.out.println(count[j]);
        }
    }

    @Test
    public void test2() throws Exception {
        int[] tmp = new int[]{1,3,5,7,9};
        for(int i = 0; i < 5 ; i++){
            for (int j = i; j < 5; j++) {
                System.out.println(tmp[i] + " * " + tmp[j] + " = " + tmp[i] * tmp[j]);
            }
        }
    }
}