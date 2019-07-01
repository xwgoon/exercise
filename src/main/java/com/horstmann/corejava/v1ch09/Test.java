package com.horstmann.corejava.v1ch09;

import com.horstmann.corejava.v1ch05.equals.Employee;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.*;

public class Test {

    public static void main(String[] args) {

//        List<String> strings = new ArrayList<String>() {{
//            add("张三");
//            add("李四");
//        }};
//        strings.add("王五");
//        strings.add("李四");
//
//        List<String> strings1 = new ArrayList<String>() {{
//            add("李四");
//            add("赵六");
//        }};
//
//        System.out.println("--0--");
//        strings.remove("张三");
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            String str = iterator.next();
//            strings.set(1, "abc");
//            System.out.println(str);
//            if (str.equals("李四")) {
//                iterator.remove();
//            }
//        }

//        strings.remove("张三");
//        strings.remove("李四");
//        System.out.println(strings);
//
//        System.out.println("\n--1--");
//        for (String str : strings) {
//            System.out.println(str);
//        }
//
//        System.out.println("\n--2--");
//        strings.iterator().forEachRemaining(System.out::println);
//
//        System.out.println("\n--3--");
//        strings.forEach(System.out::println);
//
//        System.out.println(strings.toArray().getClass().getTypeName());
//        Object[] strings2 = strings.toArray();
//        System.out.println(Arrays.toString(strings2));
//
//        String[] strings3 = new String[0];
//        strings.toArray(strings3);
//        System.out.println(Arrays.toString(strings3));
//        System.out.println(Arrays.toString(strings.toArray(strings3)));
//
//        System.out.println(strings.contains("李四"));
//        System.out.println(strings.containsAll(strings1));

//        strings.remove("李四");
//        strings.removeIf(it -> Objects.equals(it, "李四"));
//        strings.removeIf(Predicate.isEqual("李四"));
//        strings.removeIf("李四"::equals);
//        strings.removeAll(strings1);
//        strings.retainAll(strings1);
//        System.out.println(strings);
//
//        MyInterface<String> myInterface = new MyClass<>();
//        myInterface.test1("hello");
//
//        System.out.println(new MyClass<String>() instanceof MyInterface);
//
//        Set<String> set = new HashSet<>();
//        set.add("cc");
//        set.add("bb");
//        set.add("aa");
//        set.add("cc");
//
//        System.out.println(set);
//        Iterator<String> iterator = set.iterator();
//        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
//            System.out.println(iterator.next());
//        }
//
//        System.out.println(set.contains("aa"));
//        set.remove("bb");
//        System.out.println(set);
//        Set<Employee> treeSet = new TreeSet<>(Comparator.comparingDouble(Employee::getSalary));
//        treeSet.add(new Employee("aa", 2.23));
//        treeSet.add(new Employee("b", 2.22));
//
//        System.out.println(treeSet);

//        Queue<String> queue=new LinkedList<>();
//        queue.add("a");
//        queue.add("b");
//        queue.add("c");
//        System.out.println(queue);
//        queue.remove();
//        System.out.println(queue);
//        String s=queue.element();
//        System.out.println(s);
//        System.out.println(queue);

//        Map<String, Employee> map = new HashMap<>();
//        map.put("a", new Employee("张三"));
//        map.put("b", new Employee("李四"));
//        map.put("c", new Employee("赵六"));
//        System.out.println(map);
//        System.out.println(map.get("b"));
//        System.out.println(map);
//
//        map.merge("b", 1, Integer::sum);
//        System.out.println(map);

//        map.forEach((k, v) -> {
//            v = new Employee("王五");
//            v.setName("123");
//        });
//        System.out.println(map);

//        for(Map.Entry<String, Employee> e: map.entrySet()){
//            System.out.println(e.getKey());
//            System.out.println(e.getValue());
//            e.setValue(new Employee("王五"));
//        }
//
//        map.entrySet().forEach(it->{
//            it.setValue(new Employee("王五"));
//        });
//        System.out.println(map);
//
//        Set<String> sets = map.keySet();
//        map.values().iterator();
//        map.entrySet().iterator();
//        sets.remove("b");
//        map.values().remove(new Employee(""));
//        System.out.println(map);
//
//        Iterator<String> iterator=sets.iterator();
//        iterator.
//        while (iterator)

//        Employee employee=new Employee("张三",1.1,2017,9,5);
//        System.out.println(employee.hashCode());
//        System.out.println(System.identityHashCode(employee));
//
//        EnumSet<Weekday> weekdays = EnumSet.allOf(Weekday.class);
//        weekdays.add(Weekday.FRIDAY);
//        System.out.println(weekdays);
////
//        weekdays = EnumSet.noneOf(Weekday.class);
//        System.out.println(weekdays);
//
//        weekdays = EnumSet.range(Weekday.TUESDAY, Weekday.FRIDAY);
//        System.out.println(weekdays);
//
//        weekdays = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY, Weekday.SATURDAY);
//        System.out.println(weekdays);
//        System.out.println(EnumSet.complementOf(weekdays));
//
//        EnumMap<Weekday, String> em=new EnumMap<>(Weekday.class);
//        System.out.println(em.size());
//        em.get(Weekday.FRIDAY);
//        em.containsKey(Weekday.FRIDAY);

//        Arrays.asList();
//        Collections.singleton(null);
//        Collections.singletonList(null);
//        Collections.singletonMap(null, null);
//        Collections.emptyList();
//        List<Integer> list = new ArrayList<>();
//        Collections.unmodifiableList(list);
//        System.out.println(list.isEmpty());
//        System.out.println(list.size() == 0);
//        Integer[] a=new Integer[10];
//        Collection<Integer> c=a;
//        Collections.max(list);
//        Collections.sort();
//        list.sort();
//
//        list.add(3);
//        list.add(1);
//        list.add(5);
//        list.add(2);
//        System.out.println(list);
//
//        Collections.sort(list, Comparator.reverseOrder());
//        list.sort(Comparator.naturalOrder());
//        System.out.println(list);
//        list.replaceAll();
//        Collections.replaceAll();
//        System.out.println(list);
//        System.out.println(Collections.binarySearch(list, 2));
//        Integer[] x = new Integer[2];
//        Integer[] integers = list.toArray(x);
//
//        System.out.println(Arrays.toString(x));
//        System.out.println(Arrays.toString(integers));
//
//        Stack<String> strings = new Stack<>();
//        strings.push("aa");
//        strings.push("bb");
//        strings.push("cc");
//        System.out.println(strings);
//        System.out.println(strings.peek());
//        System.out.println(strings);
//        strings.insertElementAt("dd", 1);
//        strings.remove("bb");
//        System.out.println(strings);
//
//        BitSet bitSet = new BitSet(10);
//        System.out.println(bitSet);
//        System.out.println(bitSet.length());
//        bitSet.set(0, 10);
//        System.out.println(bitSet);
//        System.out.println(bitSet.get(1));
//        bitSet.clear(1);
//        System.out.println(bitSet);
//        System.out.println(bitSet.get(1));
//        bitSet.set(10);
//        System.out.println(bitSet);
//        bitSet.set(11);
//
//        BitSet b2=new BitSet(5);
//        b2.set(0,5);
//        b2.clear(2);
//        b2.set(10);
//        System.out.println(b2);
//
//        bitSet.andNot(b2);
//
//        System.out.println(bitSet);
//        System.out.println(bitSet.length());
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(22);
//        System.out.println(list);
//        System.out.println(list.stream().mapToInt(Integer::intValue).sum());


//
//        Set<String> set = new HashSet<>();
//        set.add("test");
//        set.add(null);
//        System.out.println(set);
//
//        Map<Integer, String> map = new TreeMap<>(Comparator.nullsLast(Comparator.naturalOrder()));
//        map.put(1, "test");
//        map.put(2, null);
//        System.out.println(map);
//        System.out.println(map.get(3));
//
//        Queue<String> queue = new ArrayDeque<>();
//        queue.add("test");
//        queue.add(null);
//        System.out.println(queue);

//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("c", 3);
//        System.out.println(map);
//        map.forEach();

//        Integer newValue = map.merge("b", 10, (v1, v2) -> {
//            System.out.println(v1);
//            System.out.println(v2);
//            return v1 + v2;
//        });

//        Integer newValue = map.compute("b", (k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//            return 22;
//        });

//        Integer newValue = map.computeIfPresent("b", (k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//            return 22;
//        });

//        Integer newValue = map.computeIfAbsent("bb", (k) -> {
//            System.out.println(k);
//            return 22;
//        });

//        System.out.println("newValue: " + newValue);
//        System.out.println(map);

//        ArrayList<String> staff = new ArrayList<>();
//        staff.add("a");
//        staff.add("b");
//        staff.add("c");
//        System.out.println("staff: " + staff);
//        Iterator<String> iterator=staff.iterator();
//        String first=iterator.next();
//        String second=iterator.next();
//        iterator.remove();
//        System.out.println(staff);

//        ArrayList<String> boss = new ArrayList<>();
//        boss = (ArrayList<String>) staff.clone();
//        System.out.println("boss: " + boss);
//
//        staff.add("d");
//        staff.set(1, "bb");
//
//        System.out.println("staff: " + staff);
//        System.out.println("boss: " + boss);

        System.identityHashCode("");


    }

//    enum Weekday {
//        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
//    }
}


//
//interface MyInterface<T> {
//
//    void test1(T t);
//
//    void test2();
//}
//
//abstract class MyImpl<T> implements MyInterface<T> {
//
//    @Override
//    public void test1(T t) {
//        System.out.println("test1(): " + t);
//    }
//}
//
//class MyClass<T> extends MyImpl<T> {
//
//    @Override
//    public void test2() {
//        System.out.println("test2() call test1(): ");
//    }
//}
