package leetcode.editor.cn.sortingalgorithm;

import java.util.Arrays;

public class MergeSort2 {
    public static void main(String[] args) {
        MergeSort2 mergeSort2 = new MergeSort2();
        System.out.println(Arrays.toString(mergeSort2.mergeSort(new int[]{10,9,8,7,6,5,4,3,2,1})));
    }
    public int[] mergeSort(int[] arr) {
        //递归分解结束条件
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        //分解左区域
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        //递归使左子区有序
        left = mergeSort(left);
        //分解右区域
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        //递归使右子区有序
        right = mergeSort(right);
        //合并两个有序区域即全区域有序
        return merge(left,right);
    }

    /**
     * 合并两个有序的子数组使其成为一个有序的数组
     *
     * @return
     */
    private int[] merge(int[] left, int[] right) {
        //创建结果数组
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < result.length; i++) {
            //只有两个都处于子区间才比较大小,否则将剩余依次放到剩余位置就可以了
            if (leftIndex < left.length && rightIndex < right.length) {
                if (left[leftIndex] <= right[rightIndex]){
                    result[i] = left[leftIndex];
                    leftIndex++;
                }else {
                    result[i] = right[rightIndex];
                    rightIndex++;
                }
            }else {
                //否则又一个子数组为空,将另一个依次放入结果集
                if (leftIndex< left.length){
                    result[i] = left[leftIndex++];
                }

                if (rightIndex < right.length){
                    result[i] = right[rightIndex++];
                }
            }

        }
        return result;
    }
}
