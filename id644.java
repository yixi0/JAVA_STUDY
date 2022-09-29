import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/6 14:02
 */
public class id644 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(input.nextInt());
        }
        int idx = 0;
        int maxLen = 1;
        int tempLen = 0;
        //  1,9,2,5,7,3,4,6,8,0
        for (int i = 0; i < n; i++) {
            tempLen = 0;
            for (int j = 1; i + j < n; j++) {
                tempLen++;
                if (list.get(i + j - 1) >= list.get(i + j)) {
                    if (tempLen > maxLen) {
                        idx = i;
                        maxLen = tempLen;
                    }
                    break;
                }
            }

        }
        for (int i = idx; i < idx + maxLen; i++) {
            System.out.print(list.get(i) + " ");
        }


    }
}