package com.qiu.firechill.action;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/25 16:09
 */
public interface SqlAction<T> {

    T selectById() throws SQLException;

    List<T> selectAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    <E> E getInterFace(Class<E> clazz) throws NotFoundException, CannotCompileException;
}
