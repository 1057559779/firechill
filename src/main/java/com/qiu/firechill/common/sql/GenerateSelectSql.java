package com.qiu.firechill.common.sql;

import java.sql.Connection;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/30 15:44
 * @Des 查询sql生成与返回值生成
 */
public interface GenerateSelectSql<T> {

    String getReleSql(String col);

    String getReleSql();

    List<T> getReleReturn(Connection connect, String sql,Object param) throws Exception;

    List<T> getRetrun(Connection connect,StringBuilder sql,String[] params,Object[] args) throws Exception;

    List<T> getReleReturn(Class<?> clazz ,Connection connect, String sql) throws Exception;

}
