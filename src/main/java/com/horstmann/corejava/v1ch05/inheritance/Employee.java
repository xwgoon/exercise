package com.horstmann.corejava.v1ch05.inheritance;

import java.time.*;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
//        throw new IllegalArgumentException();
    }

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
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

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
        throw new IllegalArgumentException();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }

//    public static void f1(){
//        System.out.println("Employee: f1");
//    }
//    public final void f2(){
//        System.out.println("Employee: f1");
//    }

//    private void f3(){
//        System.out.println("Employee: f1");
//    }
//    void f4(){
//        System.out.println("Employee: f1");
//    }
    protected int f5(){
        System.out.println("Employee: f1");
        return 0;
    }
//    public void f6(){
//        System.out.println("Employee: f1");
//    }

//    protected int f(float f){
//        System.out.println("Employee: f3");
//        return 0;
//    }
//
//    public static Employee f4(){
//        System.out.println("Employee: f4");
//        return null;
//    }

//    public void f(Manager i){
//        System.out.println("Employee: f");
////        return "Employee: f";
//    }

//    class Employee2 extends Employee{
//
//
//        public void f() {
//            Employee employee=new Employee();
//            employee.f3();
//            employee.f4();
//            employee.f5();
//            employee.f6();
//        }
//
//    }
}

//class Employee2{
//
//}
