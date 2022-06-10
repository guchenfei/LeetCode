package leetcode.editor.cn.tree;

import java.util.*;

/**
 * 该类主要是关于遍历树相关的方法,以及在遍历中求相关属性
 */
public class TreeDemo {
    public static void main(String[] args) {

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
        inOrderTraversal2Body(root,result);
        return result;
    }

    /**
     * 这个是迭代体,我们可以将中序遍历理解成左子树的中序遍历+根节点+右子树的中序遍历
     * @param root 待实现的中序遍历树
     * @param result 中序遍历结果
     */
    private void inOrderTraversal2Body(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inOrderTraversal2Body(root.leftNode,result);
        result.add(root.val);
        inOrderTraversal2Body(root.rightNode,result);
    }

    /**
     * 中序遍历更优解,莫里斯遍历
     */


}
