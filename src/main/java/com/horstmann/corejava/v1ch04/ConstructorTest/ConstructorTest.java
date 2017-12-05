package com.horstmann.corejava.v1ch04.ConstructorTest;

import java.util.*;

/**
 * This program demonstrates object construction.
 *
 * @author Cay Horstmann
 * @version 1.01 2004-02-19
 */
public class ConstructorTest {
    public static void main(String[] args) {

        System.out.println("nextId:" + Employee.nextId);

        // fill the staff array with three Employee objects
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

//         print out information about all Employee objects
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary="
                    + e.getSalary());


    }
}

class Employee {
    public static int nextId = getNextId();

    private int id;
    private String name = initializeName(); // instance field initialization
    private double salary;

    private static int getNextId() {
        System.out.println("getNextId()");
        return 1;
    }

    private String initializeName() {
        System.out.println("initializeName()");
        return "Tom";
    }

    // static initialization block
    static {
        Random generator = new Random();
        // set nextId to a random number between 0 and 9999
        nextId = generator.nextInt(10000);
        System.out.println("static initialization");
    }

    // object initialization block
    {
        id = nextId;
        nextId++;
        System.out.println("object initialization");
    }

    // three overloaded constructors
    public Employee(String n, double s) {
        name = n;
        salary = s;
        System.out.println("constructor Employee(String n, double s)");
    }

    public Employee(double s) {
        // calls the Employee(String, double) constructor
        this("Employee #" + nextId, s);
        System.out.println("constructor Employee(double s)");
    }

    // the default constructor
    public Employee() {
        // name initialized to ""--see above
        // salary not explicitly set--initialized to 0
        // id initialized in initialization block
        System.out.println("constructor Employee()");
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
