package com.qiu.firechill.common.sql;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author VULCAN
 * @create 2020/10/3 20:32
 */
public interface GenerateInsertSql {

    /**
     * 返回拼接好的sql与对应的占位符的值
     * @param o  Entity
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     */
    Map<String,Object> getInsertOneSql(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, Exception;

    /**
     * 返回拼接好的sql与对应的占位符的值
     * @param list List<Entity>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Map<String,Object> getInsertMoreSql(List list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
