package com.qiu.firechill.common.sql;

import java.sql.Connection;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/30 15:44
 * @Des 查询sql生成与返回值生成
 */
public interface GenerateSelectSql<T> {

    StringBuilder getSql();

    StringBuilder getSql(String col);

    StringBuilder getReleSql();

    List<T> getRetrun(Connection connect, StringBuilder sql) throws Exception;

    List<T> getRetrun(Connection connect, StringBuilder sql,Object param) throws Exception;

    List<T> getRetrun(Connection connect,StringBuilder sql,String[] params,Object[] args) throws Exception;



}
