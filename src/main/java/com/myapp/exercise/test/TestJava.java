package com.myapp.exercise.test;

import com.alibaba.fastjson.JSON;
import com.horstmann.corejava.v1ch09.map.Employee;
import com.sun.istack.internal.NotNull;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.bm.Lang;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJava {

//    private final List<String> list=new ArrayList<>();

    public static void main(String[] args) {

        //begin
//        List<String> schedules = new ArrayList<>();
//        schedules.add("TUESDAY,BEFORE_NOON,3");
//        schedules.add("MONDAY,AFTER_NOON,5");
//
//        List<ReservationSchedule> reservationSchedules = new ArrayList<>();
//        reservationSchedules.add(new ReservationSchedule(1L, 2L, "TUESDAY", "BEFORE_NOON", 2, LocalDateTime.now(), LocalDateTime.now()));
//        reservationSchedules.add(new ReservationSchedule(2L, 3L, "WEDNESDAY", "AFTER_NOON", 2, LocalDateTime.now(), LocalDateTime.now()));
        //end

//        List<ReservationSchedule> insert = new ArrayList<>();
//        List<ReservationSchedule> update = new ArrayList<>();
//
//        for (String schedule : schedules) {
//            String[] str = schedule.split(",");
//            boolean find = false;
//            for (ReservationSchedule rs : reservationSchedules) {
//                if (str[0].equals(rs.getWeekday()) && str[1].equals(rs.getPeriod())) {//此处将str字符串转为枚举进行比较
//                    rs.setCount(Integer.valueOf(str[2]));
//                    rs.setUpdateAt(LocalDateTime.now());
//                    update.add(rs);
//                    find = true;
//                    break;
//                }
//            }
//            if (!find) {
//                insert.add(new ReservationSchedule(null, 2L, str[0], str[1], Integer.valueOf(str[2]), LocalDateTime.now(), LocalDateTime.now()));//此处将str字符串转为枚举
//            }
//
//        }


//        System.out.println(insert);
//        System.out.println(update);

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

//        Date date = new Date();
//        System.out.println(date); //Wed Jan 23 11:58:48 CST 2019
//
//        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
//
//        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime(); //Date转LocalDateTime
//        System.out.println(localDateTime); //2019-01-23T11:58:48.216
//
//        Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); //LocalDateTime转Date
//        System.out.println(date1); //Wed Jan 23 11:58:48 CST 2019
//
//        LocalDate localDate = zonedDateTime.toLocalDate(); //Date转LocalDate
//        System.out.println(localDate); //2019-01-23
//
//        Date date2 = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()); //LocalDate转Date
//        System.out.println(date2); //Wed Jan 23 00:00:00 CST 2019
//
//        System.out.println(ZoneId.systemDefault()); //Asia/Shanghai
//        System.out.println(ZoneOffset.of("+8")); //+08:00


//        Child child = new Child();
//        child.setName("张三");
//        child.setAge(23);
//        child.setSchool("清华大学");

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

//        String text_beanToArray = JSON.toJSONString(child);
//        System.out.println(text_beanToArray);
//        Child c1 = JSON.parseObject(text_beanToArray, Child.class);
//        System.out.println(c1);

//        TestJava testJava=new TestJava();
//        testJava.fun();

//        numElementsInCommon1(new HashSet<String>(),new HashSet<Integer>());
//        numElementsInCommon1(null,null);
//        numElementsInCommon2(new HashSet<String>(),new HashSet<Integer>());
//        numElementsInCommon2(null,null);

//        fun(Integer.class, 1);

//        Map<Class, Object> map = new HashMap<>();
//        map.put(String.class, "a");
//        map.put(Integer.class, 1);
//        System.out.println(map.get(String.class));
//        System.out.println(map.get(Integer.class));
//
//        Class<String> stringClass = String.class;
//        Class<String> stringClass1 = String.class;
//        System.out.println(stringClass == stringClass1);

//        String[] strings1=toArray("aa", "bb");

//        String[] strings=pickTwo("aa", "bb", "cc");
//        Integer[] integers=pickTwo(1, 2, 3);

//        List<Parent> parentList = new ArrayList<>();
//        parentList.add(new Parent(10));
//        parentList.add(new Parent(60));
//        parentList.add(new Parent(30));
//        parentList.add(new Parent(90));
//        Random random = new Random();
//        for (int i = 0; i < 10_000_000; i++) {
//            parentList.add(new Parent(random.nextInt(100)));
//        }

//        long start = System.currentTimeMillis();
//        List<Integer> nameList0 = parentList.stream().filter(it -> it.getAge() > 50).map(Parent::getAge).collect(Collectors.toList());
//        System.out.println("nameList0: " + (System.currentTimeMillis() - start));
//        System.out.println(nameList0.size());
//
//        start = System.currentTimeMillis();
//        List<Integer> nameList1 = parentList.stream().map(Parent::getAge).filter(it -> it > 50).collect(Collectors.toList());
//        System.out.println("nameList1: " + (System.currentTimeMillis() - start));
//        System.out.println(nameList1.size());

//        start = System.currentTimeMillis();
//        List<Integer> nameList2 = parentList.stream().flatMap(it -> it.getAge() > 50 ? Stream.of(it.getAge()) : Stream.empty()).collect(Collectors.toList());
//        System.out.println("nameList2: " + (System.currentTimeMillis() - start));
//        System.out.println(nameList2.size());

    }

//    private static <T> T[] pickTwo(T a, T b, T c){
//        switch (ThreadLocalRandom.current().nextInt(3)){
//            case 0: return toArray(a,b);
//            case 1: return toArray(a,c);
//            case 2: return toArray(b,c);
//        }
//        throw new AssertionError();
//    }
//
//    private static <T> T[] toArray(T... args){
//        System.out.println(args);
//        return args;
//    }

//    public class Stack<E> {
//        public Stack(){};
//        public void push(E e){};
//        public E pop(){return null;};
//        public boolean isEmpty(){return false;};
//
//        public void pushAll(Iterable<? extends E> src) {
//            for (E e : src)
//                push(e);
//        }
//    }

//    static int numElementsInCommon1(Set s1, Set s2) {
//        int result = 0;
//        for (Object o1 : s1)
//            if (s2.contains(o1))
//                result++;
//        return result;
//    }
//
//    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
//        int result = 0;
//        for (Object o1 : s1)
//            if (s2.contains(o1))
//                result++;
//        return result;
//    }
//
//    private void fun(){
//
//        Child parent=new Child();
//
//
//    }
//
//    private static class Parent{
//
//        public Parent(){
//            print();
//        }
//
//        public static void print(){
//            System.out.println("Parent.print()");
//        }
//    }
//
//    private static class Child extends Parent{
//
//        public Child(){
//            print();
//        }
//
//        public static void print(){
//            System.out.println("Child.print()");
//        }
//
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

//    public static String getSaltStr(String... strings) {
//        if (strings == null || strings.length == 0) {
//            return "";
//        }
//        return StringUtils.join(strings, "_").concat("_salt");
//    }
//
//    private static class ReservationSchedule {
//
//        private Long id;
//        private Long doctorId;
//        private String weekday;
//        private String period;
//        private Integer count;
//        private LocalDateTime createAt;
//        private LocalDateTime updateAt;
//
//        ReservationSchedule() {
//        }
//
//        ReservationSchedule(
//                Long id,
//                Long doctorId,
//                String weekday,
//                String period,
//                Integer count,
//                LocalDateTime createAt,
//                LocalDateTime updateAt
//        ) {
//            this.id = id;
//            this.doctorId = doctorId;
//            this.weekday = weekday;
//            this.period = period;
//            this.count = count;
//            this.createAt = createAt;
//            this.updateAt = updateAt;
//        }
//
//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public Long getDoctorId() {
//            return doctorId;
//        }
//
//        public void setDoctorId(Long doctorId) {
//            this.doctorId = doctorId;
//        }
//
//        public String getWeekday() {
//            return weekday;
//        }
//
//        public void setWeekday(String weekday) {
//            this.weekday = weekday;
//        }
//
//        public String getPeriod() {
//            return period;
//        }
//
//        public void setPeriod(String period) {
//            this.period = period;
//        }
//
//        public Integer getCount() {
//            return count;
//        }
//
//        public void setCount(Integer count) {
//            this.count = count;
//        }
//
//        public LocalDateTime getCreateAt() {
//            return createAt;
//        }
//
//        public void setCreateAt(LocalDateTime createAt) {
//            this.createAt = createAt;
//        }
//
//        public LocalDateTime getUpdateAt() {
//            return updateAt;
//        }
//
//        public void setUpdateAt(LocalDateTime updateAt) {
//            this.updateAt = updateAt;
//        }
//
//        public String toString() {
//            StringBuilder sb = new StringBuilder("ReservationSchedule (");
//
//            sb.append(id);
//            sb.append(", ").append(doctorId);
//            sb.append(", ").append(weekday);
//            sb.append(", ").append(period);
//            sb.append(", ").append(count);
//            sb.append(", ").append(createAt);
//            sb.append(", ").append(updateAt);
//
//            sb.append(")");
//            return sb.toString();
//        }
//    }
}
