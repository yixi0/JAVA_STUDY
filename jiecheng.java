/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/7 14:48
 */
public class jiecheng {
    public static void main(String[] args) {
        int a=1,b=1;

        boolean flag=a!=b++;


        System.out.println(a++ == b);

        System.out.println(!(++a == b));
        System.out.println((++a == b) || flag);

        System.out.println((++a == b) && flag);
    }
    public static int f(int n) {
        if(n == 0) {
            return 1;
        }
        return n * f(n - 1);
    }
}