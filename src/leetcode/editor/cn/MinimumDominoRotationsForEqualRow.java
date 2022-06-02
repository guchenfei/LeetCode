//在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 
//该平铺的每一半上都有一个数字。） 
//
// 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。 
//
// 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。 
//
// 如果无法做到，返回 -1. 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
//输出：2
//解释：
//图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
//如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
// 
//
// 示例 2： 
//
// 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
//输出：-1
//解释：
//在这种情况下，不可能旋转多米诺牌使一行的值相等。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A[i], B[i] <= 6 
// 2 <= A.length == B.length <= 20000 
// 
// Related Topics 贪心 数组 👍 95 👎 0

package leetcode.editor.cn;

public class MinimumDominoRotationsForEqualRow {
    public static void main(String[] args) {
        Solution solution = new MinimumDominoRotationsForEqualRow().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDominoRotations(int[] tops, int[] bottoms) {
            int n = tops.length;
            //先考虑tops,bottoms 都为tops[0]情况,需要分别移动最小次数
            int minSwapCount = searchMinSwapCount(tops[0],tops,bottoms,n);
            //此刻我们有tops[0]=bottoms[0],该情况不用考虑bottoms[0]分别在两个数组相同成行情况
            //tops[0]!=bottoms[0],该情况分两种 1.bottoms[0]不能构成同行,没意义,不考虑 2.top[0]能成行,bottom[0]也能成行,效果和top[0]一样]
            if (minSwapCount!=-1 || tops[0]==bottoms[0]){
                return minSwapCount;
            }
            //此刻考虑Tops和Bottom都为Bottom[0]时候最小交换次数
            return searchMinSwapCount(bottoms[0],tops,bottoms,n);
        }


        /**
         * 查找x在A数组或者B数组中排成一行需要交换最小次数
         *
         * @param x 一行相同的目标数
         * @param A 数组A
         * @param B 数组B
         * @param n 数组长度
         * @return 最小交换次数(- 1不存在这种情况)
         */
        private int searchMinSwapCount(int x, int[] A, int[] B, int n) {
            int aNeedSwapCount = 0;
            int bNeedSwapCount = 0;
            for (int i = 0; i < n; i++) {
                if (A[i] != x && B[i] != x) {
                    return -1;
                } else if (A[i] != x) {
                    aNeedSwapCount++;
                } else if (B[i] != x) {
                    bNeedSwapCount++;
                }
            }
            return Math.min(aNeedSwapCount, bNeedSwapCount);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

