package com.horstmann.corejava.v1ch05;

import com.horstmann.corejava.v1ch05.inheritance.Employee;
import com.horstmann.corejava.v1ch05.inheritance.Manager;
import com.horstmann.corejava.v2ch04.socket.SocketTest;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import org.omg.CORBA.IntHolder;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) {

//        Employee e = new Employee("张三", 50000, 2017, 8, 6);
//        e = new Manager("李四", 60000, 2016, 6, 7, 5000);
//        System.out.println();
//
//        Manager m=new Manager("李四", 60000, 2016, 6, 7, 5000);
//
//
//        com.horstmann.corejava.v1ch05.Manager manager=new com.horstmann.corejava.v1ch05.Manager("李四", 60000, 2016, 6, 7, 5000);
//        manager.f4();

//        Manager[] managers=new Manager[10];
//        Employee[] employees=new Employee[5];
//        employees=managers;
//        employees[0]=new Manager("张三", 50000, 2017, 8, 6);
//        System.out.println(managers[0].toString());

//        ArrayList<Integer> list=new ArrayList<>();
//        list.add(2);
//        list.set(0, 1);
//        System.out.println(list);

//        Integer a=1000;
//        Integer b=1000;
//        Character c=128;
//        Character d=128;
//        Boolean e=Boolean.TRUE;
//        Boolean f=127;
//        System.out.println(e==f);

//        Integer i=10;
//        System.out.println(i.toString());
//        System.out.println(Integer.toString(10));
//        System.out.println(Integer.toOctalString(10));

//        Integer n=1;
//        Double x=2.0;
//        System.out.println(true?n:x);
//        System.out.println(n+x);
//        int a=1;
//        float b=2;
//        byte c = true?a:b;
//        int d=b%a;
//        System.out.println(c);

//        Integer a = 2;
//        triple(2);
//        System.out.println(a);
//
//        IntHolder b = new IntHolder(2);
//        triple(b);
//        System.out.println(b.value);

//        Object x=new Employee();
//        System.out.println(x);
//
//        int i=1;
//        Object o=i;
//        i=(Integer) o;

//        Employee employee = new Employee("张三", 20000, 2017, 8, 12);
//        employee.raiseSalary(2);
//        Class cl = employee.getClass();
//        String name = cl.getName();
//        System.out.println(name);
//
//        System.out.println(cl == Employee.class);

//        try {
//            Class newCl = Class.forName("com.horstmann.corejava.v1ch05.inheritance.Employee");
//
//            //调用无参构造方法
//            Object obj1 = newCl.newInstance();
//            System.out.println(obj1);
//
//            //调用有参构造方法
//            Constructor constructor = newCl.getConstructor(String.class, double.class, int.class, int.class, int.class);
//            Object obj2 = constructor.newInstance("张三", 20000, 2017, 8, 12);
//            System.out.println(obj2);
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

//        Class cl1 = Employee.class;
//        System.out.println(cl1.getName());
//        Class cl2 = float.class;
//        System.out.println(cl2.getName());
//        Class cl3 = Float[].class;
//        System.out.println(cl3.getName());

//        Class clazz0 = Integer.class;
//        System.out.println(clazz0.isPrimitive());
//        System.out.println(clazz0.getName());
//        System.out.println(clazz0.getSimpleName());
//        System.out.println(clazz0.getTypeName());
//        System.out.println(clazz0.getCanonicalName());
//        System.out.println(clazz0.getComponentType());
//        System.out.println();
//
//        Class clazz1 = int.class;
//        System.out.println(clazz1.isPrimitive());
//        System.out.println(clazz1.getName());
//        System.out.println(clazz1.getSimpleName());
//        System.out.println(clazz1.getTypeName());
//        System.out.println(clazz1.getCanonicalName());
//        System.out.println(clazz1.getComponentType());
//        System.out.println();
//
//        Class clazz2 = Double[].class;
//        System.out.println(clazz2.isArray());
//        System.out.println(clazz2.getName());
//        System.out.println(clazz2.getSimpleName());
//        System.out.println(clazz2.getTypeName());
//        System.out.println(clazz2.getCanonicalName());
//        System.out.println(clazz2.getComponentType());

//        System.out.println(Array.getLength(new int[5]));

//        Employee employee = new Employee("张三", 10000, 2017, 8, 12);
//
//        try {
//            Class clazz = Employee.class;
//
//            Field[] fields = clazz.getDeclaredFields();
//            AccessibleObject.setAccessible(fields, true);
//            for (Field field : fields) {
////                field.setAccessible(true);
//                Object name = field.get(employee);
//                System.out.println(name);
//            }
//            System.out.println();
//
//            Field field = clazz.getDeclaredField("name");
//            field.setAccessible(true);
//            Object name = field.get(employee);
//            System.out.println(name);
//            System.out.println(employee);
//
////            field.setAccessible(false);
//            field.set(employee, "李四");
//            System.out.println(field.get(employee));
//            System.out.println(employee);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        Manager[] managers= new Manager[10];
//        managers[0] = new Employee();
//        Employee[] employees = managers;
//        employees[0] = new Employee();
//
//        managers=(Manager[]) employees;


//        Employee[] employees = new Employee[]{new Employee("张三", 50000, 2017, 8, 6)};
//        System.out.println(employees.length);
//        System.out.println(employees[0]);
////
//        Object[] objects=new Object[10];
//        System.arraycopy(employees, 0, objects, 0, employees.length);
//        System.out.println(objects.length);
//        System.out.println(objects[0]);
////
////        employees=(Employee[]) objects;
////        System.out.println(employees.length);
////        System.out.println(employees[0]);
//

//        Employee[] employees = new Employee[10];
//        Manager[] managers=(Manager[]) employees;

//        employees[0] = new Object();

//        Object[] objects = new Object[10];

//        Object[] objects = employees;
//        objects[0] = new Object();

//        employees = (Employee[]) objects;


//        employees = (Employee[]) objects;
//        System.out.println(employees.length);
//        System.out.println(employees[0]);
//        Employee[] employees = new Employee[]{new Employee("张三", 1000, 2017, 8, 13)};
//        System.out.println(employees.length);
//        System.out.println(employees[0]);
//        System.out.println(employees.getClass().getComponentType());
//        System.out.println("1------------------");
//
//        employees = (Employee[]) badCopyOf(employees, 10);
//        System.out.println(employees.length);
//        System.out.println(employees[0]);
//        System.out.println(employees.getClass().getComponentType());

//        int[] a = new int[10];
//        int[] b = new int[5];
//        System.arraycopy(a, 9, b, 2, 2);
//
//        Integer[] x=new Integer[10];
//        Object[] y=new Object[10];
//        y=x;

//        int[] a = { 1, 2, 3 };
//        a = Arrays.copyOf(a, 10);
//        System.out.println(Arrays.toString(a));

//        Manager[] managers=new Manager[10];
//        Employee[] employees=new Employee[10];
////        List<Manager> managers=(List<Manager>) employees;
//        managers=(Manager[]) employees;
//        employees=managers;


    }

