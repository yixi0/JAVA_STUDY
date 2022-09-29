/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/7 9:27
 */

import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.OPCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 Stream API
 */
public class StreamTest {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(0, 11, "origami1", "man"));
        personList.add(new Person(1, 12, "origami2", "woman"));
        personList.add(new Person(2, 13, "origami3", "man"));
        personList.add(new Person(3, 14, "origami4", "woman"));

        //串行
        Stream<Person> stream = personList.stream();
        //并行
        Stream<Person> paralleStream = personList.parallelStream();

        //过滤
        List<Person> men = personList.stream()
                .filter(man -> Objects.equals(man.getSex(), "man"))
                .collect(Collectors.toList());
//        men.forEach(System.out::println);

        //提取
        List<String> nameList = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

//        nameList.forEach(System.out::println);

        //限制数量
        List<Person> limitList = personList.stream()
                .limit(2)
                .collect(Collectors.toList());
//        limitList.forEach(System.out::println);

        //获取个数
        long manCount = personList.stream()
                .filter(man -> Objects.equals(man.getSex(), "man"))
                .count();
//        System.out.println("manCount = " + manCount);

        //跳过一些
        List<Person> skipList = personList.stream()
                .skip(1)
                .collect(Collectors.toList());
//        skipList.forEach(System.out::println);

        //List 转 Map
        Map<Long, Person> personMap = personList.stream()
                .collect(Collectors.toMap(Person::getPid, person -> person));
        Person person = personMap.get(1L);
        System.out.println(person);


    }
}

class Person {
    private long pid;
    private int age;
    private String name;
    private String sex;

    public Person() {
    }

    public Person(long pid, int age, String name, String sex) {
        this.pid = pid;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
