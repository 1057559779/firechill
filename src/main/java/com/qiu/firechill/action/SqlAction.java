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
    T selectById(Object val) throws Exception;

    //通过某个字段查询
    T selectOneByCol(String col,Object val) throws Exception;

    //通过某个字段查询   有第三个参数则走list
    List<T> selectListByCol(String col,Object val) throws Exception;

    //查询所有
    List<T> selectAll() throws Exception;

    //根据id删除
    Integer deleteByid(Object val) throws SQLException;

    //根据某个字段删除
    Integer deleteByCol(String col,Object val) throws SQLException;

    //插入
    Integer insertOne(T t) throws IllegalAccessException, InstantiationException, NoSuchMethodException, Exception;

    //更新
    Integer update();

    //动态代理，自定义接口
    <E> E getInterFace(Class<E> clazz);
}
