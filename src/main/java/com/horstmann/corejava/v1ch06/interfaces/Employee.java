package com.horstmann.corejava.v1ch06.interfaces;

public class Employee
{
   private String name;
   private double salary;

   public Employee(String name, double salary)
   {
      this.name = name;
      this.salary = salary;
   }

   public Employee(){}

   public String getName()
   {
      return name;
   }

   public double getSalary()
   {
      return salary;
   }

   public void raiseSalary(double byPercent)
   {
      double raise = salary * byPercent / 100;
      salary += raise;
   }

//   /**
//    * Compares employees by salary
//    * @param other another Employee object
//    * @return a negative value if this employee has a lower salary than
//    * otherObject, 0 if the salaries are the same, a positive value otherwise
//    */
//   public int compareTo(Employee other)
//   {
//      return Double.compare(salary, other.salary);
//   }


   public void setName(String name) {
      this.name = name;
   }

   public void setSalary(double salary) {
      this.salary = salary;
   }

   @Override
   public String toString() {
      return "Employee{" +
              "name='" + name + '\'' +
              ", salary=" + salary +
              '}';
   }
}