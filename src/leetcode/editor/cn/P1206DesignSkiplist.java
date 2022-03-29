package leetcode.editor.cn;
//不使用任何库函数，设计一个 跳表 。 
//
// 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思
//想与链表相似。 
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作： 
//
// 
//Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons 
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。 
//
// 了解更多 : https://en.wikipedia.org/wiki/Skip_list 
//
// 在本题中，你的设计应该要包含这些函数： 
//
// 
// bool search(int target) : 返回target是否存在于跳表中。 
// void add(int num): 插入一个元素到跳表。 
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。 
//
// 
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。 
//
// 
//
// 示例 1: 
//
// 
//输入
//["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase",
// "search"]
//[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
//输出
//[null, null, null, null, false, null, true, false, true, false]
//
//解释
//Skiplist skiplist = new Skiplist();
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
// 
//
// 
//
// 提示: 
//
// 
// 0 <= num, target <= 2 * 104 
// 调用search, add, erase操作次数不大于 5 * 104 
// 
// Related Topics 设计 链表 
// 👍 109 👎 0

import java.util.Random;

public class P1206DesignSkiplist {
    public static void main(String[] args) throws InterruptedException {
        Skiplist skiplist = new P1206DesignSkiplist().new Skiplist();
        for (int i = 0; i < 100000; i++) {
            int num = new Random().nextInt(10000);
            skiplist.add(num);
        }
        skiplist.toString();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Skiplist {
        private ListNode head;
        private int level;
        //记录原链表中元素个数
        private int length;

        public Skiplist() {
            head = new ListNode(-1);
            level = 1;
        }

        class ListNode {
            private int val;
            ListNode right;
            ListNode down;

            public ListNode(int val) {
                this.val = val;
                this.right = null;
                this.down = null;
            }

            public ListNode(int val, ListNode right, ListNode down) {
                this.val = val;
                this.right = right;
                this.down = down;
            }
        }

        public boolean search(int target) {
            ListNode node = head;
            while (node != null) {
                //遍历当前层,直到当前层末尾或者target < node.right.val
                while (node.right != null && node.right.val < target) {
                    node = node.right;
                }
                //此刻存在两种情况,
                // 1.node.right == null;//下移
                //2.node.right.val >= target
                if (node.right != null && node.right.val == target) {
                    return true;
                }
                node = node.down;
            }
            return false;
        }

        public void add(int num) {
            ListNode node = head;
            //该集合记录索引查找时候每次down的节点,用于后续插入新生成的索引做记录
            ListNode[] downNodes = new ListNode[level];
            //该索引记录downNodes位置
            int downIndex = 0;
            while (node != null) {
                while (node.right != null && node.right.val < num) {
                    node = node.right;
                }
                downNodes[downIndex++] = node;
                node = node.down;
            }
            node = downNodes[--downIndex];
            ListNode newNode = new ListNode(num, node.right, null);
            node.right = newNode;
            length++;
            //还差索引层生成
            getIndexNode(newNode, downNodes,downIndex);
        }

        /**
         *
         * @param newNode
         * @param downNodes
         * @param downIndex 表示记录最后一次下移节点保存的索引值(即原链表刚插入新元素的前一个节点存放位置)
         *                  我们可以根据该值进行当前存在的索引层创建索引节点
         */
        private void getIndexNode(ListNode newNode, ListNode[] downNodes, int downIndex) {
            //生成索引高度值,我们不能限定必须生成多高(多少层)的索引
            //我们先以原链表1/64为索引层高限制
            int expectIndexLevel =  length >> 6;
            //我们分两步建立索引层
            //1.建立已有的最高高度level索引,然后根据我们的期望超越层级高度继续建立新的索引层
            int currentLevelIndex = downIndex - 1;
            ListNode newIndexNode = newNode;
            while (level < expectIndexLevel) {
                int randomNum = new Random().nextInt(2);

                if (currentLevelIndex >= 0) {
                    //执行已有层内的索引生成
                    //randomNum == 1就执行创建索引
                    if (randomNum == 1) {
                        ListNode newNodeIndex = new ListNode(newIndexNode.val, downNodes[currentLevelIndex].right, newIndexNode);
                        downNodes[currentLevelIndex].right = newNodeIndex;
                        //一旦生成新node索引保存,以备下一层生成索引index指定
                        newIndexNode = newNodeIndex;
                    }
                    currentLevelIndex--;
                } else {
                    //增加新层索引生成
                    if (randomNum == 1) {
                        ListNode newHead = new ListNode(-1);
                        ListNode newNodeIndex = new ListNode(newIndexNode.val, null, newIndexNode);
                        newIndexNode = newNodeIndex;
                        newHead.right = newNodeIndex;
                        newHead.down = head;
                        head = newHead;
                        level++;
                    }
                }
            }
        }

        public boolean erase(int num) {
            boolean exist = false;
            ListNode node = head;
            while (node != null) {
                while (node.right != null && node.right.val < num) {
                    node = node.right;
                }

                ListNode right = node.right;
                if (right != null && right.val == num) {
                    node.right = right.right;
                    right.right = null;
                    exist = true;
                }
                node = node.down;
            }

            if (exist) {
                length--;
            }
            return exist;
        }

        @Override
        public String toString() {
            ListNode[] heads = new ListNode[level];
            ListNode headTmp = head;
            int index = level - 1;
            while (headTmp != null) {
                if (index >= 0) {
                    heads[index] = headTmp;
                }
                headTmp = headTmp.down;
                index--;
            }

            for (int i = heads.length - 1; i >= 0; i--) {
                ListNode node = heads[i];
                while (node.right != null) {
                    System.out.print("          " + node.val + "            ");
                    node = node.right;
                }
                System.out.println("");
            }

            return "";
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */


/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//leetcode submit region end(Prohibit modification and deletion)

}