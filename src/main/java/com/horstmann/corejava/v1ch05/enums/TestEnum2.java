package com.horstmann.corejava.v1ch05.enums;

import java.io.IOException;

public enum TestEnum2 implements BaseEnum{
    ;


    @Override
    public void close() throws IOException {

    }

    @Override
    public void run() {

    }

    void test2(){
        System.out.println(code);
        BaseEnum.test();
    }
}
