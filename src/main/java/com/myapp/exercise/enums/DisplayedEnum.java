package com.myapp.exercise.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dy on 17/2/6.
 */
public interface DisplayedEnum {

    String DEFAULT_VALUE_NAME = "value";

    String DEFAULT_LABEL_NAME = "label";

    default Integer getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return Integer.parseInt(field.get(this).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonValue
    default String getLabel() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonCreator
    static  <T extends Enum<T>> T nameOfEnum(String name) {
//        String[] names= Arrays.stream(this.getClass().getEnumConstants()).map(Enum::name).toArray(String[]::new);
//        Class<T> enumClass = this.getClass();
//        T[] enums=this.getClass().getEnumConstants();
//        ReflectionUtils.doWithMethods(this.getClass(), );

//        Method method = this.getClass().getDeclaredMethod("values");

//        for (T unit :this.getClass().) {
//            if (unit.name().equals(name)) {
//                return unit;
//            }
//        }

        Class<?> cl=new Object(){}.getClass().getEnclosingClass();

        try {
            Method method = cl.getMethod("valueOf", String.class);
            T o = (T) method.invoke(null, name);
            return o;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }



    static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer value) {
        if (value == null)
            throw  new IllegalArgumentException("DisplayedEnum value should not be null");
//        if (enumClass.isAssignableFrom(DisplayedEnum.class))
        if (!DisplayedEnum.class.isAssignableFrom(enumClass))
            throw new IllegalArgumentException("illegal DisplayedEnum type");
        T[] enums = enumClass.getEnumConstants();
        for (T t: enums) {
            DisplayedEnum displayedEnum = (DisplayedEnum)t;
            if (displayedEnum.getValue().equals(value))
                return (T) displayedEnum;
        }
        throw new IllegalArgumentException("cannot parse integer: " + value + " to " + enumClass.getName());
    }
}
