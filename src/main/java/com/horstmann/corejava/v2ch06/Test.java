package com.horstmann.corejava.v2ch06;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {

//        IntStream.range(1, 10).forEach(System.out::println);

//        LocalDate date = LocalDate.now();
//        System.out.println("Now: " + date);
//
//        System.out.println(date.with(TemporalAdjusters.lastDayOfMonth()));

//        LocalDate date = LocalDate.of(2015, 12, 28);
//        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,4);
//        System.out.println(date.get(WeekFields.ISO.weekBasedYear()));
//        System.out.println(date.get(WeekFields.ISO.weekOfWeekBasedYear()));

        System.out.println(LocalDate.now().plusDays(1).isAfter(LocalDate.now()));


    }
}
