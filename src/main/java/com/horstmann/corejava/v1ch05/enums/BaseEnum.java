package com.horstmann.corejava.v1ch05.enums;

import java.io.Closeable;
import java.io.Serializable;

public interface BaseEnum extends Closeable, Runnable {

    Integer code=1;

    static void test(){

    }

//    default E getEnumByCode(Integer code){
//
//    }
}
