/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/7 21:52
 */
public class Fibonacci {

    public static void main(String[] args) {
//        Integer solution = solution1(10);
//        System.out.println("solution = " + solution);
        int j=0,i=3;

        while(--i>0)

            j+=j-=i;
        System.out.println("j = " + j);
    }

    public static Integer solution(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return solution(n - 1) + solution(n - 2);

    }


    public static Integer solution1(int n) {
        int[] a = new int[n + 1];
        a[0] = 0;
        a[1] = 1;
        for (int i = 0; i <= n - 2; i++) {
            a[i + 2] = a[i + 1] + a[i];
        }
        return a[n];
    }
    public static Integer solution2(int n) {
        int a = 0;
        int b = 1;
        int res = 0;
        for (int i = 0; i <= n - 2; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }
}




