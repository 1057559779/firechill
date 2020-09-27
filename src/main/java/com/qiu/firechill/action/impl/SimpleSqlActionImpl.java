package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.Select;
import com.qiu.firechill.ann.SqlMapper;
import com.qiu.firechill.ann.TableName;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/25 16:12
 * @Des 简单的sql操作类
 */
public class SimpleSqlActionImpl<T> implements SqlAction {

    private Connection connect;
    private Class<?> clazz;

    public SimpleSqlActionImpl(Connection connect, Class<?> clazz) {
        this.connect=connect;
        this.clazz=clazz;
    }

    public T selectById() throws SQLException {

        return null;
    }

    public List<T> selectAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        String[] names = new String[fields.length];
        Class[] classes = new Class[fields.length];
        StringBuilder sql = new StringBuilder("select ");
        for (int i = 0; i < fields.length; i++) {
            ColumnName columnName =fields[i].getAnnotation(ColumnName.class);
            if(columnName != null){
                //字段名拼接
                String value = columnName.value();
                sql.append(value);
                if(i != fields.length-1){
                    sql.append(",");
                }
                //获得属性名String
                String name = fields[i].getName();
                names[i]=name;
                //首字母大写化(方法化)
                String upname ="set"+name.substring(0,1).toUpperCase()+name.substring(1);
                methodname[i]=upname;
                Class<?> type = fields[i].getType();
                classes[i]=type;
            }
        }
        sql.append(" from");
        TableName tann = clazz.getAnnotation(TableName.class);
        String value = tann.value();
        sql.append(" "+value);
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(sql.toString());
        List<T> list = new ArrayList<>();
        while (rs.next()){
            T o = (T)clazz.newInstance();
            for (int i = 0; i <names.length ; i++) {
                Method method = clazz.getMethod(methodname[i], classes[i]);
                if(rs.getObject(names[i]) instanceof String ){
                    method.invoke(o,rs.getString(names[i]));
                }
                else if(rs.getObject(names[i]) instanceof Integer){
                    method.invoke(o,rs.getInt(names[i]));
                }
                else{
                    method.invoke(o,rs.getObject(names[i]));
                }
            }
            list.add(o);
        }
        return list;
    }

    @Override
    public Object getInterFace(Class clazz) throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        //定义类
        CtClass de = pool.makeClass("com.qiu.firechill.action");
        //设置父类
        de.setSuperclass(pool.get(clazz.getName()));

        return null;
    }
}
