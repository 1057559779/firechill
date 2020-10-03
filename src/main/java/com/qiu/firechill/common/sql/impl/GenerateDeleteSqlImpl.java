package com.qiu.firechill.common.sql.impl;

import com.qiu.firechill.ann.TableName;
import com.qiu.firechill.common.sql.GenerateDeleteSql;
import java.lang.reflect.Field;

/**
 * @Author qiu
 * @create 2020/10/3 10:37
 * @Des 删除语句生成类
 */
public class GenerateDeleteSqlImpl implements GenerateDeleteSql {

    private Class<?> clazz;

    private GenerateDeleteSqlImpl(){

    }

    public GenerateDeleteSqlImpl(Class<?> clazz){
        this.clazz=clazz;
    }

    @Override
    public StringBuilder getSql(String col) {
        TableName table = clazz.getAnnotation(TableName.class);
        String name = table.value();
        StringBuilder sqlb = new StringBuilder();
        //开始sql拼接
        sqlb.append("delete from ");
        sqlb.append(name);
        sqlb.append(" where "+col+" =?");
        return sqlb;
    }

}
