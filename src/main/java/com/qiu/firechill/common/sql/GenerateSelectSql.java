package com.qiu.firechill.common.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/30 15:44
 */
public interface GenerateSelectSql<T> {

    StringBuilder getNoConfidence();

    List<T> getRetrun(Connection connect, StringBuilder sql) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
