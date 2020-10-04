package com.qiu.firechill.common.sql;

import java.util.Map;

/**
 * @Author VULCAN
 * @create 2020/10/4 11:35
 */
public interface GenerateUpdateSql {

    Map<String,Object> getSql(Object o, String col, Object val) throws NoSuchMethodException, Exception;
}
