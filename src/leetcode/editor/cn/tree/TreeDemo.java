package leetcode.editor.cn.tree;

import java.sql.ResultSet;
import java.util.*;

/**
 * 该类主要是关于遍历树相关的方法,以及在遍历中求相关属性
 */
public class TreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        TreeNode treeNode = new TreeDemo().createTree(0,arr);
    }

    /**
     * 通过BFS方式求二叉树最大深度 队列+树
     * 遍历思想:
     * 通过记录每层节点数+遍历,每当该层遍历完后树深度+1
     *
     * @return depth
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        //将遍历树的每一层节点放入队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            //记录当前层node数
            int currentLevelNodeCounts = queue.size();
            //将当前层节点出队,子节点入队,并且记录该层节点数
            while (currentLevelNodeCounts > 0) {
                TreeNode node = queue.poll();
                if (node.leftNode != null) {
                    queue.offer(node.leftNode);
                }
                if (node.rightNode != null) {
                    queue.offer(node.rightNode);
                }
                currentLevelNodeCounts--;
            }
            depth++;
        }
        return depth;
    }


    /**
     * 通过DFS方式遍历树来求深度
     * 采用递归方式
     */
    public int maxDepthDFS(TreeNode root) {
        if (root == null) return 0;
        //我们可以将一棵树的最大深度抽象成
        //当前树的最大深度为max(leftTreeMaxDepth+RightTreeMaxDepth) + 1
        int leftTreeMaxDepth = maxDepthDFS(root.leftNode);
        int rightTreeMaxDepth = maxDepthDFS(root.rightNode);
        return 1 + Math.max(leftTreeMaxDepth, rightTreeMaxDepth);
    }

    /**
     * 二叉树中序遍历(迭代)
     */
    public List<Integer> inOrderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.leftNode;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.rightNode;
        }
        return result;
    }


    /**
     * 二叉树中序遍历(递归)
     */
    public List<Integer> inOrderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal2Body(root, result);
        return result;
    }

    /**
     * 这个是迭代体,我们可以将中序遍历理解成左子树的中序遍历+根节点+右子树的中序遍历
     *
     * @param root   待实现的中序遍历树
     * @param result 中序遍历结果
     */
    private void inOrderTraversal2Body(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inOrderTraversal2Body(root.leftNode, result);
        result.add(root.val);
        inOrderTraversal2Body(root.rightNode, result);
    }

    /**
     * 中序遍历更优解,莫里斯遍历
     */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode exPoint = null;
        while (root != null) {
            //找到左子树中序遍历最后一个节点
            if (root.leftNode != null) {
                exPoint = root.leftNode;
                while (exPoint.rightNode != null && exPoint.rightNode != root) {
                    exPoint = exPoint.rightNode;
                }
                if (exPoint.rightNode == null) {
                    exPoint.rightNode = root;
                    root = root.leftNode;
                } else {
                    //说明左子树已经遍历完了
                    res.add(root.val);
                    exPoint.rightNode = null;
                    root = root.rightNode;
                }
            } else {
                res.add(root.val);
                root = root.rightNode;
            }
        }
        return res;
    }

    /**
     * 该方法是将一个带有值的节点插入到二叉搜索树中
     * 二叉搜索树的特性:每一个节点都保持其左节点值小于根节点,右子树节点值大于根节点
     *
     * @param root 待插入节点的树根节点
     * @param val  待插入节点的值
     * @return 插入节点后的二叉树
     */
    public TreeNode insertSearchTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (val < root.val) {
            //将该节点插入到左子树
            root.leftNode = insertSearchTree(root.leftNode, val);
        } else {
            //将该节点插入到右子树
            root.rightNode = insertSearchTree(root.rightNode, val);
        }
        return root;
    }

    /**
     * 该方法进行二叉搜索树查找某个节点(递归)
     *
     * @param root 待查询的二叉搜索树
     * @param val  待查询节点值
     * @return 查询结果
     */
    public boolean searchNodeInSearchTree1(TreeNode root, int val) {
        if (root == null) return false;
        boolean result = false;
        if (val < root.val) {
            result = searchNodeInSearchTree1(root.leftNode, val);
        } else if (val == root.val) {
            result = true;
        } else {
            result = searchNodeInSearchTree1(root.rightNode, val);
        }
        return result;
    }

    /**
     * 该方法进行二叉搜索树查找某个节点(迭代)
     *
     * @param root 待查询的二叉搜索树
     * @param val  待查询节点值
     * @return 查询结果
     */
    public boolean searchNodeInSearchTree2(TreeNode root, int val) {
        while (root != null) {
            if (val < root.val) {
                root = root.leftNode;
            } else if (val == root.val) {
                return true;
            } else {
                root = root.rightNode;
            }
        }
        return false;
    }


    /**
     * 该方法是判断一颗二叉树是否是二叉搜索树(BST),如果是则计算出该树的键值和
     * 首先:判断一颗树是否是二叉搜索树,其依据是所有的子树都是二叉搜索树
     * 我们通过中序遍历+数组[是否二叉搜索树flag,前一个节点值,此刻键值和]
     * 由于中序遍历可以通过遍历优势来判断与上一个节点大小关系进而判断是否是BST
     */
    public int[] isValidBST(TreeNode root){
        int[] result = new int[3];
        result[0] = 0;
        result[1] = Integer.MIN_VALUE;
        result[2] = 0;
        validBST(root,result);
        return result;
    }

    /**
     * 递归体验证二叉搜索树
     */
    public void validBST(TreeNode root,int[] result){
        if (root == null){
            result[0] = 1;
            return;
        }
        validBST(root.leftNode,result);
        if (root.val <= result[1]){
            result[0] = 0;
            result[2] = 0;
            return;
        }
        result[1] = root.val;
        result[2] += root.val;
        validBST(root.rightNode,result);
    }

    /**
     * 表示二叉树的最大键值和
     */
    private int maxSumBST = Integer.MIN_VALUE;
    /**
     * 该方法是计算一颗二叉树的最大键值和
     * 我们通过前序遍历来不断计算二叉树键值和
     * 注意此刻这颗二叉树可能不是二叉搜索树
     * 所以需要我们不断判断是否二叉搜索树并且计算该二叉搜索树和
     * 通过不断遍历(前序遍历)找到这个二叉树最大的键值和
     */
    public int maxSumBST(TreeNode root){
        if (root == null){
            maxSumBST = maxSumBST>0?maxSumBST:0;
            return maxSumBST;
        }
        //root不为空通过前序遍历不断验证求键值和
        int[] rootResult = isValidBST(root);
        if (rootResult[0] == 1){
            maxSumBST = maxSumBST>rootResult[2]?maxSumBST:rootResult[2];
        }

        maxSumBST(root.leftNode);
        maxSumBST(root.rightNode);
        return maxSumBST;
    }

    /**
     * 该类是寻找二叉树中最大搜索树的键值和结果
     */
    class Result {
        /**
         * 二叉树最小节点值
         */
        private int min;
        /**
         * 二叉树最大节点值
         */
        private int max;
        /**
         * 该二叉树最大键值和
         */
        private int maxSumBST;
        /**
         * 该二叉树是否是BST
         */
        private boolean isBST;

        public Result(int min, int max, int maxSumBST, boolean isBST) {
            this.min = min;
            this.max = max;
            this.maxSumBST = maxSumBST;
            this.isBST = isBST;
        }
    }

    /**
     * 上边我们通过遍历手段发现时间复杂度为n^2
     * 我们可否优化算法来找到二叉树最大键值和
     * 利用后序遍历,这种遍历方式已经知道左右子树情况
     * 所以我们可以通过一次遍历求出二叉树最大键值和
     */
    public int maxSumBST2(TreeNode root){
       int[] maxSumBST = new int[1];
       maxSumBST2Body(root,maxSumBST);
       return maxSumBST[0];
    }

    /**
     *
     * @param root 待查找的最大键值和二叉树
     * @param max 当前最大键值和
     * @return Result
     */
    public Result maxSumBST2Body(TreeNode root,int[] max){
        if (root == null){
            return null;
        }

        Result leftResult = maxSumBST2Body(root.leftNode,max);
        Result rightResult = maxSumBST2Body(root.rightNode,max);
        if (leftResult != null&&(!leftResult.isBST|| leftResult.max >= root.val)){
            return new Result(Integer.MIN_VALUE,Integer.MAX_VALUE,0,false);
        }
        if (rightResult != null&&(!rightResult.isBST|| rightResult.min <= root.val)){
            return new Result(Integer.MIN_VALUE,Integer.MAX_VALUE,0,false);
        }

        //剩余情况就是属于BST
        int minNodeVal = leftResult==null? root.val : leftResult.min;
        int maxNodeVal = rightResult==null? root.val : rightResult.max;
        int num = 0;
        if (leftResult!= null){
            num+= leftResult.maxSumBST;
        }
        if (rightResult!= null){
            num+= rightResult.maxSumBST;
        }
        num+= root.val;
        max[0] = Math.max(max[0],num);
        return new Result(minNodeVal,maxNodeVal,num,true);
    }

    /**
     * 构造完全二叉树,我们通过数组索引(begin 0)和完全二叉树性质对照存在如下关系
     * 1.完全二叉树左节点索引是根节点索引2倍+1
     * 2.完全二叉树右节点索引是根节点索引2倍+2
     * 通过这样规律将数组中的元素依次放入每层节点中形成完全二叉树
     *
     * @param index 数组元素开始索引
     * @param arr 待放置数组
     * @return 构成完全二叉树的TreeNode
     */
    public TreeNode createTree(int index,int[] arr){
        if(index >= arr.length){
            return null;
        }
        TreeNode root = new TreeNode(arr[index]);
        root.leftNode = createTree(index*2+1,arr);
        root.rightNode = createTree(index*2 +2,arr);
        return root;
    }
}
