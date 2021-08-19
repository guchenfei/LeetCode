//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 988 👎 0

package leetcode.editor.cn;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        Solution solution = new MinStack().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * initialize your data structure here.
         */
        Stack<Long> stack = new Stack<Long>();
        private long min;

        /**
         * initialize your data structure here.
         */
        public Solution() {

        }

        public void push(int val) {
            if (stack.isEmpty()) {
                min = val;
                stack.push(0L);
            } else {
                long compare = val - min;
                min = compare < 0 ? val : min;
                stack.push(compare);
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                min = stack.peek() < 0 ? (min - stack.peek()) : min;
                stack.pop();
            }
        }

        public long top() {
            if (!stack.isEmpty()){
                long compare = stack.peek();
                return compare < 0 ? min : (stack.peek() + min);
            }else {
                return -1L;
            }
        }

        public long getMin() {
            return min;
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}

