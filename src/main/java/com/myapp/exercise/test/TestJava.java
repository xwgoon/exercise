package com.myapp.exercise.test;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestJava {

//    private final List<String> list=new ArrayList<>();

//    private Map<Class, Object> favorites = new HashMap<>();
//    public <T> void putFavorite(Class<T> type, T instance) {
//        favorites.put(Objects.requireNonNull(type), instance);
//    }
//    public <T> T getFavorite(Class<T> type) {
//        return type.cast(favorites.get(type));
//    }

//    private static int nextSerialNumber = 0;
//    private static final AtomicInteger nextSerialNumber = new AtomicInteger();

    public static void main(String[] args) throws Exception {
//        TestJava f = new TestJava();
//        f.putFavorite(String.class, "Java");
//        f.putFavorite(Integer.class, 0xcafebabe);
//        f.putFavorite(Class.class, TestJava.class);
////        f.putFavorite(null, TestJava.class);
//
//        String favoriteString = f.getFavorite(String.class);
//        int favoriteInteger = f.getFavorite(Integer.class);
//        Class<?> favoriteClass = f.getFavorite(Class.class);
////        Class<?> favoriteClass1 = f.getFavorite(null);
//        System.out.printf("%s %x %s%n", favoriteString,
//                favoriteInteger, favoriteClass.getName());
//
//        Map<?,Object> map=new HashMap<>();
//        map.put(null,1);
//
//        System.out.println();

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
//
//        SimplePropertyPreFilter pf1 = new SimplePropertyPreFilter();
//        pf1.getExcludes().add("name");
//        System.out.println(JSON.toJSONString(child, pf1));

//        SimplePropertyPreFilter pf2 = new SimplePropertyPreFilter();
//        pf2.getIncludes().add("name");
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


//        int n=8;
//        String s1="来得及伐的看法";
//        String s2="违法多少啦";
//
//        int s1Hash=s1.hashCode();
//        System.out.println(s1Hash);
//        int s2Hash=s2.hashCode();
//        System.out.println(s2Hash);
//        System.out.println((n-1)&s1Hash);
//        System.out.println(s1Hash%n);
//        System.out.println((n-1)&s2Hash);
//        System.out.println(s2Hash%n);

//        Map<Integer, Object> map=new LinkedHashMap<Integer, Object>(){
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<Integer, Object> eldest) {
//                return size()>3;
//            }
//        };
//
//        map.put(1,"a");
//        map.put(2,"b");
//        map.put(2,"c");
//        map.put(3,"d");
//        System.out.println(map.get(1));
//        map.put(4,"e");
//
//        System.out.println(map);

//        String s = "bac";
//        String s = "bacslklaoeiwszdlsafijewfaskifjhewoflashfowlafksodfhebppkeforjgtnnvuyyqakeufr";
//        String r = s.chars().sorted().collect(
//                StringBuilder::new,
//                (sb, c) -> {
//                    System.out.println("2: "+Thread.currentThread());
//                    sb.append((char) c);
//                },
//                (r1,r2)->{
//                    System.out.println("3: "+Thread.currentThread()+", r1: "+r1+", r2: "+r2);
//                    r1.append(r2);
//                })
//                .toString();
//        System.out.println(r);

//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        String s = null;
//        list.forEach(it -> {
//            if ("b".equals(it)) {
////                s=it; // 编译报错，lambda中必须使用常量或effectively常量，其实就是不能再次用等号对变量进行赋值
////                return; // 结束本次循环，继续下次循环，作用相当于普通循环中的continue
////                continue; // 编译报错，不能在此使用
////                break; // 编译报错，不能在此使用
////                throw new RuntimeException(); // 结束整个循环，作用相当于普通循环中的break
////                throw new Exception(); // 编译报错，因为Consumer接口的accept方法未声明checked异常
//            }
//            System.out.println(Thread.currentThread() + it);
//        });

//        Collection<?>[] collections = {
//                new HashSet<String>(),
//                new ArrayList<BigInteger>(),
//                new HashMap<String, String>().values()
//        };
//        for (Collection<?> c : collections) {
//            System.out.println(classify(c));
//        }
//
//        Wine[] wines = {
//                new Wine(),
//                new SparklingWine(),
//                new Champagne()
//        };
//
//        for (Wine wine : wines) {
//            System.out.println(wine.name());
//        }

//        new Thread(System.out::println).start();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit((Runnable) System.out::println);

//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        List<String> list3 = new ArrayList<>();
//        List<String> list4 = new ArrayList<>();
//        List<String> list5 = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list1.add(String.valueOf(i));
//            list2.add(String.valueOf(i));
//            list3.add(String.valueOf(i));
//            list4.add(String.valueOf(i));
//            list5.add(String.valueOf(i));
//        }
//
//        long start = System.currentTimeMillis();
//        list1.removeIf(it -> it.contains("4"));
//        System.out.println(list1.size() + ", " + (System.currentTimeMillis() - start));
//
//        start = System.currentTimeMillis();
//        for (int i = list2.size() - 1; i >= 0; i--) {
//            String val = list2.get(i);
//            if (val.contains("4")) {
//                list2.remove(i);
//            }
//        }
//        System.out.println(list2.size() + ", " + (System.currentTimeMillis() - start));
//
//        start = System.currentTimeMillis();
//        for (Iterator<String> i = list3.iterator(); i.hasNext(); ) {
//            if (i.next().contains("4")) {
//                i.remove();
//            }
//        }
//        System.out.println(list3.size() + ", " + (System.currentTimeMillis() - start));
//
//        start = System.currentTimeMillis();
//        for (int i = list4.size() - 1; i >= 0; i--) {
//            String val = list4.get(i);
//            if (val.contains("4")) {
//                list4.remove(val);
//            }
//        }
//        System.out.println(list4.size() + ", " + (System.currentTimeMillis() - start));
//
//        start = System.currentTimeMillis();
//        List<String> result = new ArrayList<>();
//        for (int i = 0, n = list5.size(); i < n; i++) {
//            String val = list5.get(i);
//            if (!val.contains("4")) {
//                result.add(val);
//            }
//        }
//        System.out.println(result.size() + ", " + (System.currentTimeMillis() - start));

//        long start=System.currentTimeMillis();
//        Integer[] arr1=list.toArray(new Integer[0]);
//        System.out.println(System.currentTimeMillis()-start);
//
//        start=System.currentTimeMillis();
//        Integer[] arr2=list.toArray(new Integer[list.size()]);
//        System.out.println(System.currentTimeMillis()-start);
//        C1 c1 = new C1();
//        C2 c2 = new C2();
//        try {
//            c1.test();
//            c2.test();
//        } finally {
//            try {
//                c2.close();
//                c1.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        try (C1 c1 = new C1(); C2 c2 = new C2(); C0 c0 = new C0()) {
//            c0.test();
//            c1.test();
//            c2.test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        List<String> list=new CopyOnWriteArrayList<>();
////        List<String> list=new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("b");
//        list.add("c");
//        for (String s : list) {
//            if("b".equals(s)){
//                list.remove(s);
//            }
//        }
//
//        System.out.println(list);

//        Map<Integer, Object> map=new ConcurrentHashMap<>();
//        Runnable runnable=()->{
//            for(int i=0;i<1000;i++){
//                map.put(generateSerialNumber(),"");
//            }
//        };
//
//        for(int i=0;i<10;i++){
//            new Thread(runnable).start();
//        }
//
//        Thread.sleep(5000);
//        System.out.println(map.size());

        fun1();

    }

    private static final Object lock = new Object();

    private static void fun1() throws ExecutionException, InterruptedException {
        System.out.println("fun1开始");
        synchronized (lock) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(TestJava::fun2).get();
            executorService.shutdown();
//            fun2();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("fun1结束");
    }

    private static void fun2() {
        System.out.println("fun2开始");
        synchronized (lock) {
            System.out.println("fun2获取到锁");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fun2结束");
    }

//    public static synchronized int generateSerialNumber() {
//        return nextSerialNumber++;
//    }

//    public static int generateSerialNumber() {
//        return nextSerialNumber.getAndIncrement();
//    }
//
//    static class C1 implements AutoCloseable {
//        @Override
//        public void close() throws Exception {
//            System.out.println("C1.close()");
//        }
//
//        public void test() {
//            System.out.println("C1.test()");
//        }
//    }
//
//    static class C2 implements AutoCloseable {
//        @Override
//        public void close() throws Exception {
//            System.out.println("C2.close()");
//        }
//
//        public void test() {
//            System.out.println("C2.test()");
//        }
//    }
//
//    static class C0 implements AutoCloseable {
//        @Override
//        public void close() throws Exception {
//            System.out.println("C0.close()");
//        }
//
//        public void test() {
//            System.out.println("C0.test()");
//        }
//    }

//    static class Wine {
//        String name() {
//            return "wine";
//        }
//    }
//
//    static class SparklingWine extends Wine {
//        @Override
//        String name() {
//            return "sparkling wine";
//        }
//    }
//
//    static class Champagne extends SparklingWine {
//        @Override
//        String name() {
//            return "champagne";
//        }
//    }

//    static String classify(Set<?> s) {
//        return "Set";
//    }
//
//    static String classify(List<?> lst) {
//        return "List";
//    }
//
//    static String classify(Collection<?> c) {
//        return "Unknown Collection";
//    }

//    static String classify(Collection<?> c) {
//        return c instanceof Set ? "Set" :
//                c instanceof List ? "List" : "Unknown Collection";
//    }

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
