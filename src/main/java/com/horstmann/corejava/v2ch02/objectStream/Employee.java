package com.horstmann.corejava.v2ch02.objectStream;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    private String name;
    private double salary;
    private LocalDate hireDay;
    private static Integer x = 1;
    private transient Integer y = 2;

    public Employee() {
    }

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    /**
     * Raises the salary of this employee.
     *
     * @byPercent the percentage of the raise
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return getClass().getName()
                + "[name=" + name
                + ",salary=" + salary
                + ",hireDay=" + hireDay
                + ",x=" + x
                + ",y=" + y
                + "]";
    }
}
