package com.horstmann.corejava.v2ch01.parallel;

import static java.util.stream.Collectors.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ParallelStreams
{
   public static void main(String[] args) throws IOException
   {
      String contents = new String(Files.readAllBytes(
            Paths.get("alice30.txt")), StandardCharsets.UTF_8);
      List<String> wordList = Arrays.asList(contents.split("\\PL+"));

      // Very bad code ahead
      int[] shortWords = new int[10];
      wordList.parallelStream().forEach(s ->
         {
            if (s.length() < 10) shortWords[s.length()]++;
         });
      System.out.println(Arrays.toString(shortWords));

      // Try again--the result will likely be different (and also wrong)
      Arrays.fill(shortWords, 0);
      wordList.parallelStream().forEach(s ->
         {
            if (s.length() < 10) shortWords[s.length()]++;
         });
      System.out.println(Arrays.toString(shortWords));

      // Remedy: Group and count
      Map<Integer, Long> shortWordCounts = wordList.parallelStream()
         .filter(s -> s.length() < 10)
         .collect(groupingBy(String::length, counting()));

      System.out.println(shortWordCounts);

      // Downstream order not deterministic
      Map<Integer, List<String>> result = wordList.parallelStream().collect(
         Collectors.groupingByConcurrent(String::length));

      System.out.println(result.get(14));

      result = wordList.parallelStream().collect(
         Collectors.groupingByConcurrent(String::length));

      System.out.println(result.get(14));

      Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
         groupingByConcurrent(String::length, counting()));

      System.out.println(wordCounts);

      long time = System.currentTimeMillis();
      Map<Integer, Long> test1 = wordList.parallelStream().collect(
              groupingBy(String::length, counting()));
      System.out.println("groupingBy: " + (System.currentTimeMillis() - time));

      time=System.currentTimeMillis();
      Map<Integer, Long> test2 = wordList.parallelStream().collect(
              groupingByConcurrent(String::length, counting()));
      System.out.println("groupingByConcurrent: " + (System.currentTimeMillis() - time));


      Map<Integer, List<String>> test3 = wordList.parallelStream().unordered().collect(
              Collectors.groupingBy(String::length));

      System.out.println(test3.get(14));

      Map<Integer, List<String>> test4 = wordList.parallelStream().unordered().collect(
              Collectors.groupingBy(String::length));

      System.out.println(test4.get(14));


   }
}
