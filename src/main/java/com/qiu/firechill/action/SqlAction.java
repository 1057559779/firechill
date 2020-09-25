package com.qiu.firechill.action;

import java.sql.SQLException;

/**
 * @Author VULCAN
 * @create 2020/9/25 16:09
 */
public interface SqlAction<T> {

    T selectById() throws SQLException;

    T selectAll() throws SQLException;
}
