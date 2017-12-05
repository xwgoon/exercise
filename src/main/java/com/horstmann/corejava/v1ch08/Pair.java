package com.horstmann.corejava.v1ch08;

import com.horstmann.corejava.v1ch05.inheritance.Employee;

import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Cay Horstmann
 * @version 1.00 2004-05-10
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

//    public boolean equals(T obj) {
//        return first.equals(obj);
//    }

    public static <T extends Employee> Pair<T> makePair0(Supplier<T> supplier) {
        return new Pair<>(supplier.get(), supplier.get());
    }

    public static <T extends Employee> Pair<T> makePair1(Class<T> clazz) {
        try {
            return new Pair<>(clazz.newInstance(), clazz.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Comparable> T[] makePair2(T... a){
        T[] mm=a;
        return mm;
    }
}
