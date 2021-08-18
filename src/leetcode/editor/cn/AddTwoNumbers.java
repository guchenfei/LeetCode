//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6547 👎 0

package leetcode.editor.cn;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        int[] arr1 = {2, 4, 3};
        int[] arr2 = {5, 6, 4};
        ListNode l1 = new AddTwoNumbers().new ListNode(-1);
        ListNode l2 = new AddTwoNumbers().new ListNode(-1);
        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        for (int i = 0; i < arr1.length; i++) {
            ListNode node1 = new AddTwoNumbers().new ListNode(arr1[i]);
            ListNode node2 = new AddTwoNumbers().new ListNode(arr2[i]);
            l1Cur.next = node1;
            l1Cur = l1Cur.next;
            l2Cur.next = node2;
            l2Cur = l2Cur.next;
        }
        ListNode result = solution.addTwoNumbers(l1.next, l2.next);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p = l1;
            ListNode q = l2;
            ListNode resultHead = new ListNode(-1);
            ListNode curr = resultHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = p != null ? p.val : 0;
                int y = q != null ? q.val : 0;
                int sum = x + y + carry;
                carry = sum / 10;
                int num = sum % 10;
                curr.next = new ListNode(num);
                curr = curr.next;
                p = p == null ? p : p.next;
                q = q == null ? q : q.next;
            }

            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return resultHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

