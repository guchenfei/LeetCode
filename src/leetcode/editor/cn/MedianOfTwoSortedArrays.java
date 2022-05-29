//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
// Related Topics 数组 二分查找 分治 👍 5481 👎 0

package leetcode.editor.cn;
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //我们通过采用二分查找方式最快速找到中位数分割线
        //通过中位数特点,中位数分割线左侧的数据量一定比右侧最多多一个(<=0)
        //为了最快找到,我们以数组量小的数组作为二分基础数组
        if (m > n){
            return findMedianSortedArrays(nums2,nums1);
        }
        //代表分割线可以放置的始末位置
        int p = 0;
        int q = m;
        //代表nums1 分割线放置位置
        int i = 0;
        //代表nums2 分割线放置位置
        int j = 0;
        while (p<=q){
            i = (p+q)/2;
            j = (m+n+1)/2 - i;
            //我们需要判断分割线左右数据大小
            //如果num1左侧最大值大于num2右侧最小值分割线应该左移查找范围为[p,i-1](注意左移越界)
            if (i!=0&&j!=n && nums1[i-1]>nums2[j]){
                q = i-1;
            }else if (i!=m&&j!=0&nums1[i]<nums2[j-1]){
                //如果num1右侧最小值小于num2左侧最大值查找范围于应该右移[i+1,q]
                p = i+1;
            }else {
                //分割线找到,终止循环查找
                break;
            }
        }

        //我们此刻取出通用的左侧最大值,备作为中位数
        int maxLeft = i==0?nums2[j-1]:(j==0?nums1[i-1]:Math.max(nums1[i-1],nums2[j-1]));
        //分奇偶,总量奇数则为maxLeft,否则(maxLeft+minRight)/2.0
        if ((m+n)%2!= 0){
            return maxLeft;
        }
        int minRight = i ==m? nums2[j] : (j ==n? nums1[i] : Math.min(nums1[i],nums2[j]));
        return (maxLeft+minRight)/2.0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

