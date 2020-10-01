package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.impl.GenerateSelectSqlImpl;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.session.ConnectBean;
import com.qiu.firechill.session.DBConnectFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/25 14:45
 * @Des 普通方式，用java配置的方式
 */
public class CommonDBConnectFactory implements DBConnectFactory {


    @Override
    public SqlAction getAction(Class<?> clazz) throws Exception {
        //得到配置信息
        ConnectBean config = new MyDataSourceConfig().config();
        DataSource dataSource = config.getDataSource();
        Connection connect = dataSource.getConnection();

        return new SimpleSqlActionImpl(connect,clazz);
    }

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
}
