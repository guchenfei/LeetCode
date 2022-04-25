package leetcode.editor.cn;
//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: 
//(1) 每次只能移动一个盘子; 
//(2) 盘子只能从柱子顶端滑出移到下一根柱子; 
//(3) 盘子只能叠在比它大的盘子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
// Related Topics 递归 数组 
// 👍 152 👎 0

import java.util.List;

public class HanotaLcci {
    public static void main(String[] args) {
        Solution solution = new HanotaLcci().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //该问题是经典的递归调用问题

        /**
         * 解决递归调用问题的时候,具有三要素
         * 1.终止条件
         * 2.递归方法体
         * 3.递归关系条件
         * 思考此问题,
         * 整体思路:需要将每个柱子上n-1个盘子移动到中间柱子，将其最后一个盘子放到目标柱子
         * 然而具有n-1盘子的柱子同样可以视为将其上n-2个盘子移动到另一柱子，最后一个盘子移动到目标柱子
         * 以此类推,通过确认递归方法体,不断递归最终实现该场景目标
         */
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            movePlate(A.size(),A,B,C);
        }


        private void movePlate(int size,List<Integer> waitMovePlates,List<Integer> internalPlates,List<Integer> targetPlates) {
            if (size == 1){
                targetPlates.add(waitMovePlates.remove(waitMovePlates.size()-1));
                return;
            }

            movePlate(size-1,waitMovePlates,targetPlates,internalPlates);
            targetPlates.add(waitMovePlates.remove(waitMovePlates.size()-1));
            movePlate(size-1,internalPlates,waitMovePlates,targetPlates);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}