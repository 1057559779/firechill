package com.qiu.firechill.common.sql;

/**
 * @Author qiu
 * @create 2020/10/1 21:31
 * @Des 代理类专用的sql封装
 */
public interface SqlProxyDo {

    /**
     * 解析自定义sql的查询，并得到返回值
     * @param sql 已经拼好的sql
     * @param tClass
     * @param isList 是否是list
     * @param params 参数的index(位置)
     * @param args 参数值
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T doSelectSql(String sql,Class<T> tClass,String isList,String[] params,Object[] args) throws Exception;

    /**
     * 处理增删改，占位符注入
     * @param sql 已经拼接好的sql
     * @param params 参数在占位符的位置
     * @param args 参数的值 实参
     * @return
     * @throws Exception
     */
    Integer doChangeSql(String sql,String[] params,Object[] args) throws Exception;

}
