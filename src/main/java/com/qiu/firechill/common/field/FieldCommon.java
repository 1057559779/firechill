package com.qiu.firechill.common.field;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author qiu
 * @create 2020/10/5 11:39
 * @Des 实体类属性的工具类
 */
public class FieldCommon {

    /**
     * 获得属性中的泛型 包名
     */
    public static String getFieldGeneric(Field field){
        //field作为参数
        Type genericType = field.getGenericType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
            //获得属性中的泛型
            return genericClazz.getName();
        }
        //如果没有适合的类型就返回空的字符串
        return "";
    }
}
