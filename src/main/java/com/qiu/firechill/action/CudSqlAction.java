package com.qiu.firechill.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @Author qiu
 * @create 2020/10/6 12:21
 * @Des 操作sql处理类
 */
public interface CudSqlAction<T> {

    //根据id删除
    Integer deleteByid(Object val) throws SQLException;

    //根据某个字段删除
    Integer deleteByCol(String col,Object val) throws SQLException;

    //插入
    Integer insertOne(T t) throws IllegalAccessException, InstantiationException, NoSuchMethodException, Exception;

    //更新
    Integer update(T t,String col ,Object val) throws InvocationTargetException, IllegalAccessException, Exception;
}
