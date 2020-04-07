package com.myapp.exercise.annotation;

public class Sample {

    @MyAnnotation({NullPointerException.class,IndexOutOfBoundsException.class})
    public static void m1() {
    } // Test should pass

//    public static void m2() {
//    }
//
//    @MyAnnotation
//    public static void m3() { // Test should fail
//        throw new RuntimeException("Boom");
//    }
//
//    public static void m4() {
//    }
//
//    @MyAnnotation
//    public void m5() {
//    } // INVALID USE: nonstatic method
//
//    public static void m6() {
//    }
//
//    @MyAnnotation
//    public static void m7() { // Test should fail
//        throw new RuntimeException("Crash");
//    }
//
//    public static void m8() {
//    }
}
