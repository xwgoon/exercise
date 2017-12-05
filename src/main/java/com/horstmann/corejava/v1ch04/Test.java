package com.horstmann.corejava.v1ch04;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.time.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Random;

//
/*

 */

/**
 *
 */

public class Test {

//    private String s;
//    private int i;
//
//    public Test(){}
//
//    public Test(String str, int x){
//        String s=str;
//        int i=x;
//        System.out.println(s);
//        System.out.println(i);
//    }

    public static void main(String[] args) {

//        Test test = new Test();
//        test.print();

//        Test t=new Test("test", 1);

//        System.out.println(new Date().getDate());
//        LocalDate localDate = LocalDate.now();
//        LocalDate localDate = LocalDate.of(2017, 7, 22);
//        System.out.println(localDate.plusDays(1000));
//        System.out.println(localDate);

//        System.out.println(LocalDate.now());
//        System.out.println(LocalTime.now());
//        System.out.println(LocalDateTime.now());

//        Employee e1 = new Employee("Tom");
//        Employee e2 = new Employee("Jim");
//        System.out.println(e1.equal(e2));

//        e1.show();
//        e2.show();
//        Employee.show();

//        BigDecimal totalProfits=BigDecimal.ZERO;
//        totalProfits.add(BigDecimal.valueOf(2));
//        totalProfits=totalProfits.add(BigDecimal.valueOf(2));
//        System.out.println(totalProfits);


//        System.out.println('a');
//        System.out.println('a'+1d);
//        System.out.println('a'+"b");
//        int y=1+1;
//        int x=1;
//        int y=2;
//        byte z=x+y;

//        byte b1=(byte) 128;
//        char b2=2;
//        char b3=3;
//        b1=b2+b3;
//        b1=b2+3;
//        b1=x;
//        b1=65535+1;

//        long a=Long.MAX_VALUE+1;
//        int b=1;
//        long c=a+b;
//
//
//        System.out.println(a);


//        System.out.println((byte) 1 + 'a' + 2f + "b");
//        System.out.println("b" + (byte) 1 + 'a' + 2f);

//        String s='a';
//        System.out.println('\u0000');

//        Employer e=new Employer();
//        System.out.println(e);

//        Date date=new Date();
//        java.sql.Date date1=new java.sql.Date(1);

        System.out.println();
        System.out.println(Year.now());

    }

}

class Employer{

//    {
//        id=1;
//        name="1";
//        System.out.println("initialization block 1");
//    }

    static {
        id=2;
//        name="2";
        System.out.println("static initialization block 1");
    }


    private static int id=5;
    private String name;

    static {
        id=3;
//        name="3";
        System.out.println("static initialization block 2");
    }

//    {
//        id=4;
//        name="4";
//        System.out.println("initialization block 2");
//    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class Employee {

//    private final StringBuilder builder = new StringBuilder("hello ");


    private static long nextId = 1;

    private Long id=1L;
    private String name="TEST";

    public Employee(){
        name="123";

    }

    public Employee(String name) {
        this.id = nextId++;
        this.name = name;
//        builder.append(format());
//        id=1;
    }
//    public Employee(){
//        id=2;
//    }

    private static long getId() {
//        System.out.println(name);
        return nextId++ + 2;
    }

    private String format() {
        String name = "test";
        long nextId = 2;
        for (int i = 0; i < 10; i++) {
//            String name="123";
            System.out.println(nextId);
        }

        return name.toUpperCase();
    }


    public boolean equal(Employee employee) {
        return format().equals(employee.format());
    }

    public void show() {
        System.out.println(id);
        System.out.println(name);

//        System.out.println(builder.toString());

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


