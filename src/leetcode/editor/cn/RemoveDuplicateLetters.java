//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小
// （要求不能打乱其他字符的相对位置）。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
// Related Topics 栈 贪心 字符串 单调栈 👍 750 👎 0

package leetcode.editor.cn;

import java.util.Stack;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicateLetters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            //我们采用栈+贪心算法
            //保存结果的栈
            Stack<Character> stack = new Stack<>();
            //首先遍历数组记录每个字符最后一次出现位置
            int[] lastAppearIndex = new int[26];
            //记录栈中是否存在某个字符
            boolean[] seen = new boolean[26];
            for (int i = 0; i < s.length(); i++) {
                lastAppearIndex[s.charAt(i) - 'a'] = i;
            }
            //开始将字符串中每个元素进行贪心比较
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                //如果当前字符在栈中未出现过,进行处理
                if (!seen[c - 'a']) {
                    //只有当当前栈非空并且当前字符字典序小于栈顶元素并且栈顶元素后续还会出现
                    //我们逐项向栈内排除这样栈内元素,直到不存在这样元素将当前元素入栈
                    while (!stack.isEmpty() && c < stack.peek() && lastAppearIndex[stack.peek() - 'a'] > i) {
                        seen[stack.pop() - 'a'] = false;
                    }
                    stack.push(c);
                    seen[c - 'a'] = true;
                }
            }

            String result = "";
            while (!stack.isEmpty()) {
                result = stack.pop() + result;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

