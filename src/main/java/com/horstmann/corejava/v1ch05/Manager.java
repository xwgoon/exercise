package com.horstmann.corejava.v1ch05;

import com.horstmann.corejava.v1ch05.inheritance.Employee;

public class Manager extends Employee {
    private double bonus;

    /**
     * @param name   the employee's name
     * @param salary the salary
     * @param year   the hire year
     * @param month  the hire month
     * @param day    the hire day
     */
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public Manager(String name, double salary, int year, int month, int day, double bonus) {
        super(name, salary, year, month, day);
        this.bonus = bonus;
    }

    public Manager() {

    }

    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }

    @Override
    public String toString() {
        return super.toString() + " Manager{" +
                "bonus=" + bonus +
                '}';
    }

    @Override
    protected int f5() {
        return 0;
    }
}