package com.horstmann.corejava.v1ch08;

public class Child extends Parent {

    @Override
    public String getF() {
        return (String) super.getF();
    }

    @Override
    public void setF(Object f) {
        super.setF(f);
    }
}
