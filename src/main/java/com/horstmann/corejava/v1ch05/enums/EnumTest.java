package com.horstmann.corejava.v1ch05.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Stream;

/**
 * This program demonstrates enumerated types.
 *
 * @author Cay Horstmann
 * @version 1.0 2004-05-24
 */
public class EnumTest {
    public static void main(String[] args) {
//      Scanner in = new Scanner(System.in);
//      System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
//      String input = in.next().toUpperCase();
//      Size size = Enum.valueOf(Size.class, input);
//      System.out.println("size=" + size);
//      System.out.println("abbreviation=" + size.getAbbreviation());
//      if (size == Size.EXTRA_LARGE)
//         System.out.println("Good job--you paid attention to the _.");


//      Size s=new Size("123");
//      System.out.println(Size.SMALL.equals(Size.SMALL));
//      System.out.println(Size.SMALL == Size.SMALL);

//        System.out.println(Size.LARGE.toString());
//        System.out.println(Size.LARGE);
//        System.out.println(Size.LARGE.name());
//        System.out.println(Size.LARGE.ordinal());
//        System.out.println(Size.LARGE.compareTo(Size.EXTRA_LARGE));
//
//        System.out.println(Enum.valueOf(Size.class, "LARGE"));
//        System.out.println(Size.valueOf("LARGE"));
//        System.out.println(Arrays.toString(Size.values()));

//        Arrays.asList(Size.values()).stream().filter();
//        Stream.of(Size.values()).

//        byte b = 0x7f; //正确
//        short s = 0x7fff; //正确
//        int i = 0x7fffffff; //正确
//        long l = 1; //正确
//        float f = 1; //正确
//        double d = 1; //正确
//
//        Byte B = 1; //正确
//        Short S = 1; //正确
//        Integer I = 1; //正确
//        Long L = 1L; //编译报错，须写成 1L（1l）
//        Float F = 1f; //编译报错，须写成 1f（1F）
//        Double D = 1d; //编译报错，须写成 1d（1D或1.）

        System.out.println(StringUtils.leftPad(Integer.toBinaryString(-1 << 3 >>> 2), 32, "0"));
//        System.out.println(Integer.toBinaryString(-1<<1));
//        System.out.println(Integer.toBinaryString(-1<<-31));


    }
}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    private String abbreviation;
}
