package leetcode.editor.cn;
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
// Related Topics 数组 堆（优先队列） 👍 414 👎 0

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P373FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        Solution solution = new P373FindKPairsWithSmallestSums().new Solution();
        List<List<Integer>> lists = solution.kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 定义一种分别从num1,num2中取出一个数字的数据结构(注意此处记录的是Index)
         */
        class Pair {
            private int xIndex;
            private int yIndex;

            Pair(int xIndex, int yIndex) {
                this.xIndex = xIndex;
                this.yIndex = yIndex;
            }
        }

//        /**
//         * 法一:最大堆实现(时间耗时大,提交没有通过)
//         * 思路:建立一个为k的最大堆,根据最大堆的性质,根节点必定是最大的,并且我们知道最小的k个和组合必定位于k*k范围内
//         * 遍历k^2范围内的组合大于堆顶抛弃,小于堆顶入堆,这样留下来的必定是最小的k个
//         *
//         * @param nums1 数组1
//         * @param nums2 数组2
//         * @param k     k对最小的组合
//         * @return 满足条件组合
//         */
//        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//            if (nums1.length == 0 || nums2.length == 0) {
//                return null;
//            }
//            //默认堆的数据结构是最小堆,我们自定义实现最大堆,同时自定义实现比较内容
//            PriorityQueue<ArrayList<Integer>> maxHeap = new PriorityQueue<>(11, new Comparator<ArrayList<Integer>>() {
//                @Override
//                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
//                    return o2.get(0) + o2.get(1) - (o1.get(0) + o1.get(1));
//                }
//            });
//
//            //我们先以num1最小值和num2前k个进行匹配组合,并且将其分别入堆
//            //
//            for (int i = 0; i < Math.min(nums1.length, k); i++) {
//                for (int j = 0;j<Math.min(nums2.length,k);j++){
//                    ArrayList<Integer> element = new ArrayList<>();
//                    element.add(nums1[i]);
//                    element.add(nums2[j]);
//                    if (maxHeap.size() != k || nums1[i]+ nums2[j] < maxHeap.peek().get(0) + maxHeap.peek().get(1)){
//                      if (maxHeap.size() == k){
//                          maxHeap.poll();
//                      }
//                      maxHeap.add(element);
//                  }
//                }
//            }
//
//            return new ArrayList<>(maxHeap);
//        }

        /**
         * 法二:用最小堆来解决
         * 思路:由于两个数组是升序第一个最小组合一定【0,,0】
         * 1.先将任意横着第一行或者竖着第一列放入最小堆中,然后取出最小堆根(满足条件的k个组合中一个),根据根获取下一个元素(这个元素有可能成为下一个根)
         * 其实出现上边策略根本原因是观测两个升序数组组合,我们发现最小的和组合一定处于左上角(将组合看成表格),并且每当我们弹出最小堆根节点,它的下一个附近节点有可能成为下一个最小的
         * 因此入堆
         * @param nums1 数组1
         * @param nums2 数组2
         * @param k     k对最小的组合
         * @return 满足条件组合
         */
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            if (nums1.length == 0 || nums2.length == 0) {
                return null;
            }
            //默认堆的数据结构是最小堆,我们自定义实现最小堆
            PriorityQueue<Pair> minHeap = new PriorityQueue<>(11, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return nums1[o1.xIndex] + nums2[o1.yIndex]- (nums1[o2.xIndex] + nums2[o2.yIndex]);
                }
            });

            //首先将横或者竖的一列k个组合入堆
            //此处以竖的一列为例
            for (int i = 0; i < Math.min(nums1.length,k);i++){
                Pair pair = new Pair(i, 0);
                minHeap.add(pair);
            }

            List<List<Integer>> result = new ArrayList<>();
            while (k>0&&!minHeap.isEmpty()){
                Pair pair = minHeap.poll();
                ArrayList<Integer> element = new ArrayList<>();
                element.add(nums1[pair.xIndex]);
                element.add(nums2[pair.yIndex]);
                result.add(element);
                int nextYIndex = ++pair.yIndex;
                if (nextYIndex < nums2.length){
                    minHeap.add(new Pair(pair.xIndex, nextYIndex));
                }
                k--;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}