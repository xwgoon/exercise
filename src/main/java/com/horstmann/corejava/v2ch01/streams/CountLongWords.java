package com.horstmann.corejava.v2ch01.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        long start;
        long end;
        start = System.currentTimeMillis();
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(count);

        start = System.currentTimeMillis();
        count = words.stream().filter(w -> w.length() > 12).count();
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(count);

        start = System.currentTimeMillis();
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(count);
    }
}