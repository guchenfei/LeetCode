package leetcode.editor.cn;
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 7420 👎 0

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        /**
         * 解题思路
         * 双指针+Hash算法
         * 目前我们采用Hash算法快速定位新字符是否已经存在当前子串
         * 1.定义hash算法,这里由于存放的字符串都是ASCII码前边的可以用int范围内表示,
         *      即：a -> 97简化hash算法直接作为index值,hash容器存入该字符出现于字符串中位置
         * 2.定义双指针
         *   start = 0     
         *   right = 1
         *   通过right指针不断遍历取出字符，根据hash算法查找是否存在
         *   （1）没有相同字符（即不存在该字符于当前字符串合法index）通过hash算法将字符位置存入
         *   （2）存在相同字符，通过记录的上次处于字符串位置，将start指针移动到该记录++
         *   通过每次的合法子串长度记录最大的子串长度
         */
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int length = s.length();
            if (length == 0) return 0;
            if (length < 2) return 1;
            int[] hashArray = new int[128];//该数据存放hash算法的内容
            int start = 0;
            int right = 0;
            int res = 0;//表示最大子串长度
            //初始化hashArray
            Arrays.fill(hashArray, -1);
            //tmmzuxt
            while (right < s.length()) {
                int hashCode = s.charAt(right);//简化hash算法
                if (hashArray[hashCode] != -1) {
                    //当前子串存在该字符
                    //start指针移动到上一次存在位置++,right指针字符信息存入hash
                    //这里我做一次历史记录重置操作
                    int lastIndex = hashArray[hashCode];
                    start = start > lastIndex ? start : ++lastIndex;
                }
                res = Math.max(res, right - start + 1);
                hashArray[hashCode] = right;
                right++;
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}