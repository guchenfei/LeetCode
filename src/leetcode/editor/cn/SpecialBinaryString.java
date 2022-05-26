//特殊的二进制序列是具有以下两个性质的二进制序列： 
//
// 
// 0 的数量与 1 的数量相等。 
// 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。 
// 
//
// 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一
//个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。) 
//
// 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？ 
//
// 示例 1: 
//
// 
//输入: S = "11011000"
//输出: "11100100"
//解释:
//将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
//这是在进行若干次操作后按字典序排列最大的结果。
// 
//
// 说明: 
//
// 
// S 的长度不超过 50。 
// S 保证为一个满足上述定义的特殊 的二进制序列。 
// 
// Related Topics 递归 字符串 👍 82 👎 0

package leetcode.editor.cn;
public class SpecialBinaryString {
    public static void main(String[] args) {
        Solution solution = new SpecialBinaryString().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        String[] arr = new String[25];
        int index = 0;
        int start=0;
        int countOne =0;
        for (int i = 0;i<s.length();i++){
            countOne+=s.charAt(i) =='1'?1:-1;
            if (countOne == 0){
                String result = makeLargestSpecial(s.substring(start+1,i));
                arr[index++] = "1" + result + "0";
                start = i + 1;
            }
        }
        bubbleSort(arr,index);
        for (int i = index-1;i>=0;i--){
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public void bubbleSort(String numbers[],int length){
        for (int i = 0; i < length - 1; i++) {
            //第一层循环表示通过冒泡算法循环需要冒泡几趟(每冒泡一趟,得出一个该趟最大值)
            for (int j = 0; j <length - 1 - i; j++) {
                //第二层循环表明该趟冒泡长度,由于前边已经找到了较大值,
                //该次冒泡只需要在总长度-已经找到较大值=剩余长度的区间继续冒泡下一个较大值
                if (numbers[j].compareTo(numbers[j + 1]) > 0) {
                    //执行交换
                    String temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

