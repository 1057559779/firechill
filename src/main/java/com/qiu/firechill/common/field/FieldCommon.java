package com.qiu.firechill.common.field;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.OneToOne;
import com.qiu.firechill.ann.TableName;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author qiu
 * @create 2020/10/5 11:39
 * @Des 实体类属性的工具类
 */
public class FieldCommon {

    /**
     * 获得属性中的泛型 包名
     */
    public static String getFieldGeneric(Field field){
        //field作为参数
        Type genericType = field.getGenericType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
            //获得属性中的泛型
            return genericClazz.getName();
        }
        //如果没有适合的类型就返回空的字符串
        return "";
    }

    /**
     * 一对一 通过递归获取OneToOne注解
     */
    public static String getOneToOneJoinSql(Class<?> clazz,String ptable,String pkey,String skey) {
        TableName table = clazz.getAnnotation(TableName.class);
        String tablename = table.value();
        StringBuilder sql = new StringBuilder(" left join "+tablename+" on "+ptable+"."+pkey+"="+tablename+"."+skey);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            OneToOne onetoone = field.getAnnotation(OneToOne.class);
            if (onetoone != null) {
                //得到当前的id
                String spkey = onetoone.pkey();
                //得到外键id
                String sskey = onetoone.skey();
                Class<?> type = field.getType();
                String ssql = getOneToOneJoinSql(type,tablename,spkey,sskey);
                sql.append(ssql);
            }

        }
        return sql.toString();
    }


    public static String getOneToOneColSql(Class<?> clazz){
        StringBuilder fieldsql = new StringBuilder();
        TableName table = clazz.getAnnotation(TableName.class);
        String tableName = table.value();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            ColumnName col = field.getAnnotation(ColumnName.class);
            OneToOne onetoone = field.getAnnotation(OneToOne.class);
            if(col !=null){
                String colname = col.value();
                fieldsql.append(tableName+"."+colname+",");
            }
            if(onetoone !=null){
                Class<?> type = field.getType();
                String ssfield = getOneToOneColSql(type);
                fieldsql.append(ssfield);
            }
        }
        return fieldsql.toString();
    }
}
