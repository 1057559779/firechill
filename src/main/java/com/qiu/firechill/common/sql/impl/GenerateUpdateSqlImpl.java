package com.qiu.firechill.common.sql.impl;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateUpdateSql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author VULCAN
 * @create 2020/10/4 11:36
 */
public class GenerateUpdateSqlImpl implements GenerateUpdateSql {
    @Override
    public Map<String,Object> getSql(Object o, String col, Object val) throws Exception {

        Class<?> oClass = o.getClass();
        TableName table = oClass.getAnnotation(TableName.class);
        String tableName = table.value();
        StringBuilder sql = new StringBuilder("update "+tableName+" set ");
        Field[] fields = oClass.getDeclaredFields();
        //值s
        List<Object> vals = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            ColumnName colitem = fields[i].getAnnotation(ColumnName.class);
            if(colitem != null){
                String fieldName = fields[i].getName();
                String mname ="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                Method method = oClass.getMethod(mname);
                //得到属性中的值
                Object real = method.invoke(o);
                if(real != null){
                    String colname = colitem.value();
                    sql.append(colname+"=?,");
                    vals.add(real);
                }

            }
        }
        //去掉最后一个逗号
        StringBuilder newsql = new StringBuilder(sql.substring(0, sql.length() - 1));
        newsql.append(" where "+col+" ="+val);
        int initialCapacity = 4;
        Map<String, Object> returnObj = new HashMap<>(initialCapacity);
        returnObj.put("sql",newsql.toString());
        returnObj.put("vals",vals);
        return returnObj;
    }
}
