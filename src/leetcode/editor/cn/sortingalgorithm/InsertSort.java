package leetcode.editor.cn.sortingalgorithm;

public class InsertSort {
    public static void main(String[] args) {
        /**
         * 插入排序
         * 该算法的思想是:从待排序中左边拿出一个数,从已排序末尾依次向前比对(已排序的由小到大),比自己大向后移动,继续向前比较
         * 直到遇见比自己小的前一个,将自己放到该处(特殊情况:如果比较到已排序第一个还是比自己大后移后将自己放到首位)
         */

        //定义一个无序数组
        int[] numbers = new int[]{10, 7, 4, 5, 6, 8, 3, 9, 1, 2};
        //双层循环
        //第一层循环表示需要几趟插入才能将一个数据排序好,第一个默认有序,当然是size-1趟(也就是从数组的第二个拿直到拿到末尾每个进行插入操作)
        for (int i = 1; i < numbers.length; i++) {
            //我们将拿到的待插入数据进行缓存
            int temp = numbers[i];
            for (int j = i - 1; j >= 0; j--) {
                //第二层循环是将待插入数据从拿数据的前一个位置依次向前比较,直到自己插入到合适位置
                if (numbers[j] > temp) {
                    //比自己大的让其向后移(注意特殊位置比到首位自己最小,index 0处放自己)
                    numbers[j + 1] = numbers[j];
                    if (j == 0) {
                        numbers[0] = temp;
                    }
                } else {
                    //比自己小,将自己放到比较位置后一位
                    numbers[j + 1] = temp;
                    break;
                }
            }
        }

        for (int number : numbers) {
            System.out.print(" " + number);
        }
    }
}
