package com.horstmann.corejava.v1ch06;

import com.horstmann.corejava.v1ch06.interfaces.Employee;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Consumer;

import static com.horstmann.corejava.v1ch06.SubClass.X;

public class Test {

    public static void main(String[] args) {

//        BigDecimal bigDecimal=new BigDecimal("1.0");
//        BigDecimal bigDecimal1=new BigDecimal("1.00");
//
//        System.out.println(bigDecimal.equals(bigDecimal1));
//        System.out.println(bigDecimal.compareTo(bigDecimal1));

//        System.out.println(MyInterface.print());
//        MyInterface myInterface= () -> System.out.println("呵呵");

//        MyInterface myInterface2= new MyInterface() {
//
//            @Override
//            public void test() {
//
//            }
//
//            @Override
//            public String string() {
//                return "Test class";
//            }
//        };

//        test2(new Test());
//        test2(myInterface);
//        test2(() -> System.out.println("呵呵123"));

//        Test t = new Test();
//        System.out.println(t.string());
//        t.test();
//        String firs="";
//
//        Comparator<String> com = (first, second) -> first.length() - second.length();

//        fun(new Test1());
//        System.out.println();
//        fun(it -> System.out.println("lambda: " + it));
//        System.out.println();
//        fun(System.out::println);
//
//        fun2(new Test2());
//        fun2(it -> System.out.println("MyConsumer: " + it));

//        Employee employee = new Employee();
//        employee.setName("张三");
//        employee.setSalary(123);
//        System.out.println(employee);
//
//        System.out.println();
//
//        System.out.println(new Employee() {{
//            setName("李四");
//            setSalary(456);
//        }});

//        System.out.println(getClass());

//        class T{}
//        System.out.println(T.class.getEnclosingClass());
//
//        System.out.println(new Object(){}.getClass().getEnclosingClass());


    }

//    public static void fun(Consumer<String> consumer) {
//        System.out.println("fun() start.");
//        consumer.accept("Hello");
//        System.out.println("fun() end.");
//    }
//
//    public static void fun2(MyConsumer<String> consumer) {
//        System.out.println("fun2() start.");
//        consumer.accept("Hello");
//        System.out.println("fun2() end.");
//    }

}

//class Test1 implements Consumer<String> {
//
//    @Override
//    public void accept(String s) {
//        System.out.println("Test1: " + s);
//    }
//}
//
//class Test2 implements MyConsumer<String> {
//
//    @Override
//    public void accept(String s) {
//
//    }

//    @Override
//    public boolean accept(int obj) {
//        return false;
//    }
//}

//@FunctionalInterface
//interface MyConsumer<T> {
//    int x=1;
//
//    void accept(T s);
//
////    boolean accept(int obj);
//
//    default void test(T s){
//        accept(s);
//    }
//
//}
