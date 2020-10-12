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
        buildSql(new ArrayList(){{add(o);}});
    }
    public Map<String, Object> buildSql(List<Object> lists) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //key sql   key List<Object> 存放一个对象中的所有值
        Object o1 = lists.get(0);
        Class<?> obclass = o1.getClass();
        TableName table = obclass.getAnnotation(TableName.class);
        //得到表名
        String tablename = table.value();
        //值
        List<Object> vals = new ArrayList<>();
        String annStr = createAnnStr(obclass, ",");
        String values="("+ String.format("%0" + (obclass.getDeclaredFields().length - 1) + "d", 0).replace("0", "?,")+"?"+")";
        StringBuilder sql=null;
        if(lists.size()>1){
            String values2=String.format("%0" + (lists.size()-1) + "d", 0).replace("0",values+",")+ values;
            sql = new StringBuilder("insert into "+tablename+" ("+annStr+") values "+values2);
        }else{
            sql = new StringBuilder("insert into "+tablename+" ("+annStr+") values "+values);
        }
        //创建完整的sql语句
        //将object 里面的值取出来
        for(Object o:lists){
            Class<?> oClass = o.getClass();
            Field[] obfields = obclass.getDeclaredFields();
            for (int i = 0; i < obfields.length; i++) {
                ColumnName columnName =obfields[i].getAnnotation(ColumnName.class);
                if(columnName !=null){
                    //获取字段名
                    String name = obfields[i].getName();
                    String mname ="get"+name.substring(0,1).toUpperCase()+name.substring(1);
                    Method method = obclass.getMethod(mname);
                    //得到属性中的值
                    Object val = method.invoke(o);
                    vals.add(val);
                }
            }
        }
        //定义map，将两个需要返回的值返回出去
        Map<String, Object> bigReturn = new HashMap<>();
        //sql
        bigReturn.put("sql",sql.toString());
        //占位符需要添加的值
        bigReturn.put("vals",vals);
        return bigReturn;

    }
    //从c1中找出c2的注解,并用分隔符拼接每个c2注解的值 (练习)
    private String createAnnStr(Class c1,String character1){
        StringBuilder str=new StringBuilder();
        ColumnName columnName=null;
        for (Field f:c1.getDeclaredFields()){
            columnName=f.getAnnotation(ColumnName.class);
            if(columnName!=null){
                str.append(columnName.value()+character1);
            }
        }
        str.deleteCharAt(str.length() - character1.length());
        return str.toString();

    }
    @Override
    public Map<String, Object> getInsertMoreSql(List o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return buildSql(o);
    }
}
