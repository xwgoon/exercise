package com.horstmann.corejava.v1ch06;

public class SubClass implements MyInterface, MyInterface2{

    public static final int X = 1;

    @Override
    public void test() {
    }

    public String string() {
//        print();
        test();
        return "call SubClass string()";
    }

//    public void test(){
//        System.out.println("SubClass test()");
//    }
}
