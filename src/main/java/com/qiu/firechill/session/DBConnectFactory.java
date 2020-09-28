package com.qiu.firechill.session;

import com.qiu.firechill.action.SqlAction;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @Author qiu
 * @create 2020/9/25 14:44
 * @Des 数据库连接创建工厂
 */
public interface DBConnectFactory {

    SqlAction getAction(Class<?> clazz) throws Exception;

    <T> T doSelectSql(String sql,Class<T> tClass);

}
