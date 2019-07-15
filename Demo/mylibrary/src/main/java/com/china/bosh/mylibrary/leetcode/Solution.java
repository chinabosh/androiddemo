package com.china.bosh.mylibrary.leetcode;

/**
 * @author lzq
 * @date 2019/5/15
 */
public class Solution {
    public boolean isRobotBounded(String instructions) {
        final int NORTH = 0;
        final int EAST = 1;
        final int SOUTH = 2;
        final int WEST = 3;
        int direction = NORTH;

        int x = 0;
        int y = 0;
        do {
            for (int i = 0; i < instructions.length(); i++) {
                char tmp = instructions.charAt(i);
                if ('G' == tmp) {
                    switch (direction) {
                        case NORTH:
                            y++;
                            break;
                        case EAST:
                            x++;
                            break;
                        case SOUTH:
                            y--;
                            break;
                        case WEST:
                            x--;
                            break;
                        default:
                    }
                }
                if ('L' == tmp) {
                    direction = (direction + 3) % 4;
                }
                if ('R' == tmp) {
                    direction = (direction + 5) % 4;
                }
            }
        } while (direction != 0);
        return (x == 0 && y == 0);
    }

    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     *
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        char[] tmp = new char[S.length()];
        int index = S.length() - 1;
        for (int i = 0, len = S.length(); i < len; i++) {
            char charTmp = S.charAt(i);
            if (!Character.isLetter(charTmp)) {
                tmp[i] = charTmp;
                continue;
            }
            for (int j = index; j >= i; j--) {
                if (!Character.isLetter(S.charAt(j))) {
                    tmp[j] = S.charAt(j);
                    continue;
                }
                System.out.println("i:" + S.charAt(i) + ", j:" + S.charAt(j));
                tmp[i] = S.charAt(j);
                tmp[j] = S.charAt(i);
                index = j - 1;
                break;
            }
            if (i >= index) {
                break;
            }
        }
        return String.valueOf(tmp);
    }

    /**
     * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
     * <p>
     * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，而当 i >= 0 时 C[i+A.length] = C[i]）
     * <p>
     * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
     */
    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int curMax, max, curMin, min, sum;
        curMax = max = curMin = min = sum = A[0];
        for (int i = 1; i < A.length; i++) {
            sum += A[i];
            curMax = curMax > 0 ? curMax + A[i] : A[i];
            max = curMax > max ? curMax : max;
            curMin = curMin < 0 ? curMin + A[i] : A[i];
            min = curMin < min ? curMin : min;
        }
        if (max < 0)
            return max;
        return Math.max(sum - min, max);
    }

    public void testAddTwoNumbers() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode res = addTwoNumbers(l1, l2);
        do {
            System.out.print(res.val);
            res = res.next;
        } while (res != null);
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode tmpResult = result;
        int up = 0;
        do {
            if (l1 != null && l2 != null) {
                tmpResult.val = (l1.val + l2.val + up) % 10;
                up = (l1.val + l2.val + up) / 10;
            } else if (l1 == null) {
                tmpResult.val = (l2.val + up) % 10;
                up = (l2.val + up) / 10;
            } else {
                tmpResult.val = (l1.val + up) % 10;
                up = (l1.val + up) / 10;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 == null && l2 == null) {
                break;
            }
            tmpResult.next = new ListNode(0);
            tmpResult = tmpResult.next;
        } while (true);
        if (up != 0) {
            tmpResult.next = new ListNode(up);
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * @param s
     * @return 遇到重复的start跳到重复字符后面位置
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        int end = 0;
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        do {
            int index = s.substring(start, end).indexOf(s.substring(end, end + 1));
            if (index != -1) {
                start += index + 1;
            } else {
                if (max < end - start + 1) {
                    max = end - start + 1;
                }
            }
            end++;
        } while (end < length);
        return max;
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * @param nums1
     * @param nums2
     * @return 用index标记最大最小，每次首尾各移动一次，最后剩一个或两个即可求的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1, start2, end1, end2;
        double res = 0;
        if (nums1 != null) {
            if (nums1.length == 0) {
                start1 = 1;
                end1 = 0;
            } else if (nums1.length == 1) {
                start1 = 0;
                end1 = 0;
            } else {
                if (nums1[0] <= nums1[nums1.length - 1]) {
                    start1 = 0;
                    end1 = nums1.length - 1;
                } else {
                    start1 = nums1.length - 1;
                    end1 = 0;
                }
            }
        } else {
            start1 = 1;
            end1 = 0;
        }
        if (nums2 != null) {
            if (nums2.length == 0) {
                start2 = 1;
                end2 = 0;
            } else if (nums2.length == 1) {
                start2 = 0;
                end2 = 0;
            } else {
                if (nums2[0] <= nums2[nums2.length - 1]) {
                    start2 = 0;
                    end2 = nums2.length - 1;
                } else {
                    start2 = nums2.length - 1;
                    end2 = 0;
                }
            }
        } else {
            start2 = 1;
            end2 = 0;
        }
        do {
            if (start1 > end1 && start2 > end2) {
                break;
            }
            if (start1 > end1) {
                if (start2 - end2 == 0 || start2 - end2 == -1) {
                    res = (double) (nums2[start2] + nums2[end2]) / 2;
                    break;
                }
            } else if (start1 == end1) {
                if (start2 == end2) {
                    res = (double) (nums1[start1] + nums2[start2]) / 2;
                    break;
                } else if (start2 > end2) {
                    res = nums1[start1];
                    break;
                }
            } else {
                if (start1 + 1 == end1 && start2 > end2) {
                    res = (double) (nums1[start1] + nums1[end1]) / 2;
                    break;
                }
            }
            if (start1 <= end1 && start2 <= end2) {
                if (nums1[start1] < nums2[start2]) {
                    start1++;
                } else {
                    start2++;
                }
                if (nums1[end1] > nums2[end2]) {
                    end1--;
                } else {
                    end2--;
                }
            } else if (start1 > end1) {
                start2++;
                end2--;
            } else {
                start1++;
                end1--;
            }
        } while (true);
        return res;
    }

    /**
     * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符。
     * '*' 匹配零个或多个前面的元素。
     * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        return false;
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 例：
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * <p>
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuilder ans = new StringBuilder();
        int length = s.length();
        int mov, index;
        boolean one;
        for (int i = 0; i < numRows; i++) {
            index = i;
            one = false;
            while (index < length) {
                ans.append(s.charAt(index));
                if (one) {
                    mov = i * 2;
                } else {
                    mov = (numRows - 1 - i) * 2;
                }
                one = !one;
                if(mov == 0) {
                    mov = (numRows - 1) * 2;
                }
                if (mov == 0) {
                    mov = 1;
                }
                index += mov;
            }
        }
        return ans.toString();
    }
}
