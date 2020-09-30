package com.qiu.firechill.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/25 16:09
 */
public interface SqlAction<T> {

    //通过id查询  数据库字段的主键可能是int也可能是varchar
    T selectById(Object val) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    //通过某个字段查询
    T selectOneByCol(String col,Object val) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    //通过某个字段查询   有第三个参数则走list
    List<T> selectListByCol(String col,Object val) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    //查询所有
    List<T> selectAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    //动态代理，自定义接口
    <E> E getInterFace(Class<E> clazz);
}
