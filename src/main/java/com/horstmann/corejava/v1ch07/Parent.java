package com.horstmann.corejava.v1ch07;

import java.io.EOFException;
import java.io.IOException;

public class Parent {

    private final int xx=1;
    public static int yy=11;

    public void fun(int x) throws EOFException{
        if (x == 1) {
            throw new EOFException();
        } else if (x == 2) {
            throw new NullPointerException();
        }
        System.out.println("Parent fun()");
//        return "Parent fun()";
    }

    public void fun11(){}
}
