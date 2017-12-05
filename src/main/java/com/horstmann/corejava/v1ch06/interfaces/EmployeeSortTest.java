package com.horstmann.corejava.v1ch06.interfaces;

import java.util.*;

/**
 * This program demonstrates the use of the Comparable interface.
 *
 * @author Cay Horstmann
 * @version 1.30 2004-02-27
 */
public class EmployeeSortTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[5];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl", 75000);
        staff[2] = new Employee("Tester", 38000);
        staff[3] = new Employee("Tester", 10000);
        staff[4] = new Employee(null, 20000);

//        Arrays.sort(staff);

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());

        System.out.println();
//        Arrays.sort(staff, new ComparatorTest());
//        Arrays.sort(staff, (e1, e2) -> e1.getName().compareTo(e2.getName()));
//        Arrays.sort(staff, Comparator.comparing(Employee::getName));
//        Arrays.sort(staff, Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary));
//        Arrays.sort(staff, Comparator.comparing(Employee::getName, (s, t) -> Integer.compare(s.length(), t.length())));
//        Arrays.sort(staff, (e1, e2) -> Integer.compare(e1.getName().length(), e2.getName().length()));
//        Arrays.sort(staff, Comparator.comparingInt(p -> p.getName().length()));
        Arrays.sort(staff, Comparator.comparing(Employee::getName, Comparator.nullsLast(Comparator.naturalOrder())));
//        Arrays.sort(staff, Comparator.comparing(Employee::getName).reversed());

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
    }

}

class ComparatorTest implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
//        return o1.getName().length() - o2.getName().length();
        return o1.getName().compareTo(o2.getName());
    }
}