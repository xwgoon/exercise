package com.horstmann.corejava.v1ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {

    public static void main(String[] args) throws IOException {

//        Child child = new Child();
//        try {
//            child.fun(1);
//        } catch (Exception e) {
//            StringWriter out = new StringWriter();
//            e.printStackTrace(new PrintWriter(out));
//            String s = out.toString();
//            System.err.println(s);
////            e.printStackTrace();
//            Exception ioException = new IOException();
//            //noinspection ConstantConditions
//            throw new IOException();
//        }

//        System.out.println(test(2));

        test(2);
        System.out.println("end");

    }

    private static void test(int i) {
        try {
            System.out.println("1");
            if (i == 1) {
                throw new NullPointerException("NullPointerException");
            } else if (i == 2) {
                throw new IOException("IOException");
            }
            System.out.println("2");
//            return "try";
        } catch (IOException e) {
            System.out.println("3");
//            if (e instanceof RuntimeException) {
            throw new RuntimeException("RuntimeException");
//            }
//            System.out.println("4");
//            return "catch";
        } finally {
            System.out.println("5");
//            return;
        }
//        return "finally";

    }

}
