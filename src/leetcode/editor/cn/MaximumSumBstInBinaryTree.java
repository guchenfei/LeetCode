//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。 
//
// 二叉搜索树的定义如下： 
//
// 
// 任意节点的左子树中的键值都 小于 此节点的键值。 
// 任意节点的右子树中的键值都 大于 此节点的键值。 
// 任意节点的左子树和右子树都是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
// 
//
// 示例 3： 
//
// 
//输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
// 
//
// 示例 4： 
//
// 
//输入：root = [2,1,3]
//输出：6
// 
//
// 示例 5： 
//
// 
//输入：root = [5,4,8,3,null,6,3]
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 每棵树有 1 到 40000 个节点。 
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 动态规划 二叉树 👍 100 👎 0

package leetcode.editor.cn;

public class MaximumSumBstInBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MaximumSumBstInBinaryTree().new Solution();
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode leftNode, TreeNode right) {
            this.val = val;
            this.left = leftNode;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    /**
     * 该类是寻找二叉树中最大搜索树的键值和结果
     */
    class Result {
        /**
         * 二叉树最小节点值
         */
        public int min;
        /**
         * 二叉树最大节点值
         */
        public int max;
        /**
         * 该二叉树最大键值和
         */
        public int maxSumBST;
        /**
         * 该二叉树是否是BST
         */
        public boolean isBST;

        public Result(int min, int max, int maxSumBST, boolean isBST) {
            this.min = min;
            this.max = max;
            this.maxSumBST = maxSumBST;
            this.isBST = isBST;
        }
    }

    class Solution {
        public int maxSumBST(TreeNode root) {
            int[] maxSumBST = new int[1];
            maxSumBST2Body(root, maxSumBST);
            return maxSumBST[0];
        }

        /**
         * @param root 待查找的最大键值和二叉树
         * @param max  当前最大键值和
         * @return Result
         */
        public Result maxSumBST2Body(TreeNode root, int[] max) {
            if (root == null) {
                return null;
            }

            Result leftResult = maxSumBST2Body(root.left, max);
            Result rightResult = maxSumBST2Body(root.right, max);
            if (leftResult != null && (!leftResult.isBST || leftResult.max >= root.val)) {
                return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
            }
            if (rightResult != null && (!rightResult.isBST || rightResult.min <= root.val)) {
                return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
            }

            //剩余情况就是属于BST
            int minNodeVal = leftResult == null ? root.val : leftResult.min;
            int maxNodeVal = rightResult == null ? root.val : rightResult.max;
            int num = 0;
            if (leftResult != null) {
                num += leftResult.maxSumBST;
            }
            if (rightResult != null) {
                num += rightResult.maxSumBST;
            }
            num += root.val;
            max[0] = Math.max(max[0], num);
            return new Result(minNodeVal, maxNodeVal, num, true);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

