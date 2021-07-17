//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2876 👎 0

package leetcode.editor.cn;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        int[] test = {-127, 125, 7678, Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < test.length; i++) {
            int result = solution.reverse(test[i]);
            System.out.println("result " + result);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
                return 0;
            }
            int sign = x > 0 ? 1 : -1;
            x = x < 0 ? -x : x;
            String str = String.valueOf(x);
            char[] chars = str.toCharArray();
            //方案一：利用另一个数组进行倒置
//            int len = chars.length;
//            char[] newChars = new char[len];
//            for (int i = len - 1; i >= 0; i--) {
//                newChars[len - 1 - i] = chars[i];
//            }

            //方案二：利用一个数组进行内部首尾交换，分奇偶情况
            //startIndex >= endIndex交换结束，否则小于进行首尾交换
//            int startIndex = 0;
//            int endIndex = chars.length-1;
//
//            while (startIndex < endIndex){
//                char temp = chars[startIndex];
//                chars[startIndex] = chars[endIndex];
//                chars[endIndex] = temp;
//                startIndex++;
//                endIndex--;
//            }

            //方案三:用数学的方式进行高效计算
            int last = 0;
            int result = 0;
            while ((last = x % 10) != x) {
                result = result * 10 + last;
                x/=10;
            }
            if (last != 0){
                long re = (result * 10L) + last;
                if (re >Integer.MAX_VALUE||re<Integer.MIN_VALUE){
                    result=0;
                }else {
                    result = (int)re;
                }
            }

//            String s = String.valueOf(chars);
//            long aLong = Long.parseLong(s);
//            boolean b = aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE;
//            int result = b ? 0 : (int) aLong;
            return result * sign;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

