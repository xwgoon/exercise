package com.horstmann.corejava.v1ch14;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Test {

    public static void main(String[] args) {

//        String s0="123";
//        String s1=null;
//
//        boolean b=(s0=s1) != null;
//        System.out.println(b);
//
//        System.out.println(s0);
//        System.out.println(s1);

        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
//        concurrentMap.put("a", 1);
//        concurrentMap.put("b", 2);
        System.out.println(concurrentMap);

//        concurrentMap.merge("b", 22, Integer::sum);
//        System.out.println(concurrentMap);

        Integer sum = concurrentMap.reduceValues(1L, Integer::sum);
        System.out.println(sum);

    }
}
