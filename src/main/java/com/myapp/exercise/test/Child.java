package com.myapp.exercise.test;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;


//@JSONType(serialzeFeatures= SerializerFeature.BeanToArray, parseFeatures= Feature.SupportArrayToBean)
public class Child extends Parent {

//    @JSONField(serialize = false)
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Child{" +
                "school='" + school + '\'' +
                "} " + super.toString();
    }
}
