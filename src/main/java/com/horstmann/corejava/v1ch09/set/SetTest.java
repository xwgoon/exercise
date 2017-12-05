package com.horstmann.corejava.v1ch09.set;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * This program uses a set to print all unique words in System.in.
 *
 * @author Cay Horstmann
 * @version 1.12 2015-06-21
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>(); // HashSet implements Set
        long totalTime = 0;

        try (Scanner in = new Scanner(new FileInputStream("alice30.txt"), "UTF-8")) {
            while (in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++)
            System.out.println(iter.next());
        System.out.println(". . .");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
    }
}
