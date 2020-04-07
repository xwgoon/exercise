package com.horstmann.corejava.v1ch08;

import com.horstmann.corejava.v1ch05.inheritance.Employee;
import com.horstmann.corejava.v1ch05.inheritance.Manager;
import org.springframework.util.SocketUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {

//        int a = 1;
//        Integer b = 1;
//        int[] c = {1};
//        Integer[] d = {1};
//
//        getMiddle0(a);
//        getMiddle0(b);
//        getMiddle0(c);
//        getMiddle0(d);
//
//        getMiddle1(a);
//        getMiddle1(b);
//        getMiddle1(c);
//        getMiddle1(d);
//
//        getMiddle2(a);
//        getMiddle2(b);
//        getMiddle2(c);
//        getMiddle2(d);
//
//        getMiddle3(a);
//        getMiddle3(b);
//        getMiddle3(c);
//        getMiddle3(d);
//
//        getMiddle4(a);
//        getMiddle4(b);
//        getMiddle4(c);
//        getMiddle4(d);
//
//        getMiddle5(a);
//        getMiddle5(b);
//        getMiddle5(c);
//        getMiddle5(d);
//
//        getMiddle6(a);
//        getMiddle6(b);
//        getMiddle6(c);
//        getMiddle6(d);

//        DateInterval interval = new DateInterval();
//        Pair<LocalDate> pair=interval;
//        pair.getSecond();
//        pair.setSecond(LocalDate.now());

//        Pair<LocalDate> pair0=new Pair<>();
//        System.out.println(pair0.getClass());

//        Pair<String> pair0 = new Pair<>("", "");
//        System.out.println(pair0);
//
//        Pair<String> pair1 = Pair.makePair0(() -> {
//            String s0 = "hello";
//            String s1 = " world";
//            return s0 + s1;
//        });
//        System.out.println(pair1);
//
//        Pair<String> pair2 = Pair.makePair0(String::new);
//        System.out.println(pair2);
//
//        Pair<String> pair3 = Pair.makePair1(String.class);
//        System.out.println(pair3);
//
//        String[] pair4 = Pair.makePair2("");
//        System.out.println(Arrays.toString(pair4));
//        Employee e1 = new Employee("e1");
//        Employee e2 = new Employee("e2");
//        Pair<Employee> p1 = new Pair<>(e1, e2);
//        printBuddies(p1);
//
//        Manager m1 = new Manager("m1");
//        Manager m2 = new Manager("m2");
//        Pair<Manager> p2 = new Pair<>(m1, m2);
//        printBuddies(p2);
//
//        System.out.println(hasNulls0(p2));
//        System.out.println(hasNulls1(p2));

//        Employee employee = new Employee("a", 1);
//        try {
//            Field field = Employee.class.getDeclaredField("name");
//            field.setAccessible(true);
//            System.out.println(field.get(employee));
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("#".toUpperCase());
//        Object x= null;
//        switch (x){
//            case "0":
//                System.out.println("0");
//                break;
//            case "1":
//                System.out.println("1");
//                break;
//            default:
//                System.out.println("default");
//        }

//        Object o=null;
//        String s= o.toString();  //空指针
//        String s=(String) o;
//        System.out.println(s==null);

//        Integer i= 1;
//        String s=  String.valueOf(i);

        BigDecimal[] prices={new BigDecimal(1), null, new BigDecimal(2)};
        System.out.println(Arrays.stream(prices)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));



    }

    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Object obj = p.getFirst();
        Employee second = p.getSecond();
//        p.setFirst(new Manager());
        p.setFirst(null);
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void printBuddies1(Pair<? super Employee> p) {
        Object first = p.getFirst();
        p.setFirst(new Employee());
        p.setFirst(new Manager());
        p.setFirst(null);
//        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static boolean hasNulls0(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static <T> boolean hasNulls1(Pair<T> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
    //    public static <T> T getMiddle0(T a) {
//        return a;
//    }
//
//    public static <T> T getMiddle1(T... a) {
//        return a[a.length / 2];
//    }
//
//    public static <T> T getMiddle2(T[] a) {
//        return a[a.length / 2];
//    }
//
//    public static int getMiddle3(int... a) {
//        return a[a.length / 2];
//    }
//
//    public static int getMiddle4(int[] a) {
//        return a[a.length / 2];
//    }
//
//    public static Integer getMiddle5(Integer... a) {
//        return a[a.length / 2];
//    }
//
//    public static Integer getMiddle6(Integer[] a) {
//        return a[a.length / 2];
//    }


}

