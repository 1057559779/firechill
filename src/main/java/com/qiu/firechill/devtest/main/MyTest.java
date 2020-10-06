package com.qiu.firechill.devtest.main;


import com.qiu.firechill.common.field.FieldCommon;
import com.qiu.firechill.devtest.pojo.MoreToMorePojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author VULCAN
 * @create 2020/9/27 10:50
 */
public class MyTest {

    public static void main(String[] args) {
        MoreToMorePojo moreToMorePojo = new MoreToMorePojo();
        Class<?> aClass = moreToMorePojo.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields) {

            String fieldGeneric = FieldCommon.getFieldGeneric(field);
            System.out.println(fieldGeneric);

        }
    }
}
