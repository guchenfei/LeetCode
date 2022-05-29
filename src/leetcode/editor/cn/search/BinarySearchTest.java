package leetcode.editor.cn.search;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] a = {1,3,4,6,7,9,11,14,17,20,22,25,30,33,35,40};
        System.out.println(binarySearch(31,a,0,a.length-1));
    }


    public static int binarySearch(int key,int a[],int start,int end){
        if (start > end){
            return -1;
        }

        int m = (start + end) /2;
        if (a[m] == key) return m;
        if (a[m] > key) return binarySearch(key,a,start,m-1);
        return binarySearch(key,a,m+1,end);
    }
}
