package com.horstmann.corejava.v1ch08;

import com.horstmann.corejava.v1ch08.pair3.Pair;

import java.time.LocalDate;

public class DateInterval extends Pair<LocalDate> {

    @Override
    public LocalDate getSecond() {
        System.out.println("DateInterval getSecond()");
        return null;
    }

    @Override
    public void setSecond(LocalDate newValue) {
        System.out.println("DateInterval setSecond(LocalDate newValue)");
        super.setSecond(null);
    }

//    @Override
//    public void setSecond(Object newValue) {
//        System.out.println("DateInterval setSecond(LocalDate newValue)");
//        super.setSecond(null);
//    }
}
