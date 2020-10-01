package com.qiu.firechill.common.sql;

/**
 * @Author qiu
 * @create 2020/10/1 21:31
 * @Des 代理类专用的sql封装
 */
public interface SqlProxyDo {

    //处理查询 并得到返回值
    <T> T doSelectSql(String sql,Class<T> tClass,String isList,String[] params,Object[] args) throws Exception;

    //处理删除 无需返回
    Integer doDeleteSql(String sql,String[] params,Object[] args) throws Exception;
}
