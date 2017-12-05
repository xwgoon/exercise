package com.horstmann.corejava.v1ch07;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class Child extends Parent {

    private final int xx = 2;
    public static final int yy = 22;

    public static void main(String[] args) {
    }

    @Override
    public void fun(int x) throws EOFException {
        System.out.println("Child fun()");
//        throw new RuntimeException("oops");
        throw new EOFException("oops");
//        return "Child fun()";
    }

    public void fun22() {
        fun11();
        System.out.println(yy);
    }
}
