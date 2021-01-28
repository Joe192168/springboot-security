import com.test.entity.Users;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class CollectorsTest {

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    List<Users> usersList1 = new ArrayList<>();
    List<Users> usersList2 = new ArrayList<>();

    //准备数据
    @Before
    public void load(){
        //初始数据集合 一个是里面元素无重复的集合，一个是里面元素有重复的集合
        list1 = Arrays.asList("jack", "bob", "alice", "mark");
        list2 = Arrays.asList("jack", "jack", "alice", "mark");

        //无重复
        usersList1.add(new Users(1,"张三",12));
        usersList1.add(new Users(2,"李四",30));
        usersList1.add(new Users(3,"王五",20));
        usersList1.add(new Users(4,"赵六",12));

        //重复
        usersList2.add(new Users(1,"张三",12));
        usersList2.add(new Users(2,"李四",30));
        usersList2.add(new Users(3,"王五",20));
        usersList2.add(new Users(4,"赵六",12));
        usersList2.add(new Users(4,"赵六",12));
    }

    //转换toList
    @Test
    public void toList(){
        List<String> collect1 = list1.stream().collect(Collectors.toList());
        collect1.forEach(System.out::println);

        System.out.println("---------------------------");

        List<Users> collect2 = usersList1.stream().collect(Collectors.toList());
        collect2.forEach(System.out::println);
    }

    //转换toSet
    @Test
    public void toSet(){
        Set<String> collect1 = list1.stream().collect(Collectors.toSet());
        collect1.forEach(System.out::println);

        System.out.println("---------------------------");

        Set<String> collect2 = list2.stream().collect(Collectors.toSet());
        collect2.forEach(System.out::println);

        System.out.println("---------------------------");

        Set<Users> collect3 = usersList1.stream().collect(Collectors.toSet());
        collect3.forEach(System.out::println);

        System.out.println("---------------------------");

        Set<Users> collect4 = usersList2.stream().collect(Collectors.toSet());
        collect4.forEach(System.out::println);
    }

    //转换toMap
    @Test
    public void toMap(){
        //无重复
        Map<String, String> collect1 = list1.stream().collect(Collectors.toMap(String::new, s -> s));
        collect1.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("---------------------------");

        //重复
        //如果stream中有重复的值，则转换会报IllegalStateException异常
        //在toMap中添加第三个参数mergeFunction，来解决冲突的问题
        Map<String, String> collect2 = list2.stream().collect(Collectors.toMap(String::new, s -> s,(item,mergeItem)->item));
        collect2.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("---------------------------");

        Map<Integer, String> collect3 = usersList1.stream().collect(Collectors.toMap(Users::getId, Users::getName));
        collect3.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("---------------------------");

        Map<Integer, String> collect4 = usersList2.stream().collect(Collectors.toMap(Users::getId, Users::getName,(item,mergeItem)->item));
        collect4.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });
    }

    //转换toCollection
    @Test
    public void toCollection(){
        LinkedList<String> collect1 = list1.stream().collect(Collectors.toCollection(LinkedList::new));
        collect1.forEach(System.out::println);

        System.out.println("---------------------------");

        LinkedList<Users> collect2 = usersList1.stream().collect(Collectors.toCollection(LinkedList::new));
        collect2.forEach(System.out::println);
    }

    //collectingAndThen允许我们对生成的集合再做一次操作
    @Test
    public void collectingAndThen(){
        List<String> collect1 = list1.stream().collect(Collectors.collectingAndThen(Collectors.toList(), s -> {
            return new ArrayList(s);
        }));
        collect1.forEach(System.out::println);

        System.out.println("---------------------------");

        ArrayList collect2 = usersList1.stream().collect(Collectors.collectingAndThen(Collectors.toList(), u -> {
            //转换list后进行改变某个元素的属性值
            u.get(0).setAge(20);
            return new ArrayList(u);
        }));
        collect2.forEach(System.out::println);
    }

    //joining连接结合元素
    @Test
    public void joining(){
        //第一种连接方式
        String collect1 = list1.stream().collect(Collectors.joining());
        System.out.println("第一种连接方式："+collect1);
        //第二种连接方式，使用空格进行连接，充当分隔符操作
        String collect2 = list1.stream().collect(Collectors.joining(" "));
        System.out.println("第二种连接方式："+collect2);
        //第三种连接方式，追加前缀和后缀
        String collect3 = list1.stream().collect(Collectors.joining(" ","[","]"));
        System.out.println("第三种连接方式："+collect3);

        System.out.println("---------------------------");

        String collect4 = usersList1.stream().map(Users::getName).collect(Collectors.joining());
        System.out.println(collect4);

        String collect5 = usersList1.stream().map(Users::getName).collect(Collectors.joining(","));
        System.out.println(collect5);

        String collect6 = usersList1.stream().map(Users::getName).collect(Collectors.joining(",","{","}"));
        System.out.println(collect6);
    }

    //统计stream中的元素的个数
    @Test
    public void counting(){
        Long collect1 = list1.stream().collect(Collectors.counting());
        System.out.println(collect1);

        System.out.println("---------------------------");

        Long collect2 = usersList1.stream().collect(Collectors.counting());
        System.out.println(collect2);
    }

    //summarizingDouble/Long/Int为stream中的元素生成了统计信息，返回的结果是一个统计类
    @Test
    public void summarizing(){
        //summarizingInt 统计
        IntSummaryStatistics collect1 = list1.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println(collect1);

        System.out.println("---------------------------");

        //summarizingDouble 统计
        DoubleSummaryStatistics collect2 = list1.stream().collect(Collectors.summarizingDouble(String::length));
        System.out.println(collect2);

        System.out.println("---------------------------");

        //summarizingLong 统计
        LongSummaryStatistics collect3 = list1.stream().collect(Collectors.summarizingLong(String::length));
        System.out.println(collect3);

        System.out.println("---------------------------");

        DoubleSummaryStatistics collect4 = usersList1.stream().collect(Collectors.summarizingDouble(Users::getAge));
        System.out.println(collect4);

        System.out.println("---------------------------");

        LongSummaryStatistics collect5 = usersList1.stream().collect(Collectors.summarizingLong(Users::getAge));
        System.out.println(collect5);

        System.out.println("---------------------------");

        IntSummaryStatistics collect6 = usersList1.stream().collect(Collectors.summarizingInt(Users::getAge));
        System.out.println(collect6);
    }

    //averaging/Double/Long/Int对stream中的元素做平均操作
    @Test
    public void averaging(){
        //averaging Double 平均
        Double collect1 = list1.stream().collect(Collectors.averagingDouble(String::length));
        System.out.println(collect1);

        System.out.println("---------------------------");

        //averaging Long 平均
        Double collect2 = list1.stream().collect(Collectors.averagingLong(String::length));
        System.out.println(collect2);

        System.out.println("---------------------------");

        //averaging Int 平均
        Double collect3 = list1.stream().collect(Collectors.averagingInt(String::length));
        System.out.println(collect3);

        System.out.println("---------------------------");

        Double collect4 = usersList1.stream().collect(Collectors.averagingDouble(Users::getAge));
        System.out.println(collect4);

        System.out.println("---------------------------");

        Double collect5 = usersList1.stream().collect(Collectors.averagingLong(Users::getAge));
        System.out.println(collect5);

        System.out.println("---------------------------");

        Double collect6 = usersList1.stream().collect(Collectors.averagingInt(Users::getAge));
        System.out.println(collect6);
    }

    //summing/Double/Long/Int对stream中的元素做sum操作
    @Test
    public void summing(){
        //summingDouble 求和
        Double collect1 = list1.stream().collect(Collectors.summingDouble(String::length));
        System.out.println(collect1);

        System.out.println("---------------------------");

        //summingLong 求和
        Long collect2 = list1.stream().collect(Collectors.summingLong(String::length));
        System.out.println(collect2);

        System.out.println("---------------------------");

        //summingInt 求和
        Integer collect3 = list1.stream().collect(Collectors.summingInt(String::length));
        System.out.println(collect3);

        System.out.println("---------------------------");

        Double collect4 = usersList1.stream().collect(Collectors.summingDouble(Users::getAge));
        System.out.println(collect4);

        Long collect5 = usersList1.stream().collect(Collectors.summingLong(Users::getAge));
        System.out.println(collect5);

        Integer collect6 = usersList1.stream().collect(Collectors.summingInt(Users::getAge));
        System.out.println(collect6);
    }

    //maxBy()/minBy()根据提供的Comparator，返回stream中的最大或者最小值
    @Test
    public void max(){
        //可以使用默认Comparator.naturalOrder()自然计算，根据字母的大小， 这块使用Comparator.comparing(String::length)值的长度
        Optional<String> collect1 = list1.stream().collect(Collectors.maxBy(Comparator.comparing(String::length)));
        System.out.println(collect1);

        System.out.println("---------------------------");

        //可以使用默认Comparator.naturalOrder()自然计算，根据字母的大小， 这块使用Comparator.comparing(String::length)值的长度
        Optional<String> collect2 = list1.stream().collect(Collectors.minBy(Comparator.comparing(String::length)));
        System.out.println(collect2);

        System.out.println("---------------------------");

        Optional<Users> collect3 = usersList1.stream().collect(Collectors.maxBy(Comparator.comparing(Users::getAge)));
        System.out.println(collect3);

        Optional<Users> collect4 = usersList1.stream().collect(Collectors.minBy(Comparator.comparing(Users::getAge)));
        System.out.println(collect4);
    }

    //GroupingBy根据某些属性进行分组，并返回一个Map
    @Test
    public void groupingBy(){
        Map<Integer, Set<String>> collect = list1.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        collect.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("---------------------------");

        usersList1.stream().collect(Collectors.groupingBy(Users::getAge)).forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });
    }

    //PartitioningBy是一个特别的groupingBy，PartitioningBy返回一个Map，
    //这个Map是以boolean值为key，从而将stream分成两部分，一部分是匹配PartitioningBy条件的，一部分是不满足条件的
    //结果被分成了两部分
    @Test
    public void partitioningBy(){
        Map<Boolean, List<String>> collect1 = list1.stream().collect(Collectors.partitioningBy(s -> s.length() > 3));
        collect1.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("---------------------------");

        Map<Boolean, List<Users>> collect2 = usersList1.stream().collect(Collectors.partitioningBy(u -> u.getAge() > 20));
        collect2.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });
    }

    //分组后获取最大值或最小值
    @Test
    public void groupingMaxOrMin(){
        //分组后去最大值
        Map<Integer, Optional<Users>> collect = usersList1.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.maxBy(Comparator.comparing(Users::getId))));

        //分组后去最小值
        Map<Integer, Optional<Users>> collect1 = usersList1.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.minBy(Comparator.comparing(Users::getId))));

        //打印最大值
        collect.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });

        System.out.println("-----------------------");

        //打印最小值
        collect1.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });
    }

    //分组后重新组装新对象
    @Test
    public void mapping(){
        Map<Integer, List<String>> collect = usersList1.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.mapping(u -> {
            return u.getName();
        }, Collectors.toList())));

        collect.forEach((key,value)->{
            System.out.println("key：" +key+" ---- value："+value);
        });
    }

    //单字段进行排序
    @Test
    public void sort(){
        System.out.println("$$$$$$$$$$$倒序$$$$$$$$$$");
        usersList1.stream().sorted(Comparator.comparing(Users::getAge).reversed()).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("$$$$$$$$$$$升序$$$$$$$$$$");
        usersList1.stream().sorted(Comparator.comparing(Users::getAge)).collect(Collectors.toList()).forEach(System.out::println);
    }

    //多字段排序，注意：排序的字段不能为空
    @Test
    public void comparator(){
        //手动控制排序，（复制业务的时候，可以采用该方法）
        //1、年龄倒序 2、id升序
        Comparator<Users> usersComparator = Comparator.comparing(Users::getAge, (o1, o2) -> {
            //倒序
            return o2.compareTo(o1);
        }).thenComparing(Users::getId, (o1, o2) -> {
            //升序
            return o1.compareTo(o2);
        });

        List<Users> collect = usersList1.stream().sorted(usersComparator).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("#@@@@@@@@@@@@@@@@@@@@@@@@@");


        //* 第二种排倒序方法
        //* Comparator.reverseOrder 倒叙
        //* Comparator.naturalOrder 升叙
        //1、年龄 倒序，2、id 倒序
        Comparator<Users> usersComparator1 = Comparator.comparing(Users::getAge, Comparator.reverseOrder()).thenComparing(Users::getId, Comparator.reverseOrder());

        List<Users> collect1 = usersList1.stream().sorted(usersComparator1).collect(Collectors.toList());

        collect1.forEach(System.out::println);
    }


}
