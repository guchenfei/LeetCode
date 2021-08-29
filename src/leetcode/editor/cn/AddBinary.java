//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 
// 👍 650 👎 0

package leetcode.editor.cn;

public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        System.out.println(solution.addBinary("1010", "1011"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public String addBinary(String a, String b) {
            ListNode aNode = covertListNode(a);
            ListNode bNode = covertListNode(b);
            ListNode resultNode = new ListNode(-1);

            ListNode aCurr = aNode;
            ListNode bCurr = bNode;
            ListNode resultCurr = resultNode;

            int carry = 0;
            while (aCurr != null || bCurr != null) {
                int x = aCurr != null ? aCurr.val : 0;
                int y = bCurr != null ? bCurr.val : 0;
                int sum = x + y + carry;// 1 1 1
                resultCurr.next = new ListNode(sum % 2);
                resultCurr = resultCurr.next;
                carry = sum / 2;
                aCurr = aCurr == null ? aCurr : aCurr.next;
                bCurr = bCurr == null ? bCurr : bCurr.next;
            }

            if (carry > 0) {
                resultCurr.next = new ListNode(carry);
            }

            //逆序遍历拼接
            resultNode = resultNode.next;
            StringBuilder sb = new StringBuilder();
            while (resultNode != null) {
                sb.append(resultNode.val);
                resultNode = resultNode.next;
            }
            return sb.reverse().toString();
        }

        private ListNode covertListNode(String s) {
            char[] chars = s.toCharArray();
            ListNode node = new ListNode(-1);
            ListNode curr = node;
            for (int i = chars.length - 1; i >= 0; i--) {
                curr.next = new ListNode(chars[i] == '1' ? 1 : 0);
                curr = curr.next;
            }
            return node.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

