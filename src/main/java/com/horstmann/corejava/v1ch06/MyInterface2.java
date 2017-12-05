package com.horstmann.corejava.v1ch06;

public interface MyInterface2 {

    void test();
//
//    int X = 1;

    static String print() {
        return "print";
    }

    default String string() {
        return "call MyInterface2 string()";
    }

//    default void test(){
//
//    }

}
