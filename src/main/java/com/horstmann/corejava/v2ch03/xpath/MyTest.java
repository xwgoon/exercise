package com.horstmann.corejava.v2ch03.xpath;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import java.util.StringJoiner;

public class MyTest {

    public static void main(String[] args) {

//        StringJoiner joiner = new StringJoiner(",", "{", "}");
//        joiner.add("aa");
//        joiner.add("bb");
//        System.out.println(joiner);

        try {
            QName returnType = (QName) XPathConstants.class.getField("BOOLEAN").get(null);
            System.out.println(returnType);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


}
