package com.qiu.firechill.common.sql;

import java.sql.Connection;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/30 15:44
 * @Des 查询sql生成与返回值生成
 */
public interface GenerateSelectSql<T> {

    /**
     * 获得有参数的sql
     * @param col
     * @return
     */
    String getReleSql(String col);

    /**
     * 全查 联表专用
     * @return
     */
    String getReleSql();

    /**
     * 返回的内容
     * @param connect
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    List<T> getReleReturn(Connection connect, String sql,Object param) throws Exception;

    /**
     * 代理专用返回的内容
     * @param connect
     * @param sql
     * @param params
     * @param args
     * @return
     * @throws Exception
     */
    List<T> getRetrun(Connection connect,StringBuilder sql,String[] params,Object[] args) throws Exception;

    /**
     * 高级的查询 可联表也可单表
     * @param clazz
     * @param connect
     * @param sql
     * @return
     * @throws Exception
     */
    List<T> getReleReturn(Class<?> clazz ,Connection connect, String sql) throws Exception;

}
