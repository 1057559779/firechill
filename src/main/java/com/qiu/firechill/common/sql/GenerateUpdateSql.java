package com.qiu.firechill.common.sql;

import java.util.Map;

/**
 * @Author qiu
 * @create 2020/10/4 11:35
 */
public interface GenerateUpdateSql {

    /**
     * 生成更新语句用的sql 返回sql与占位符
     * @param setVal 实体类中的值就是set的值
     * @param col update语句中条件字段
     * @param val 条件字段值
     * @return
     * @throws NoSuchMethodException
     * @throws Exception
     */
    Map<String,Object> getSql(Object setVal, String col, Object val) throws NoSuchMethodException, Exception;
}
