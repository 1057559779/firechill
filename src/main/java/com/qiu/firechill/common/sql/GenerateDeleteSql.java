package com.qiu.firechill.common.sql;

/**
 * @Author qiu
 * @create 2020/10/3 10:31
 * @Des 删除sql生成
 */
public interface GenerateDeleteSql {

    /**
     * 获得sql
     * @param col 单个字段
     * @return
     */
    StringBuilder getSql(String col);
}
