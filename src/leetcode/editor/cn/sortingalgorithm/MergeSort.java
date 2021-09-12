package leetcode.editor.cn.sortingalgorithm;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        /**
         * 临时缓存合并排序左半部分数组的索引
         */
        int leftArrayIndex = 0;
        /**
         * 合并排序前半个数组长度
         */
        int leftMergeLength = mid - begin;
        /**
         * 合并排序后半个数组开始位置的索引
         */
        int rightArrayIndex = mid;
        /**
         * 合并排序后半个数组末位置的索引
         */
        int rightArrayEndIndex = end;
        /**
         * 比较大小后放入位置的索引
         */
        int startIndex = begin;

        /**
         * 备份左边数组
         */

        for (int i = 0; i < leftMergeLength; i++) {
            leftArray[i] = array[begin + i];
        }

        while (leftArrayIndex < leftMergeLength) {
            if (rightArrayIndex < rightArrayEndIndex && cmp(array[rightArrayIndex], leftArray[leftArrayIndex]) < 0) {
                array[startIndex++] = array[rightArrayIndex++];
            } else {
                array[startIndex++] = leftArray[leftArrayIndex++];
            }
        }
    }
}
