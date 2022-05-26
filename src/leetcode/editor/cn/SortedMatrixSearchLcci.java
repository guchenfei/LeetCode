//给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
// Related Topics 数组 二分查找 分治 矩阵 👍 42 👎 0

package leetcode.editor.cn;

public class SortedMatrixSearchLcci {
    public static void main(String[] args) {
        Solution solution = new SortedMatrixSearchLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            if (m == 0) {
                return false;
            }
            int n = matrix[0].length;
            if (n == 0) {
                return false;
            }
            return searchSubMatrix(matrix, target, 0, 0, m - 1, n - 1);
        }

        public boolean searchSubMatrix(int[][] matrix, int target, int startRow, int startColumn, int endRow, int endColumn) {
            /**
             * 递归结束条件
             * 1.元素个数小于1
             * 2.矩阵最小元素都比target大
             */
            if (startRow > endRow || startColumn > endColumn) {
                return false;
            }
            if (matrix[startRow][startColumn] > target) {
                return false;
            }
            //注意:以下场景都是建立在该矩阵最小元素都小于等于target
            /**
             * 函数主功能
             */
            int diagonal_length = Math.min(endRow - startRow + 1, endColumn - startColumn + 1);
            //我们取矩形最短边对应的正方形对角线
            for (int i = 0; i < diagonal_length; i++) {
                //如果在对角线上找到返回true
                if (matrix[startRow + i][startColumn + i] == target) {
                    return true;
                }
                //根据场景,最小值不等于target必定小于target,因此第一个值必定此处小于target
                //我们进行划分区域有两种情况
                //1.对角线最后一个值还是小于target(此时target可能在右上侧,特殊区域强行分区查找)
                //2.正常情况对角线下一个元素比当前值大
                if (i == diagonal_length - 1 || matrix[startRow + i + 1][startColumn + i + 1] > target) {
                    //此时找到了划分区域的分界,我们从左下和右上查找target
                    //右上
                    return searchSubMatrix(matrix, target, startRow, startColumn + i + 1, startRow + i, endColumn) || searchSubMatrix(matrix, target, startRow + i + 1, startColumn, endRow, startColumn + i);
                }
            }
            return false;
        }

        //法二
        public boolean searchMatrix1(int[][] matrix,int target){
            int m = matrix.length;
            if (m==0){
                return false;
            }
            int n = matrix[0].length;
            if (n==0){
                return false;
            }

            int currentRow = 0;
            int currentColumn = n-1;
            while (currentColumn>=0&&currentRow<m){
                if (matrix[currentRow][currentColumn]==target) {
                    return true;
                }
                if (matrix[currentRow][currentColumn] < target){
                    currentRow++;
                }else {
                    currentColumn--;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

