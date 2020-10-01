package com.qiu.firechill.common.sql.impl;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/30 14:37
 * @Des 生成sql工具类
 */
public class GenerateSelectSqlImpl<T> implements GenerateSelectSql {

    private Class<?> clazz;

    private Field[] fields;
    //方法名String
    String[] methodname;
    //属性名
    String[] names;
    //所有属性的数据类型
    Class[] classes;

    private GenerateSelectSqlImpl(){

    }

    public GenerateSelectSqlImpl(Class<?> clazz,Field[] fields,String[] methodname, String[] names, Class[] classes){
        this.clazz=clazz;
        this.fields=fields;
        this.methodname=methodname;
        this.names=names;
        this.classes=classes;
    }

    //生成无条件的查询sql
    public  StringBuilder getSql(){

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

       return sql;
   }

    @Override
    public StringBuilder getSql(String col) {
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
        sql.append(" "+value+" where "+col+"=?");
        return sql;
    }

    //生成返回值 无参数 调用方可以判断是否需要get[0]
    public List<T> getRetrun(Connection connect,StringBuilder sql) throws Exception {

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

    //生成返回值 单参数的 调用方可以判断是否需要get[0]
    public List<T> getRetrun(Connection connect,StringBuilder sql,Object param) throws Exception {

        PreparedStatement pstmt = connect.prepareStatement(sql.toString());
        if(param instanceof  String){
            pstmt.setString(1, (String)param);
        }else if(param instanceof  Integer){
            pstmt.setInt(1, (Integer) param);
        }
        ResultSet rs = pstmt.executeQuery();
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

    //生成放回置，多参数的
    public List<T> getRetrun(Connection connect,StringBuilder sql,String[] params,Object[] args) throws Exception {

        PreparedStatement pstmt = connect.prepareStatement(sql.toString());
        //将使用端的实参得到set进sql的?中
        for (int i = 0; i < params.length; i++) {
            Integer index = Integer.parseInt(params[i]);
            if(args[i] instanceof  String){
                pstmt.setString(index, (String)args[i]);
            }else if(args[i] instanceof  Integer){
                pstmt.setInt(index, (Integer) args[i]);
            }
        }
        //将各种反射需要用到的数组都填上
        for (int i = 0; i < fields.length; i++) {
                //获得属性名String
                String name = fields[i].getName();
                names[i]=name;
                //首字母大写化(方法化)
                String upname ="set"+name.substring(0,1).toUpperCase()+name.substring(1);
                methodname[i]=upname;
                Class<?> type = fields[i].getType();
                classes[i]=type;
        }
        //返回值拼接
        ResultSet rs = pstmt.executeQuery();
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

}
