package com.qiu.firechill.common.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/30 15:44
 */
public interface GenerateSelectSql<T> {

    StringBuilder getSql();

    StringBuilder getSql(String col);

    List<T> getRetrun(Connection connect, StringBuilder sql) throws Exception;

    List<T> getRetrun(Connection connect, StringBuilder sql,Object param) throws Exception;

    List<T> getRetrun(Connection connect,StringBuilder sql,String[] params,Object[] args) throws Exception;



}
