//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 10⁵ 
// -10⁹ <= nums1[i], nums2[i] <= 10⁹ 
// nums1 和 nums2 均为升序排列 
// 1 <= k <= 1000 
// 
// Related Topics 数组 堆（优先队列） 👍 413 👎 0

package leetcode.editor.cn;

import java.util.Comparator;
import java.util.List;

public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        Solution solution = new FindKPairsWithSmallestSums().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Pair {
            int x;
            int y;
            public Pair(int x,int y){
                this.x = x;
                this.y = y;
            }
        }

        class MySort implements Comparator<Pair>{
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.x + o1.y - o2.x-o2.y;
            }
        }

        //解法1:最大堆
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

