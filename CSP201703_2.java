import java.util.Scanner;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/10/20 14:09
 */
public class CSP201703_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < m; i++) {
            int id = input.nextInt();
            int move = input.nextInt();
            //索引
            int idx = 0;
            while (arr[idx++] != id) {
            }
            idx--;
            if (move > 0) {
                //向后移动
                for (int j = idx; j < idx + move; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[idx + move] = id;
            } else {
                //向前移动
                for (int j = idx; j > idx + move; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[idx + move] = id;
            }

        }
        for (int j = 0; j < n; j++) {
            System.out.print(arr[j] + " ");
        }
    }
}
