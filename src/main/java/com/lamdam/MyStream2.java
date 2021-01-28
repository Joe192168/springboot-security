package com.lamdam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyStream2 {
    public static void main(String[] args) {
        //初始化数据
        int max = 1000000;
        List<String> arrayList = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            arrayList.add(uuid.toString());
        }
        //串行计算 558
        /*long start = System.currentTimeMillis();
        long count = arrayList.stream().sorted().count();
        System.out.println(count);
        long end = System.currentTimeMillis();
        long millis = end - start;
        System.out.println(millis);*/

        //并行计算 289
        long start = System.currentTimeMillis();
        long count = arrayList.parallelStream().sorted().count();
        System.out.println(count);
        long end = System.currentTimeMillis();
        long millis = end - start;
        System.out.println(millis);

    }
}
