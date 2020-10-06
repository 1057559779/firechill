package com.qiu.firechill.action;

import java.util.List;

/**
 * @Author qiu
 * @create 2020/10/6 12:21
 * @Des 读专用sql处理类
 */
public interface ReadSqlAction<T> {

    //通过id查询  数据库字段的主键可能是int也可能是varchar
    T selectById(Object val) throws Exception;

    //通过某个字段查询
    T selectOneByCol(String col,Object val) throws Exception;

    //通过某个字段查询   有第三个参数则走list
    List<T> selectListByCol(String col, Object val) throws Exception;

    //查询所有
    List<T> selectAll() throws Exception;
}
