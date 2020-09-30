package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.SqlActionProxy;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.impl.GenerateSelectSqlImpl;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/25 16:12
 * @Des 简单的sql操作类
 */
public class SimpleSqlActionImpl<T> implements SqlAction {

    private Connection connect;

    private Class<?> clazz;

    private Field[] fields ;

    private String[] methodname;

    private String[] names;

    private Class[] classes;

    public SimpleSqlActionImpl(Connection connect, Class<?> clazz) {
        this.connect=connect;
        this.clazz=clazz;
    }

    public T selectById(Object val) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //属性
        fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        //生成sql
        StringBuilder sql = generate.getSql("id",val);
        //得到返回值
        List<T> list = generate.getRetrun(connect, sql);
        T t = list.get(0);
        return t;
    }

    @Override
    public Object selectBySome(String col, Object val) throws SQLException {
        return null;
    }

    public List<T> selectAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //属性
        fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        //生成sql
        StringBuilder sql = generate.getSql();
        //得到返回值
        List<T> list = generate.getRetrun(connect, sql);
        return list;
    }


    @Override
    public Object getInterFace(Class clazz){
        SqlActionProxy sqlActionProxy = new SqlActionProxy(clazz);
        Object proxy = sqlActionProxy.getProxy();
        return proxy;
    }
}
