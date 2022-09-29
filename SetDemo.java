import java.util.*;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/28 9:47
 */
public class SetDemo {

    private static final int CLUB_NUM = 3;
    private static final String[] CLUB_NAME_LIST = new String[]{"游戏社", "动漫社", "健身社"};

    public static void main(String[] args) {
        //用于存放所有的社团成员名单

        Set<String> clubsSet = new HashSet<>();

        //存放所有的社团
        Map<String, List<String>> clubList = new LinkedHashMap<>();

        List[] lists = new List[CLUB_NUM];

        //一般是从数据库获取数据，这里为了简单，直接手动添加数据
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list1.add("张三");
        list1.add("李四");
        list1.add("王五");

        list2.add("李四");
        list2.add("王五");
        list2.add("赵六");

        list3.add("王五");
        list3.add("赵六");
        list3.add("田七");



        lists[0] = list1;
        lists[1] = list2;
        lists[2] = list3;

        //将社团存放在Map中
        for (int i = 0; i < CLUB_NUM; i++) {
            clubList.put(CLUB_NAME_LIST[i], lists[i]);
        }

        for (int i = 0; i < CLUB_NUM; i++) {
            List<String> club = clubList.get(CLUB_NAME_LIST[i]);
            clubsSet.addAll(club);
        }

        clubsSet.forEach(System.out::println);

    }
}
