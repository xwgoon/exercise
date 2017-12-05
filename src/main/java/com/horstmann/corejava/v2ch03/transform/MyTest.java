package com.horstmann.corejava.v2ch03.transform;

import java.util.StringTokenizer;

public class MyTest {
    public static void main(String[] args) {
        String str = "ab|1|c|23|d";
        StringTokenizer t = new StringTokenizer(str, "|");
        System.out.println(t.nextToken());
        System.out.println(t.nextToken());
        System.out.println(t.nextToken());

        System.out.println("-------------------");

        String[] arr=str.split("\\|");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

    }
}
