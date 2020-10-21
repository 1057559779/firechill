package com.qiu.firechill.action;

import java.util.List;

/**
 * @Author qiu
 * @create 2020/10/6 12:21
 * @Des 读专用sql处理类
 */
public interface ReadSqlAction<T> {

    /**
     * 根据id查询 因为id的值可能是int 也可能是uuid
     * @param val
     * @return
     * @throws Exception
     */
    T selectById(Object val) throws Exception;

    /**
     * 指定一个字段查询一条数据
     * @param col
     * @param val
     * @return
     * @throws Exception
     */
    T selectOneByCol(String col,Object val) throws Exception;

    /**
     * 指定一个字段查询多条数据
     * @param col
     * @param val
     * @return
     * @throws Exception
     */
    List<T> selectListByCol(String col, Object val) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<T> selectAll() throws Exception;
}
