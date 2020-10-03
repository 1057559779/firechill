package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.SqlActionProxy;
import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateDeleteSql;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.impl.GenerateDeleteSqlImpl;
import com.qiu.firechill.common.sql.impl.GenerateSelectSqlImpl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private Field[] fields ;

    public SimpleSqlActionImpl(Connection connect, Class<?> clazz) {
        this.connect=connect;
        this.clazz=clazz;
    }

    public T selectById(Object val) throws Exception {
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
        StringBuilder sql = generate.getSql("id");
        //得到返回值
        List<T> list = generate.getRetrun(connect, sql,val);
        T t = list.get(0);
        return t;
    }

    @Override
    public Object selectOneByCol(String col, Object val) throws Exception {
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
        StringBuilder sql = generate.getSql(col);
        //得到返回值
        List<T> list = generate.getRetrun(connect, sql,val);
        T t = list.get(0);
        return t;
    }

    @Override
    public List selectListByCol(String col, Object val) throws Exception {

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
        StringBuilder sql = generate.getSql(col);
        //得到返回值
        List<T> list = generate.getRetrun(connect, sql,val);

        return list;
    }

    public List<T> selectAll() throws Exception {
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
    public Integer deleteByid(Object val) throws SQLException {
        GenerateDeleteSql generate = new GenerateDeleteSqlImpl(clazz);
        StringBuilder sql = generate.getSql("id");
        PreparedStatement pstmt = connect.prepareStatement(sql.toString());
        if(val instanceof  String){
            pstmt.setString(1, (String)val);
        }else if(val instanceof  Integer){
            pstmt.setInt(1, (Integer) val);
        }
        int i = pstmt.executeUpdate();
        return i;
    }

    @Override
    public Integer deleteByCol(String col, Object val) throws SQLException {
        GenerateDeleteSql generate = new GenerateDeleteSqlImpl(clazz);
        StringBuilder sql = generate.getSql(col);
        PreparedStatement pstmt = connect.prepareStatement(sql.toString());
        if(val instanceof  String){
            pstmt.setString(1, (String)val);
        }else if(val instanceof  Integer){
            pstmt.setInt(1, (Integer) val);
        }
        int i = pstmt.executeUpdate();
        return i;
    }

    @Override
    public Integer insertOne(Object o) throws Exception {
        Class<?> obclass = o.getClass();
        TableName table = obclass.getAnnotation(TableName.class);
        //得到表名
        String tablename = table.value();
        //属性数组
        Field[] obfields = obclass.getDeclaredFields();
        //占位符
        StringBuilder paramIndex = new StringBuilder("(");
        //值s
        List<Object> vals = new ArrayList<>();
        //insert into table (,,) values(?,?)
        StringBuilder sql = new StringBuilder("insert into "+tablename+" (");
        //将object 里面的值取出来
        for (int i = 0; i < obfields.length; i++) {
            ColumnName columnName =obfields[i].getAnnotation(ColumnName.class);
            if(columnName !=null){
                //获取字段名
                String value = columnName.value();
                String name = obfields[i].getName();
                sql.append(value+",");
                paramIndex.append("?,");
                String mname ="get"+name.substring(0,1).toUpperCase()+name.substring(1);
                Method method = obclass.getMethod(mname);
                //得到属性中的值
                Object val = method.invoke(o);
                vals.add(val);
            }
         }
        //拼接sql
        StringBuilder newSql = new StringBuilder(sql.substring(0, sql.length() - 1));
        StringBuilder newParamIndex = new StringBuilder(paramIndex.substring(0, paramIndex.length() - 1));
        newParamIndex.append(")");
        newSql.append(") values ");
        newSql.append(newParamIndex);
        PreparedStatement pstmt = connect.prepareStatement(newSql.toString());
        //jdbc的sql占位符是从1开始的
        for (int i = 0; i < vals.size(); i++) {
            Object real = vals.get(i);
            if(real instanceof  String){
                pstmt.setString(i+1, (String)real);
            }else if(real instanceof  Integer){
                pstmt.setInt(i+1, (Integer) real);
            }
        }
        int i = pstmt.executeUpdate();
        return i;
    }


    @Override
    public Integer update() {
        return null;
    }


    @Override
    public Object getInterFace(Class clazz){
        SqlActionProxy sqlActionProxy = new SqlActionProxy(clazz);
        Object proxy = sqlActionProxy.getProxy();
        return proxy;
    }
}
