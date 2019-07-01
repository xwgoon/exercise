package com.myapp.exercise.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.horstmann.corejava.v1ch05.inheritance.Employee;
import com.horstmann.corejava.v1ch05.inheritance.Manager;
import com.myapp.exercise.bean.PhoneNumber;
import com.sun.istack.internal.NotNull;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.bm.Lang;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.io.File;
import java.io.PipedReader;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TestJava {

    public static void main(String[] args) {
//        String[] str=new String[]{};
//        Arrays.asList(str);

//        System.out.println(getSaltStr("18380460877", "2847"));

//        Long early = 1492744857757L;
//        Long after = System.currentTimeMillis();
//        System.out.println(after);
//        System.out.println((after - early) / (60 * 1000));

//        String str = "13564758475" + "1234" + "test";
//        System.out.println(str.substring(15));

//        String str = "Hello World!";
//        String[] arr = str.split("");
//        System.out.println(Arrays.toString(arr));  //[H, e, l, l, o,  , W, o, r, l, d, !]
//        System.out.println(StringUtils.join(arr, ""));  //Hello World!
//        System.out.println(String.join("", (CharSequence[]) arr));  //Hello World!
//        System.out.println(Arrays.toString(str.split("l")));  //[He, , o Wor, d!]

//        Set<String> set = new HashSet<>();
//        set.addAll(Arrays.asList("a", "b", "a", "c"));
//        System.out.println(set);
//        System.out.println(set.contains("aa"));


//        String test = "abc" + null;
//        System.out.println(test); //"abcnull"
//        System.out.println(test.equals("abcnull")); //true
//
//
//        System.out.println(formatHref("abc"));
//        System.out.println(formatHref("/qwe"));
//        System.out.println(formatHref(null));
//
//        int a = 0;
//        Integer b = null;
//        boolean bl = a == b;


//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(File.separator);

//        Path path1= Paths.get("F:\\project\\exercise", "test1", "test2");
//        Path path2= Paths.get("project", "test3");
//        System.out.println("path1: " + path1);
//        System.out.println("path2: " + path2);
//
//        System.out.println(path1.resolve("C:\\hello"));
//        System.out.println(path1.resolve("hello"));
//
//        System.out.println(path1.resolveSibling("C:\\hello"));
//        System.out.println(path1.resolveSibling("hello"));
//
//        System.out.println(path1.relativize(path2.toAbsolutePath()));
//
//        System.out.println(Paths.get("a/b/../c/./d").normalize());
//
//        System.out.println(path2.getParent());
//        System.out.println(path1.getFileName());
//        System.out.println(path2.getRoot());
//        System.out.println(Paths.get("F:\\").toFile());
//
//
//        new TestJava().fun();
//
//        String template="【中汽配】您申请的金额为#{0, number, currency}#的 开 票 操作已完成，请留意快递信息或邮箱。";
//
//        String msg= MessageFormat.format(template, 1000.3456);
//        System.out.println(msg);
//
//        System.out.println(Integer.toHexString(','));
//        System.out.println('\u002c');

//        int[] a = {0, 1, 2};
//
//        int[] b = a;
//        int[] c = a.clone();
//        int[] d = Arrays.copyOf(a, a.length);
//
//        a[1] = 123;
//
//        //a = [0, 123, 2]
//        //b = [0, 123, 2]
//        //c = [0, 1, 2]
//        //d = [0, 1, 2]
//
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//        System.out.println(Arrays.toString(c));
//        System.out.println(Arrays.toString(d));
//
//        int[] a = {1, 2, 3};
//        Object o=a;
//        System.out.println(o.getClass().getComponentType());
//        System.out.println(Array.getLength(o));
//
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(Long.decode("#10"));

//        System.out.println(BigDecimal.valueOf(1235).subtract(BigDecimal.TEN).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP));
//        List<Integer> integers=new ArrayList<>();
//        List<Integer> integers1 = integers.stream().filter(i->i>1).collect(Collectors.toList());
//        System.out.println(integers1.size());

//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.atStartOfDay());
//        System.out.println(localDate.atTime(LocalTime.MIN));
//        System.out.println(localDate.atTime(LocalTime.MAX));

//        System.out.println(3 >>> 33);
//        System.out.println(-3/2);

//        int[] a = {3, 2, 9, 4, 8, 6};
//        mySort(a);
//        System.out.println(Arrays.toString(a));

//        String s = "h";
//        System.out.println(reverse(s));
//
//        System.out.println(new StringBuilder(s).reverse());

//        System.out.println(s.length());
//        System.out.println(s.substring(1));

//        int i = 0;
//        for (foo('A'); foo('B') && (i < 2); foo('C')) {
//            i++;
//            foo('D');
//        }

//         System.out.println("当前时间："+ new Date());
//        System.out.println("当天0点时间：" + getTimesmorning());
//        System.out.println("当天24点时间：" + getTimesnight());
//        System.out.println("本周周一0点时间：" + getTimesWeekmorning());
//        System.out.println("本周周日24点时间：" + getTimesWeeknight());
//        System.out.println("本月初0点时间："+ getTimesMonthmorning());
//        System.out.println("本月未24点时间："+ getTimesMonthnight());

//        Integer i = null;
//        System.out.println(Objects.equals(i, null));

//        String s="\uD842\uDFB7";
//        System.out.println(s);
//        System.out.println(s.length());
//        System.out.println(s.codePointCount(0, s.length()));
//        System.out.println(s.charAt(0));
//        System.out.println(s.charAt(1));
//        System.out.println(String.valueOf(Character.toChars(s.codePointAt(0))));

//        String s = "\uD842\uDFB7"; //汉字"𠮷"
//        System.out.println(Integer.toHexString(s.codePointAt(0))); //"0x20bb7"
//        char c = (char) s.codePointAt(0);
//        System.out.println(Integer.toHexString(c)); //"0x0bb7"，强转时最高位"2"被舍弃了

//        long l = Long.MAX_VALUE;
//        int i = (int) l;
//        System.out.println(Long.toHexString(l));    //"0x7fffffffffffffff"
//        System.out.println(Integer.toHexString(i)); //"0xffffffff"

//        System.out.println(31%7);
//        System.out.println(7&31);

//        System.out.println(Integer.compare(-0, 0)); //0
//        System.out.println(Float.compare(-0.0f, 0.0f)); //-1
//        System.out.println(Double.compare(-0.0, 0.0)); //-1
//        System.out.println(BigDecimal.valueOf(-0.0).compareTo(BigDecimal.valueOf(0.0))); //0

//        BigDecimal b1 = new BigDecimal(0.1);
//        BigDecimal b2 = new BigDecimal(0.10);
//        System.out.println(b1); //0.1000000000000000055511151231257827021181583404541015625
//        System.out.println(b2); //0.1000000000000000055511151231257827021181583404541015625
//        System.out.println(b1.equals(b2)); //true
//        System.out.println(b1.compareTo(b2) == 0); //true
//
//        System.out.println();
//
//        BigDecimal b3 = new BigDecimal("0.1");
//        BigDecimal b4 = new BigDecimal("0.10");
//        System.out.println(b3); //0.1
//        System.out.println(b4); //0.10
//        System.out.println(b3.equals(b4)); //false
//        System.out.println(b3.compareTo(b4) == 0); //true
//
//        System.out.println();
//
//        BigDecimal b5 = BigDecimal.valueOf(0.1);
//        BigDecimal b6 = BigDecimal.valueOf(0.10);
//        System.out.println(b5); //0.1，因为Double.toString(0.1)等于"0.1"
//        System.out.println(b6); //0.1，因为Double.toString(0.10)等于"0.1"
//        System.out.println(b5.equals(b6)); //true
//        System.out.println(b5.compareTo(b6) == 0); //true
//
//        System.out.println();
//
//        System.out.println(Double.toString(0.1)); //0.1
//        System.out.println(Double.toString(0.10)); //0.1

//        Random random = new Random();
//        List<PhoneNumber> phoneNumbers = new ArrayList<>();
//        for (int i = 0; i < 1000000; i++) {
//            int areaCode = (random.nextInt(9) + 1) * 100 + random.nextInt(100);
//            int prefix = (random.nextInt(9) + 1) * 1000 + random.nextInt(1000);
//            int lineNum = (random.nextInt(9) + 1) * 1000 + random.nextInt(1000);
//            phoneNumbers.add(new PhoneNumber(areaCode, prefix, lineNum));
//        }
//
//        long start = System.currentTimeMillis();
//        Collections.sort(phoneNumbers);
//        System.out.println(System.currentTimeMillis() - start);

//        System.out.println(phoneNumbers);

//        PhoneNumber[] arr=new PhoneNumber[1];
//        arr[0]=new PhoneNumber(1,2,3);
//
////        PhoneNumber[] arr2=arr.clone();
//        PhoneNumber[] arr2=Arrays.copyOf(arr,arr.length);
//
////        arr2[0]=new PhoneNumber(4,5,6);
//        arr2[0].setAreaCode(7);
//
//        System.out.println(arr[0]);
//        System.out.println(arr2[0]);
//
//        System.out.println(arr[0].hashCode());
//        System.out.println(arr2[0].hashCode());

//        BigDecimal b1=BigDecimal.valueOf(-1);
//        BigDecimal b2=BigDecimal.valueOf(-1);
//        System.out.println(b1==b2);

        Child child = new Child();
        child.setName("张三");
        child.setAge(23);
        child.setSchool("清华大学");

//        System.out.println(JSON.toJSONString(child));

//        PropertyPreFilters.MySimplePropertyPreFilter pf = new PropertyPreFilters().new MySimplePropertyPreFilter();
//        pf.addIncludes("name", "age");
//        System.out.println(JSON.toJSONString(child, pf));
//
//        PropertyPreFilters.MySimplePropertyPreFilter pf2 = new PropertyPreFilters().new MySimplePropertyPreFilter();
//        pf2.addExcludes("age");
//        System.out.println(JSON.toJSONString(child, pf2));
//
//        String text_beanToArray = JSON.toJSONString(child, SerializerFeature.BeanToArray);
//        System.out.println(text_beanToArray);
//        Child c1 = JSON.parseObject(text_beanToArray, Child.class, Feature.SupportArrayToBean);
//        System.out.println(c1);

        String text_beanToArray = JSON.toJSONString(child);
        System.out.println(text_beanToArray);
        Child c1 = JSON.parseObject(text_beanToArray, Child.class);
        System.out.println(c1);

    }


//    static boolean foo(char c) {
//        System.out.println(c);
//        return true;
//    }
//
//    public static void mySort(int[] a) {
//        int temp;
//        for (int i = a.length - 1; i > 0; i--) {
//            for (int j = 0; j < i; j++) {
//                if (a[j] > a[j + 1]) {
//                    temp = a[j];
//                    a[j] = a[j + 1];
//                    a[j + 1] = temp;
//                }
//            }
//        }
//    }
//
//    private static String reverse(String s) {
//        return (s == null || s.length() <= 1) ? s : reverse(s.substring(1)) + s.charAt(0);
//    }


//    public void fun(){
//        list.add("hello");
//        System.out.println(list);
//    }

//    private static String formatHref(String href) {
//        if (StringUtils.isEmpty(href)) {
//            return "";
//        }
//        return href.startsWith("/") ? href : "/" + href;
//    }
//
//    public static String getSaltStr(String... strings) {
//        if (strings == null || strings.length == 0) {
//            return "";
//        }
//        return StringUtils.join(strings, "_").concat("_salt");
//    }
}
