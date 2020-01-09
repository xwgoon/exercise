package com.horstmann.corejava.v1ch14.synch;

public class MySynchTest {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws Exception {

        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (LOCK) {
                    counter.increase();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

        Thread.sleep(10000);

        System.out.println(counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public void increase() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

}
