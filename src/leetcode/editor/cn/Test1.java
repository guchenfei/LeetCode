package leetcode.editor.cn;

public class Test1 {
    public static void main(String[] args) {
        int n = 456;
        if (n >= 1 && n <= Math.pow(10.0, 5.0)) {
            System.out.println(quadrature(n) - sum(n));
        }
    }

    private static int quadrature(int n){
        int result = 1;
        int current = 1;
        while ((current = n%10) != n){
            result = current*result;
            n = n / 10;
        }
        return result * n;
    }

    private static int sum(int n){
        int result = 0;
        int current = 0;
        while ((current = n%10) != n){
            result = current+result;
            n = n / 10;
        }
        return result + n;
    }
}
