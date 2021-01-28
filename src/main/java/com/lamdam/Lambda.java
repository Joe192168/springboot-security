package com.lamdam;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Lambda {

    public static void main(String[] args) {
        //匿名函数
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        };
        new Thread(runnable1).start();
        //lambda表达式第二种写法
        Runnable runnable2 = ()->{System.out.println("aaa......");};
        new Thread(runnable2).start();
        //lambda表达式第三种写法
        new Thread(()->{System.out.println("bbb......");}).start();
        //第四种写法，如果方法体里面只有一条语句的时候，可以省去大括号
        new Thread(()-> System.out.println("ccc......")).start();

        //匿名函数
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };
        TreeSet<String> treeSet = new TreeSet<>(comparator);

        //lambda表达式
        Comparator<String> comparator1 = (o1,o2)->o1.length()-o2.length();
        TreeSet<String> treeSet1 = new TreeSet<>(comparator1);

        //lambda表达式
        TreeSet<String> treeSet2 = new TreeSet<>((o1,o2)->o1.length()-o2.length());

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("hello");
        list.add("stream");

        int count = 0;
        for (String s : list) {
            if (s.length()>3){
                count++;
            }
        }
        System.out.println(count);

        long count1 = list.stream().filter((s) -> s.length() > 3).count();
        System.out.println(count1);

        int[] array = new int[]{1,2,3};
        IntStream arrayStream = Arrays.stream(array);
        long count2 = arrayStream.filter(i -> i > 2).count();
        System.out.println(count2);



    }

}