//    public static Object[] badCopyOf(Object[] a, int newLength) {
//
//        System.out.println(a.length);
//        System.out.println(a[0]);
//        System.out.println(a.getClass().getComponentType());
//        System.out.println("2------------------");
//
//        Object[] newArray = new Object[1];
//        System.out.println(newArray.getClass().getTypeName());
////        Object[] newArray = new Object[newLength];
////        System.out.println(newArray.getClass().getComponentType());
//        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
////        newArray = a;
//        newArray[0] = new Employee("李四", 1000, 2017, 8, 13);
//        System.out.println(newArray[0]);
//        System.out.println(newArray.getClass().getTypeName());
//        System.out.println(newArray[0].getClass().getTypeName());
//
//        Employee e = (Employee) newArray[0];
//        System.out.println(e);
//        Employee[] employees=(Employee[]) newArray;
//
//
//
////        System.out.println(newArray.length);
////        System.out.println(newArray[0]);
////        System.out.println(newArray.getClass().getTypeName());
//        System.out.println("3------------------");
//
//        return newArray;
//    }
//
//    public void t1(int[] a){
//        t2(a);
//    }
//
//    private void t1(int... a){
//        t2(a);
//    }
//
//    public void t2(Object a){
//
//    }
//
//    public static void triple(Integer x) {
//        x = 3 * x;
//        System.out.println(x);
//    }
//
//    public static void triple(IntHolder x) {
//        x.value = 3 * x.value;
//        System.out.println(x.value);
//    }

//    public void p() {
//        System.out.println(hashCode());
//        System.out.println("".hashCode());
//        System.out.println("".length());
//        System.out.println(Objects.hash("test", 1));

//    }
}

//class T2 extends T1{
//    @Override
//    public void t1(int... a) {
//        super.t1(a);
//    }
//}
//
//class T1{
//    public void t1(int[] a){
//
//    }
//}




