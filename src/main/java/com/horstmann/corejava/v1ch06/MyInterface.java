package com.horstmann.corejava.v1ch06;

public interface MyInterface {

    void test();

    int X = 1;

    static String print() {
        return "print";
    }

    //    @Override
//    default String string() {
//        return "call MyInterface string()";
//    }
}
