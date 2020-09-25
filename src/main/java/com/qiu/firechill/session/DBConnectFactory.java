package com.qiu.firechill.session;

import com.qiu.firechill.action.SqlAction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author qiu
 * @create 2020/9/25 14:44
 * @Des 数据库连接创建工厂
 */
public interface DBConnectFactory {

    SqlAction getAction(DataSource dataSource,Class<?> clazz) throws SQLException;

}
