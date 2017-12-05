package com.myapp.exercise.test;

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
import java.util.*;

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

    }


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

    public static String getSaltStr(String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        return StringUtils.join(strings, "_").concat("_salt");
    }
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
