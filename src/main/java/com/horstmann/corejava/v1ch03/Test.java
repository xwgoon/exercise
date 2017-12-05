package com.horstmann.corejava.v1ch03;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Test {

    public static void main(String[] args) {

//        int f1 = 1;
//        float f2=3/4f;
//        double d=5.0;

//        int i=1;
//        long l=2;
//
//        Integer i_=1;
//        Long l_;
//        l=i;
//        l=i_;
//        l_=i;

//
//        double d=Math.pow(2,-3);
//        System.out.println(2e-3);
//        System.out.println(2^-3);
//        double f=0x10p10;
//        System.out.println();

//        System.out.println(2+Math.floorMod(-2,7));
//        System.out.println(Math.sqrt(1.44f));
//        System.out.println(StrictMath.sqrt(1.44f));

//         long l= 9147483648242133242L;
//        float f=1.1f;
//        double d=1.2;
//        int i=1;
//        short s=1;
//        byte b1=127;
//        char c=65535;
//
//        char x = 6_5;

//        int b=s+c;

//        System.out.println(Math.log(9)/Math.log(3));

//        int a=4;
//        int b=1;

//        if(a!=0 && b/a != 2){
//            System.out.println(a);
//        }

//        long l=Double.doubleToLongBits(0.75);
//        System.out.println(Long.toBinaryString(l));


//        float f=3401483648_3243242323_3423423423_442342340f;
//        System.out.println(f);

//        String s = "A啊\uD835\uDD6B!";
//        String s2 = "ab你好!";

//        System.out.println(s.offsetByCodePoints(0,3));

//        System.out.println("\uD835\uDD6B");
//        System.out.println(0xD835);
//        System.out.println(0xDD6B);
//        System.out.println(s.codePointAt(2));
//        System.out.println(s.codePointAt(3));
//        System.out.println(s.charAt(2));
//        System.out.println(s.charAt(3));
//        System.out.println(new String(Character.toChars(s.codePointAt(2))));
//        System.out.println(new String(Character.toChars(s.codePointAt(3))));


//        int[] codePoints = s.codePoints().toArray();
//        System.out.println(Arrays.toString(codePoints));
//        System.out.println(new String(codePoints, 0, codePoints.length));
//
//        for (int cp : codePoints) {
//            if (Character.isSupplementaryCodePoint(cp)) {
//                System.out.print("<" + new String(Character.toChars(cp)) + ">");
//            } else {
//                System.out.print((char) cp);
//            }
//        }


//        System.out.println(s.length());
//        System.out.println(s.codePointCount(0,s.length()));

//        System.out.println(s.charAt(4));
//        System.out.println(s.codePointAt(4));
//        System.out.println(s.codePointAt(5));

//        System.out.println(s.offsetByCodePoints(1,3));
//        System.out.println(s.codePointAt(s.offsetByCodePoints(1,3)));
//        System.out.println(s.offsetByCodePoints(1,4));
//        System.out.println(s.codePointAt(s.offsetByCodePoints(1,4)));
//        System.out.println((int) '\uD835');
//        System.out.println((int) '\uDD6B');
//        System.out.println(Character.isSupplementaryCodePoint(s.codePointAt(5)));
//        System.out.println(Character.isSurrogate(s.charAt(5)));
//        System.out.println();
//        System.out.println();
//
//        System.out.println(s.substring(6, 7));

//        int index=s.offsetByCodePoints(4,1);
//        System.out.println(index);
//        System.out.println(new String(Character.toChars(s.codePointAt(index))));

//        System.out.println("s的长度："+ s.length());
//        System.out.println("s的字符个数："+ s.codePointCount(0, s.length()));
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (Character.isSurrogate(c)) {
//                System.out.print("<" + s.substring(i, ++i+1) + ">");
//            } else {
//                System.out.print(c);
//            }
//        }
//        System.out.println();
//        for (int i = 0; i < s.codePointCount(0, s.length()); i++) {
//            int idx=s.offsetByCodePoints(0, i);
//            int cp = s.codePointAt(idx);
//            if (Character.isSupplementaryCodePoint(cp)) {
//                System.out.print("<" + new String(Character.toChars(cp)) + ">");
//            } else {
//                System.out.print((char) cp);
//            }
//
//        }

//        double x = 10000.0 / 3;
//        double x1 = 12.345;
//        String s=String.format("%-4.0f", x1);
//        System.out.println("123456789");
//        System.out.println("a"+s+"b");
//        System.out.println(s.length());
//        System.out.printf("%s%g", "result:",x1);
//        System.out.println(0x1.fp0);
//        System.out.println(0b1);

//        String bs="101";
//        int i=Integer.parseInt(bs,2);
//        System.out.println(i);
//        Long l=Long.parseLong(bs,2);
//        System.out.println(0b11>>1);


//        int i=Float.floatToIntBits(1.5f);
//        System.out.println(i);
//        String s=Integer.toBinaryString(i);
//        System.out.println(s);
//
//        int i2=Integer.parseInt(s,2);
//        Float f=Float.intBitsToFloat(i2);
//        System.out.println(f);

//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(Float.floatToIntBits(1)));
//
//        long l1=Double.doubleToLongBits(1.5);
//        System.out.println(l1);
//        String s1=Long.toBinaryString(l1);
//        System.out.println(s1);
//
//        long l=Long.parseLong(s1,2);
//        Double d= Double.longBitsToDouble(l);
//        System.out.println(d);

//        System.out.println(0b101>>2);
//        System.out.println(0b101*Math.pow(2,-2));
//
//        String b1 = "1.1";
//
//        int index = b1.indexOf(".");
//        if (index < 0) {
//            System.out.println(Long.parseLong(b1, 16));
//        } else {
//            StringBuilder builder = new StringBuilder(b1);
//            int len = builder.length();
//            builder.deleteCharAt(index);
//            Long l = Long.parseLong(builder.toString(), 16);
//            System.out.println(l * Math.pow(16, index - len + 1));
//        }

//        String s=String.format("%tF %<tT", new Date());
//        System.out.println(s);


//        System.out.println(System.getProperties());
//
//        Properties properties = System.getProperties();
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.nanoTime());

//        try {
//            Scanner in = new Scanner(Paths.get("words.txt"), "utf-8");
//            PrintWriter out = new PrintWriter("f:\\new.txt", "utf-8");
//            while (in.hasNextLine()) {
//                String s = in.nextLine();
//                System.out.println(s);
//                out.println(s);
//            }
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Size sz = Size.MEDIUM;
        switch (sz) {
            case SMALL:
                System.out.println("small");
//            {
                StringBuilder sb=new StringBuilder();
                sb.append("small");
                System.out.println(sb.toString());
                break;
//            }

            case MEDIUM:
//                System.out.println("medium");
//            {
//                sb=new StringBuilder();
//                sb.append("medium");
//                System.out.println(sb.toString());
//            }
//
//                break;
            case LARGE:
                System.out.println("large");
                break;
            default:
                System.out.println("none");
                break;
        }




//        test:
//        {
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < 5; j++) {
//                    if (j == 3) {
//                        break test;
//                    }
//                    System.out.println(j);
//                }
//            }
//        }

//        BigInteger bigInteger=BigInteger.valueOf(1);
//
//        System.out.println(BigDecimal.valueOf(1.235).setScale(2, RoundingMode.HALF_UP).doubleValue());

//        int[] a=new int[100];
//        int b[]=new int[100];
//        System.out.println(a.length);

//        int[] a;
//        a=new int[]{0,1,2};
//        System.out.println(a.length);
//        int[] b=new int[0];
//        int[] c=new int[2];
//        System.out.println(b.length);

//        int[] a={1,2,3};
//        int[] b=new int[5];
//        b= Arrays.copyOfRange(a,2,2);
//        System.out.println(b.length);
//        System.out.println(Arrays.toString(b));
//        b[1]=4;
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));

//        String[] a=new String[3];
//        System.out.println(Arrays.toString(a));
//        char c=0;
//        System.out.println("a"+c+"b");

//        int[] a={1,2,3};
//        int[] b={1,3,2,1,6};
//        Arrays.sort(b,2,5);
//        Arrays.fill(b,0,2,100);
//        System.out.println(Arrays.toString(b));

    }

    private enum Size {
        SMALL, MEDIUM, LARGE
    }

}