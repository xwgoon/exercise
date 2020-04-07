package com.myapp.exercise.annotation;

import java.io.Closeable;
import java.lang.reflect.Method;

public class MyAnnotationTest {

    public static void main(String[] args) {
        Method[] methods=Sample.class.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(MyAnnotation.class)){
//                method.invoke(null);
            }
        }
    }
}
