package com.qiu.firechill.common.sql;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author VULCAN
 * @create 2020/10/3 20:32
 */
public interface GenerateInsertSql {

    Map<String,Object> getInsertOneSql(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, Exception;
}
