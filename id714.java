import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/13 15:27
 */
public class id714 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int j = 0; j < T; j++) {
            Map<Integer, Integer> map1 = new TreeMap<>();
            Map<Integer, Integer> map2 = new TreeMap<>();
            int n1, n2, zhishu, xishu;

            n1 = input.nextInt();
            for (int i = 0; i < n1; i++) {
                xishu = input.nextInt();
                zhishu = input.nextInt();
                map1.put(zhishu, xishu);
            }
            n2 = input.nextInt();
            for (int i = 0; i < n2; i++) {
                xishu = input.nextInt();
                zhishu = input.nextInt();
                map2.put(zhishu, xishu);
            }
            
            System.out.println(Multinomial.print(map1));
            System.out.println(Multinomial.print(map2));
            System.out.println(Multinomial.print(Multinomial.add(map1, map2)));
            System.out.println(Multinomial.print(Multinomial.minus(map1, map2)));
            System.out.println(Multinomial.print(Multinomial.multiply(map1, map2)));
            System.out.println();
        }
    }
}

class Multinomial {

    private Map<Integer, Integer> map;

    public static String print(Map<Integer, Integer> res) {
        String str = "";
        for (Map.Entry<Integer, Integer> e1 : res.entrySet()) {

            int zhishu = e1.getKey();
            int xishu = e1.getValue();
            if (xishu == 0) {
                continue;
            }
            if (xishu > 0) {
                str += "+";
            }
            //-1-2x-x^2
            if (Math.abs(xishu) == 1) {
                if (xishu < 0) {
                    str += "-";
                }
                if (zhishu == 0) {
                    str += 1;
                } else if (zhishu == 1) {
                    str += "x";
                } else {
                    if (zhishu < 0) {
                        str += "x^(" + zhishu + ")";
                    } else {
                        str += "x^" + zhishu;
                    }
                }
            } else {
                if (zhishu == 0) {
                    str += xishu;
                } else if (zhishu == 1) {
                    str += xishu + "x";
                } else {
                    if (zhishu < 0) {
                        str += xishu + "x^(" + zhishu + ")";
                    } else {
                        str += xishu + "x^" + zhishu;
                    }
                }
            }

        }
        if (str.equals("")) {
            return "0";
        }
        if (str.startsWith("+")) {
            str = str.substring(str.indexOf("+") + 1);
        }
        return str;
    }

    public static Map<Integer, Integer> add(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> res = new TreeMap<>(Comparator.naturalOrder());
        res.putAll(map1);
        for (Map.Entry<Integer, Integer> e2 : map2.entrySet()) {
            res.merge(e2.getKey(), e2.getValue(), Integer::sum);
        }
        return res;
    }

    public static Map<Integer, Integer> minus(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> res = new TreeMap<>(Comparator.naturalOrder());
        res.putAll(map1);
        for (Map.Entry<Integer, Integer> e2 : map2.entrySet()) {
            if (res.get(e2.getKey()) == null) {
                res.put(e2.getKey(), -e2.getValue());
            } else {
                res.put(e2.getKey(), res.get(e2.getKey()) - e2.getValue());
            }
        }
        return res;
    }

    public static Map<Integer, Integer> multiply(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> res = new TreeMap<>(Comparator.naturalOrder());
        for (Map.Entry<Integer, Integer> e1 : map1.entrySet()) {
            for (Map.Entry<Integer, Integer> e2 : map2.entrySet()) {
                int zhishu = e1.getKey() + e2.getKey();
                int xishu = e1.getValue() * e2.getValue() + (res.get(zhishu) == null ? 0 : res.get(zhishu));
                res.put(zhishu, xishu);
            }
        }
        return res;
    }
}

