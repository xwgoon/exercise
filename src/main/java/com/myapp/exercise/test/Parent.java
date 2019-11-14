package com.myapp.exercise.test;

public class Parent {

    private static String id = "123"; //①
    private String name = "张三"; //③
    private Integer age;

    static {
        System.out.println("static block"); //②
    }

    {
        System.out.println("construct block"); //④
    }

    public Parent() {
        System.out.println("constructor"); //⑤
    }

    public Parent(Integer age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
