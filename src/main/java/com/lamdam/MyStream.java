package com.lamdam;

import com.test.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStream {

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("zhangsan",20,1000));
        list.add(new Employee("lisi",30,2000));
        list.add(new Employee("wangwu",40,3000));
        list.add(new Employee("wangwu",40,3000));

        System.out.println("$$$$$$$$$筛选$$$$$$$$$");

        //筛选
        Stream<Employee> employeeStream = list.stream().filter(e -> e.getAge() > 30);
        employeeStream.forEach(e-> System.out.println(e.getName()));

        System.out.println("$$$$$$$$$获取第一个$$$$$$$$$");

        //获取第一个
        Stream<Employee> limit = list.stream().limit(1);
        limit.forEach(System.out::println);

        System.out.println("$$$$$$$$$跳过一个$$$$$$$$$");

        //跳过一个
        Stream<Employee> skip = list.stream().skip(1);
        skip.forEach(System.out::println);

        System.out.println("$$$$$$$$$去重$$$$$$$$$");

        //自动调用equals和hashcode
        Stream<Employee> distinct = list.stream().distinct();
        distinct.forEach(System.out::println);

        //map一接收Lambda，
        //将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        Stream<String> stringStream = list.stream().map(e -> e.getName());
        stringStream.forEach(System.out::println);

        //小写转换大写
        List<String> strings = Arrays.asList("a", "b", "c");
        Stream<String> stringStream1 = strings.stream().map(String::toUpperCase);
        stringStream1.forEach(System.out::println);

        System.out.println("$$$$$$$$$第一种升序排序$$$$$$$$$");

        Stream<Integer> sorted = list.stream().map(Employee::getAge).sorted();
        sorted.forEach(System.out::println);

        System.out.println("$$$$$$$$$第二种升序排序$$$$$$$$$");

        List<Employee> collect = list.stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("$$$$$$$$$第二种降序排序$$$$$$$$$");

        List<Employee> collect1 = list.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList());
        collect1.forEach(System.out::println);

        System.out.println("$$$$$$$$$定制排序$$$$$$$$$");
        Stream<Employee> sorted1 = list.stream().sorted((o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return o1.getAge() - o2.getAge();
            }
        });
        sorted1.forEach(System.out::println);

        System.out.println("$$$$$$$$$查询匹配$$$$$$$$$");

        //检查是否匹配所有元素
        boolean b1 = list.stream().allMatch(e -> e.getAge() > 30);
        System.out.println(b1);

        //检查是否至少匹配一个元素
        boolean b2 = list.stream().anyMatch(e -> e.getAge() > 30);
        System.out.println(b2);

        //检查是否没有匹配的元素
        boolean b3 = list.stream().noneMatch(e -> e.getName().equals("666"));
        System.out.println(b3);

        //返回第一个值
        Optional<Employee> first = list.stream().findFirst();
        if (first.isPresent()) {
            Employee employee = first.get();
            System.out.println(employee);
        } else {
            System.out.println("no value?");
        }

        //返回任意一个值
        Optional<Employee> any = list.stream().filter(e->e.getAge()>30).findAny();
        if (any.isPresent()) {
            Employee employee = any.get();
            System.out.println(employee);
        } else {
            System.out.println("no value?");
        }

        //返回流中元素的总个数
        long count = list.stream().filter(e->e.getAge()>30).count();
        System.out.println(count);

        //返回流中最大的值
        Optional<Employee> max = list.stream().max((x, y) -> x.getAge() - y.getAge());
        System.out.println(max.get());

        //返回流中最小的值
        Optional<Employee> min = list.stream().min((x, y) -> x.getAge() - y.getAge());
        System.out.println(min.get());

    }

}
