package com.horstmann.corejava.v1ch06;

public interface MyInterface {

    void test();

    int X = 1;

    static String print() {
        return "print";
    }

    default String string() {
        System.out.println(hashCode());
        return "call MyInterface string()";
    }
}
