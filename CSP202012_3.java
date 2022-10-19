import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CSP202012_3 {
    public static class Node {
        String name;
        String id;
        int level;

        public Node(String name, String id, int level) {
            this.name = name;
            this.id = id;
            this.level = level;
        }
    }

    static int n;
    static Node[] tree = new Node[105];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            int j = 0;
            while (str.charAt(j) == '.') {
                j++;
            }

            int level = j / 2;
            str = str.substring(j);
            String[] split = str.split(" ");
            //有ID
            if (split.length != 1) {
                tree[i] = new Node(split[0].toLowerCase(), split[1], level);
            } else {
                tree[i] = new Node(split[0].toLowerCase(), null, level);
            }
        }
        while (m-- > 0) {
            String qs = scanner.nextLine();
            process(qs);
        }
    }

    private static void process(String qs) {
        String[] split = qs.split(" ");
        //变小写
        for (int i = 0; i < split.length; i++) {
            if (!split[i].contains("#")) {
                split[i] = split[i].toLowerCase();
            }
        }
        int k = split.length, s = 0;
        ArrayList<Integer> list = new ArrayList<>();
        find(s, k, -1, split, 0, list);
        Collections.sort(list);
        int last = -1, count = 0;
        StringBuilder res = new StringBuilder();
        for (int a : list) {
            //去重+按序输出
            if (last != a) {
                res.append(" ").append(a);
                count++;
                last = a;
            }
        }
        System.out.println(count + res.toString());
    }


    private static void find(int s, int k, int level, String[] q, int l, ArrayList<Integer> list) {
        if (s == k) {
            //匹配成功
            list.add(l);
            return;
        }
        //匹配到最后都没成功
        if (l == n) {
            return;
        }
        for (int i = l; i < n; i++) {
            //越级，匹配失败
            if (level >= tree[i].level) {
                return;
            }
            if (q[s].equals(tree[i].name) || q[s].equals(tree[i].id)) {
                find(s + 1, k, tree[i].level, q, i + 1, list);
            }
        }
    }
}