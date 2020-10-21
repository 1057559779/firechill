package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.ReadSqlAction;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.impl.GenerateSelectSqlImpl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/10/6 12:46
 */
public class ReadSqlActionImpl<T> implements ReadSqlAction<T> {

    private Connection connect;

    private Class<?> clazz;

    public ReadSqlActionImpl(Connection connect, Class<?> clazz){
        this.connect=connect;
        this.clazz=clazz;
    }


    @Override
    public T selectById(Object val) throws Exception {
        //属性
        Field[] fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        //生成sql
        String sql = generate.getReleSql("id");
        //得到返回值
        List<T> list = generate.getReleReturn(connect, sql,val);
        T t = list.get(0);
        return t;
    }

    @Override
    public T selectOneByCol(String col, Object val) throws Exception {
        //属性
        Field[] fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        //生成sql
        String sql = generate.getReleSql(col);
        //得到返回值
        List<T> list = generate.getReleReturn(connect, sql,val);
        T t = list.get(0);
        return t;
    }

    @Override
    public List selectListByCol(String col, Object val) throws Exception {
        //属性
        Field[] fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];
        //属性
        fields=clazz.getDeclaredFields();

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        //生成sql
        String sql = generate.getReleSql(col);
        //得到返回值
        List<T> list = generate.getReleReturn(connect, sql,val);

        return list;
    }


    // 字段名：table+col
    @Override
    public List selectAll() throws Exception {

        //属性
        Field[] fields=clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];

        GenerateSelectSql<T> generate = new GenerateSelectSqlImpl<T>(clazz, fields,methodname,names,classes);
        String releSql = generate.getReleSql();
        List<T> relReturn = generate.getReleReturn(clazz,connect, releSql);

        return relReturn;
    }
}
