package com.china.bosh.mylibrary.leetcode;

import com.china.bosh.mylibrary.entity.TestGson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        public int val;
        public ListNode next;

        public ListNode(int x) {
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
                if (mov == 0) {
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

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。
     * <p>
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 示例 :
     * <p>
     * 给定这个链表：1->2->3->4->5
     * <p>
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * <p>
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * <p>
     * 说明 :
     * <p>
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode curHead = head;
        boolean firstReverse = true;
        ListNode last = head;
        do {
            ListNode nextHead = getReverseLast(curHead, k);
            if (nextHead == null) {
                break;
            } else {
                nextHead = nextHead.next;
            }
            ListNode indexAfter = curHead.next;
            ListNode index = curHead;
            ListNode indexBefore = nextHead;
            for (int i = 0; i < k; i++) {
                index.next = indexBefore;
                if (i < k - 1) {
                    indexBefore = index;
                    index = indexAfter;
                    indexAfter = indexAfter.next;
                }
            }
            if (firstReverse) {
                head = index;
                firstReverse = false;
                last = curHead;
            } else {
                last.next = index;
                last = curHead;
            }
            curHead = nextHead;
        } while (true);
        return head;
    }

    private ListNode getReverseLast(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int i = 1;
        while (head.next != null && i < k) {
            head = head.next;
            i++;
        }
        if (i < k) {
            return null;
        } else {
            return head;
        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String longestPalindrome(String s) {
        if ("".equals(s)) {
            return "";
        }
        String res = s.substring(0, 1);
        int a, b;
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            a = i;
            b = i;
            while (true) {
                a++;
                b--;
                if (a >= len || b < 0) {
                    break;
                }
                if (s.charAt(a) == s.charAt(b)) {
                    if (a - b + 1 > res.length()) {
                        res = s.substring(b, a + 1);
                    }
                } else {
                    break;
                }
            }
            a = i + 1;
            b = i;
            if (a >= len || s.charAt(a) != s.charAt(b)) {
                continue;
            }
            if (2 > res.length()) {
                res = s.substring(b, a + 1);
            }
            while (true) {
                a++;
                b--;
                if (a >= len || b < 0) {
                    break;
                }
                if (s.charAt(a) == s.charAt(b)) {
                    if (a - b + 1 > res.length()) {
                        res = s.substring(b, a + 1);
                    }
                } else {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 编写一个程序，通过已填充的空格来解决数独问题。
     * <p>
     * 一个数独的解法需遵循如下规则：
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     * <p>
     * Note:
     * <p>
     * 给定的数独序列只包含数字 1-9 和字符 '.' 。
     * 你可以假设给定的数独只有唯一解。
     * 给定数独永远是 9x9 形式的。
     */
    private class SudokuStack {
        char[][] board;
        SudokuStack next;
    }

    public void solveSudoku(char[][] board) {
        solve(board);
        printSodu(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isSoduOk(board, i, j, k)) {
                            board[i][j] = k;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSoduOk(char[][] board, int i, int j, char tmp) {
        for (int k = 0; k < 9; k++) {
            if (k == i) {
                continue;
            }
            if (tmp == board[k][j]) {
                return false;
            }
        }
        for (int k = 0; k < 9; k++) {
            if (k == j) {
                continue;
            }
            if (tmp == board[i][k]) {
                return false;
            }
        }
        for (int k = i / 3 * 3; k < i / 3 * 3 + 3; k++) {
            for (int l = j / 3 * 3; l < j / 3 * 3 + 3; l++) {
                if (k == i && l == j) {
                    continue;
                }
                if (tmp == board[k][l]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printSodu(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        List<Character> list = new ArrayList<>();
        int size;
        char tmp;
        for (int i = 0; i < len; i++) {
            tmp = s.charAt(i);
            if (isLeft(tmp)) {
                list.add(tmp);
            } else {
                size = list.size();
                if (size == 0) {
                    return false;
                }
                if (list.get(size - 1) == getLeft(tmp)) {
                    list.remove(size - 1);
                } else {
                    return false;
                }
            }
        }
        if (list.size() != 0) {
            return false;
        }
        return true;
    }

    private char getLeft(char value) {
        if (value == ')') return '(';
        if (value == '}') return '{';
        if (value == ']') return '[';
        return ' ';
    }

    private boolean isLeft(char value) {
        return value == '(' || value == '{' || value == '[';
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        if (len < 3) {
            return list;
        }
        Arrays.sort(nums);
        int j, k;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            j = i + 1;
            k = len - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list1 = Arrays.asList(nums[i], nums[j], nums[k]);
                    list.add(list1);
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return list;
    }

    /**
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     * <p>
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     * <p>
     * 示例 1:
     * <p>
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     * <p>
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     * 提示：
     * <p>
     * 0 <= A.length <= 40000
     * 0 <= A[i] < 40000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        int count = 0;
        Arrays.sort(A);
        int index = -1;
        int j = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i - 1]) {
                continue;
            }
            if (index < A[i]) {
                index = A[i];
                j = i;
            }
            index++;
            while (true) {
                if (j == A.length) {
                    break;
                }
                if (index > A[j]) {
                    j++;
                } else if (index == A[j]) {
                    index++;
                } else {
                    if (A[j] - A[j - 1] == 1) {
                        index += 2;
                        j++;
                    } else {
                        break;
                    }
                }
            }
            count += index - A[i];
        }
        return count;
    }

    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     * <p>
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     *  
     * <p>
     * 提示：
     * <p>
     * 给定链表的结点数介于 1 和 100 之间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode res = head;
        boolean value = true;
        while (head.next != null) {
            head = head.next;
            if (value) {
                res = res.next;
                value = false;
            } else {
                value = true;
            }
        }
        return res;
    }

    /**
     * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
     * <p>
     * 注意：本题相对原题稍作改动
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入： [1,2,3,1]
     * 输出： 4
     * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
     * 示例 2：
     * <p>
     * 输入： [2,7,9,3,1]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
     * 示例 3：
     * <p>
     * 输入： [2,1,4,5,3,1,1,3]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/the-masseuse-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int message(int[] nums) {
        int res = 0;
        int lastRes = 0;
        boolean isBord = false;
        int i = 0;
        int len = nums.length;
        while (i < len) {
            if (isBord) {
                if (lastRes + nums[i] > res) {
                    int tmp = res;
                    res = lastRes + nums[i];
                    lastRes = tmp;
                    isBord = true;
                } else {
                    isBord = false;
                }
            } else {
                lastRes = res;
                res += nums[i];
                isBord = true;
            }
            i++;
        }
        return res;
    }

    /**
     * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
     * <p>
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     * <p>
     * 请你返回最终形体的表面积。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[[2]]
     * 输出：10
     * 示例 2：
     * <p>
     * 输入：[[1,2],[3,4]]
     * 输出：34
     * 示例 3：
     * <p>
     * 输入：[[1,0],[0,2]]
     * 输出：16
     * 示例 4：
     * <p>
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：32
     * 示例 5：
     * <p>
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：46
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surface-area-of-3d-shapes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int countCube = 0;
        int countRepeat = 0;
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                countCube += grid[i][j];
                if (grid[i][j] > 0) {
                    if (i + 1 < len && grid[i + 1][j] > 0) {
                        countRepeat += Math.min(grid[i][j], grid[i + 1][j]);
                    }
                    if (j + 1 < len && grid[i][j + 1] > 0) {
                        countRepeat += Math.min(grid[i][j], grid[i][j + 1]);
                    }
                    countRepeat += grid[i][j] - 1;
                }
            }
        }
        return countCube * 6 - countRepeat * 2;
    }

    /**
     * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     * <p>
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     * <p>
     * 返回车能够在一次移动中捕获到的卒的数量。
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释：
     * 在本例中，车能够捕获所有的卒。
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：0
     * 解释：
     * 象阻止了车捕获任何卒。
     * 示例 3：
     * <p>
     * <p>
     * <p>
     * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释：
     * 车可以捕获位置 b5，d6 和 f5 的卒。
     *  
     * <p>
     * 提示：
     * <p>
     * board.length == board[i].length == 8
     * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
     * 只有一个格子上存在 board[i][j] == 'R'
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param board
     * @return
     */
    public int numRookCaptures(char[][] board) {
        int count = 0;
        int i, j = 0, x, y;
        for (i = 0; i < board.length; i++) {
            boolean find = false;
            for (j = 0; j < board.length; j++) {
                if (board[i][j] == 'R') {
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        x = i;
        y = j;
        int dir = 0;
        for (int m = 1; ; m++) {
            if ((dir & 1) == 0) {
                if (y - m >= 0) {
                    if (board[x][y - m] == 'p') {
                        count++;
                        dir |= 1;
                    }
                    if (board[x][y - m] == 'B') {
                        dir |= 1;
                    }
                } else {
                    dir |= 1;
                }
            }
            if ((dir & 2) == 0) {
                if (y + m < board.length) {
                    if (board[x][y + m] == 'p') {
                        count++;
                        dir |= 2;
                    }
                    if (board[x][y + m] == 'B') {
                        dir |= 2;
                    }
                } else {
                    dir |= 2;
                }
            }
            if ((dir & 4) == 0) {
                if (x - m >= 0) {
                    if (board[x - m][y] == 'p') {
                        count++;
                        dir |= 4;
                    }
                    if (board[x - m][y] == 'B') {
                        dir |= 4;
                    }
                } else {
                    dir |= 4;
                }
            }
            if ((dir & 8) == 0) {
                if (x + m < board.length) {
                    if (board[x + m][y] == 'p') {
                        count++;
                        dir |= 8;
                    }
                    if (board[x + m][y] == 'B') {
                        dir |= 8;
                    }
                } else {
                    dir |= 8;
                }
            }
            if ((dir & 15) == 15) {
                break;
            }
        }
        return count;
    }

    /**
     * 给定一副牌，每张牌上都写着一个整数。
     * <p>
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     * <p>
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     * 示例 2：
     * <p>
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 3：
     * <p>
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 4：
     * <p>
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     * 示例 5：
     * <p>
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     * <p>
     * 提示：
     * <p>
     * 1 <= deck.length <= 10000
     * 0 <= deck[i] < 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean hasGroupsSizeX(int[] deck) {
        boolean res = false;
        if (deck.length < 2) {
            return false;
        }
        Arrays.sort(deck);
        List<Integer> xList = new ArrayList<>();
        for (int i = 2; i <= deck.length; i++) {
            if (deck.length % i == 0) {
                xList.add(i);
            }
        }
        xList.add(deck.length);
        for (int i = 0; i < xList.size(); i++) {
            int size = xList.get(i);
            res = true;
            for (int j = 0; j < deck.length; j += size) {
                int tmp = deck[j];
                for (int k = 1; k < size; k++) {
                    if (tmp != deck[j + k]) {
                        res = false;
                        break;
                    }
                }
                if (!res) {
                    break;
                }
            }
            if (res) {
                break;
            }
        }
        return res;
    }

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * <p>
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     * <p>
     * 输入: n = 10, m = 17
     * 输出: 2
     *  
     * <p>
     * 限制：
     * <p>
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    private int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * <p>
     * <p>
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int max = 0;
        left[0] = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > max) {
                max = height[i - 1];
            }
            left[i] = max;
        }
        max = 0;
        right[height.length - 1] = max;
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i + 1] > max) {
                max = height[i + 1];
            }
            right[i] = max;
        }
        int count = 0;
        int min = 0;
        for (int i = 0; i < height.length; i++) {
            min = Math.min(left[i], right[i]);
            if (min > height[i]) {
                count += Math.min(left[i], right[i]) - height[i];
            }
        }
        return count;
    }

    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * <p>
     * 不占用额外内存空间能否做到？
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * 示例 2:
     * <p>
     * 给定 matrix =
     * [
     * [ 5, 1, 9,11],
     * [ 2, 4, 8,10],
     * [13, 3, 6, 7],
     * [15,14,12,16]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [15,13, 2, 5],
     * [14, 3, 4, 1],
     * [12, 6, 8, 9],
     * [16, 7,10,11]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int tmp;
        int count = 0;
        for (int i = 0; i < length / 2; i++) {
            for (int j = count; j < length - count - 1; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length - j - 1][i] = matrix[length - 1 - i][length - j - 1];
                matrix[length - 1 - i][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = tmp;
            }
            count++;
        }
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 1：
     * <p>
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * 提示：
     * <p>
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        int count = 0;
        boolean[][] tmp = new boolean[m][n];
        tmp[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!tmp[i][j]) {
                    continue;
                }
                if (sumInt(i) + sumInt(j) <= k) {
                    count++;
                    if (i+1<m) {
                        tmp[i+1][j] = true;
                    }
                    if (j +1 < n) {
                        tmp[i][j+1] = true;
                    }
                }
            }
        }
        return count;
    }

    private int sumInt(int x) {
        int tmp = 0;
        while (x >= 10) {
            tmp += x % 10;
            x = (x - x % 10) / 10;
        }
        tmp += x;
        return tmp;
    }
}
