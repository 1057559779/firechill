package com.qiu.firechill.common.sql.impl;

import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.SqlProxyDo;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/10/1 21:32
 * @Des 代理类专用的sql分装的实现类
 */
public class SqlProxyDoImpl implements SqlProxyDo {

    @Override
    public Object doSelectSql(String sql,Class clazz,String islist,String[] params,Object[] args) throws Exception {
        //属性
        Field[] fields =clazz.getDeclaredFields();
        //方法名String
        String[] methodname = new String[fields.length];
        //属性名
        String[] names = new String[fields.length];
        //所有属性的数据类型
        Class[] classes = new Class[fields.length];
        Connection conn = new MyDataSourceConfig().config().getDataSource().getConnection();
        GenerateSelectSql generate = new GenerateSelectSqlImpl(clazz, fields,methodname,names,classes);
        StringBuilder sbsql = new StringBuilder(sql);
        List list = generate.getRetrun(conn, sbsql, params, args);
        if (islist.equals("List")){
            //这是个list类型的
            return list;
        }else {
            return list.get(0);
        }
    }

    @Override
    public Integer doDeleteSql(String sql, String[] params, Object[] args) throws Exception {
        Connection conn = new MyDataSourceConfig().config().getDataSource().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //将使用端的实参得到set进sql的?中
        for (int i = 0; i < params.length; i++) {
            Integer index = Integer.parseInt(params[i]);
            if(args[i] instanceof  String){
                pstmt.setString(index, (String)args[i]);
            }else if(args[i] instanceof  Integer){
                pstmt.setInt(index, (Integer) args[i]);
            }
        }
        int count = pstmt.executeUpdate();

        return count;

    }
}
