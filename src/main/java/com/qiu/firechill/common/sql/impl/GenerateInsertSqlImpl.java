package com.qiu.firechill.common.sql.impl;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateInsertSql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author qiu
 * @create 2020/10/3 20:33
 * @Des 插入语句的sql生成类
 */
public class GenerateInsertSqlImpl implements GenerateInsertSql {

    @Override
    public Map<String, Object> getInsertOneSql(Object o) throws Exception {
        //key sql   key List<Object> 存放一个对象中的所有值
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
        //创建初始化的sql
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
        //拼接sql  去掉最后一个半括号
        StringBuilder newSql = new StringBuilder(sql.substring(0, sql.length() - 1));
        //占位符们
        StringBuilder newParamIndex = new StringBuilder(paramIndex.substring(0, paramIndex.length() - 1));
        newParamIndex.append(")");
        newSql.append(") values ");
        newSql.append(newParamIndex);

        //定义map，将两个需要返回的值返回出去
        Map<String, Object> bigReturn = new HashMap<>();
        //sql
        bigReturn.put("sql",newSql.toString());
        //占位符需要添加的值
        bigReturn.put("vals",vals);

        return bigReturn;
    }
}
