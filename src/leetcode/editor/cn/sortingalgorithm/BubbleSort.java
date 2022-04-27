package leetcode.editor.cn.sortingalgorithm;

public class BubbleSort {
    public static void main(String[] args) {
        /**
         * 冒泡排序
         */

        //定义一个无序数组
        int[] numbers = new int[]{10, 7, 4, 5, 6, 8, 3, 9, 1, 2};
        for (int i = 0; i < numbers.length - 1; i++) {
            //第一层循环表示通过冒泡算法循环需要冒泡几趟(每冒泡一趟,得出一个该趟最大值)
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                //第二层循环表明该趟冒泡长度,由于前边已经找到了较大值,
                //该次冒泡只需要在总长度-已经找到较大值=剩余长度的区间继续冒泡下一个较大值
                if (numbers[j] > numbers[j + 1]) {
                    //执行交换
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        for (int number : numbers) {
            System.out.print(" " + number);
        }
    }
}
