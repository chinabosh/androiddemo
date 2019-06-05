package com.china.bosh.mylibrary;

import com.china.bosh.mylibrary.leetcode.Solution;

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
//        int[] a = new int[] {3, 5, 7, 11, 13, 17, 19, 23, 29};
//        for(int i = 0; i < a.length; i++) {
//            for(int j = i + 1; j < a.length; j++) {
//                System.out.print(a[i] + a[j] + " ");
//            }
//            System.out.println();
//        }
    }

    @Test
    public void test() throws Exception{
        Solution solution = new Solution();
        int which = 7;
        switch (which) {
            case 1: {
                boolean res = solution.isRobotBounded("GGLLGG");
                System.out.println(res);
            }
                break;
            case 2: {
                String res = solution.reverseOnlyLetters("Test1ng-Leet=code-Q!");
//        String res = solution.reverseOnlyLetters("dc-ba");
                System.out.println(res);
            }
                break;
            case 3:{
                //        int[] test = new int[]{9,7,-6,7,9,-10,5,9,0,-1};
                int[] test = new int[]{-5,3,5};
                int max = solution.maxSubarraySumCircular(test);
                System.out.println(max);
            }
            break;
            case 4:{
                solution.testAddTwoNumbers();
            }
            break;
            case 5:{
                System.out.println("ab".substring(0, 1));
                int num = solution.lengthOfLongestSubstring("");
                System.out.println(num);
            }
            break;
            case 6:{
                int[] nums1 = new int[]{1, 1};
                int[] nums2 = new int[]{1,1};
                double res = solution.findMedianSortedArrays(nums1, nums2);
                System.out.println(res);
            }
            case 7:{
                boolean value = solution.isMatch("asfjal", "as.jala");
                System.out.println(value);
            }
            break;
            default:
        }
    }
}