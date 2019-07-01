package com.horstmann.corejava.v1ch14;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

public class Test {

//    private static final ThreadLocal<SimpleDateFormat> simpleDateFormat =
//            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static volatile int MY_INT = 0;

    public static void main(String[] args) {

//        String s0="123";
//        String s1=null;
//
//        boolean b=(s0=s1) != null;
//        System.out.println(b);
//
//        System.out.println(s0);
//        System.out.println(s1);

//        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
//        concurrentMap.put("a", 1);
//        concurrentMap.put("b", 2);
//        System.out.println(concurrentMap);
//
//        concurrentMap.merge("b", 22, Integer::sum);
//        System.out.println(concurrentMap);

//        Integer sum = concurrentMap.reduceValues(1L, Integer::sum);
//        System.out.println(sum);
//        Runnable simpleDateFormatRunnable = () -> {
//            while (true) {
//                try {
//                    System.out.println(Thread.currentThread().toString() + simpleDateFormat.get().parse("2018-03-15"));
//                    Thread.sleep(1000L);
//                } catch (ParseException | InterruptedException e) {
//                }
//            }
//        };

//        for (int i = 0; i < 10; i++) {
//            new Thread(simpleDateFormatRunnable).start();
//        }


//        Thread t = new Thread(() -> {
//            while (true) {
//                System.out.println(LocalDateTime.now());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    System.out.println(Thread.currentThread().getName() + " : InterruptedException.");
//                    break;
//                }
//            }
//        });
//        t.start();
//
//        try {
//            Thread.sleep(2000);
//            t.interrupt();
////            System.out.println(t.isInterrupted());
//        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + " : InterruptedException.");
//        }

//        new Thread(() -> {
//            int local_value = MY_INT;
//            while (local_value < 5) {
//                if (local_value != MY_INT) {
//                    System.out.println("Got Change for MY_INT : " + MY_INT);
//                    local_value = MY_INT;
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            int local_value = MY_INT;
//            while (MY_INT < 5) {
//                System.out.println("Incrementing MY_INT to " + local_value + 1);
//                MY_INT = ++local_value;
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Callable<String> callable = () -> {
            System.out.println("callable begin.");
            Thread.sleep(2000);
            System.out.println("callable end.");
            return Thread.currentThread().getName();
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        try {
            Thread.sleep(1000);
            futureTask.cancel(false);
            System.out.println(futureTask.isCancelled());
//            System.out.println(futureTask.isDone());
            System.out.println(futureTask.get(3000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
