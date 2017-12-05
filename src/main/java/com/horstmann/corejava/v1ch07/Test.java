package com.horstmann.corejava.v1ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {

    public static void main(String[] args) throws IOException{

        Child child = new Child();
        try {
            child.fun(1);
        } catch (Exception e) {
            StringWriter out = new StringWriter();
            e.printStackTrace(new PrintWriter(out));
            String s = out.toString();
            System.err.println(s);
//            e.printStackTrace();
            Exception ioException = new IOException();
            //noinspection ConstantConditions
            throw new IOException();
        }

    }
}
