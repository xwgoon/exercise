package com.myapp.exercise.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by shengmingYu on 2017/2/15.
 */
public enum Status {

    VALID("有效", 0), INVALID("无效", 1);

    String label;

    Integer value;

    Status(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    @JsonCreator
    public static Status create(String name) {
        return Status.valueOf(name);
    }
}
