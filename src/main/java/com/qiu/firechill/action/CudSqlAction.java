package com.qiu.firechill.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/10/6 12:21
 * @Des 操作sql处理类
 */
public interface CudSqlAction<T> {

    /**
     * 根据id删除 所有字段中必须有一个字段是id
     * @param val
     * @return
     * @throws SQLException
     */
    Integer deleteByid(Object val) throws SQLException;

    /**
     * 指定某个字段删除
     * @param col 字段名
     * @param val 字段值
     * @return
     * @throws SQLException
     */
    Integer deleteByCol(String col,Object val) throws SQLException;

    /**
     * 根据实体类中的属性插入一条数据
     * @param t Entity
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws Exception
     */
    Integer insertOne(T t) throws IllegalAccessException, InstantiationException, NoSuchMethodException, Exception;

    /**
     * 根据list泛形中的属性批量插入
     * @param lists list<Entity>
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Integer insertMore(List<T> lists) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * 根据实体类中的属性来更新指定字段的数据
     * @param t Entity
     * @param col  指定字段
     * @param val  字段的值
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     */
    Integer update(T t,String col ,Object val) throws InvocationTargetException, IllegalAccessException, Exception;
}
