package com.qiu.firechill.session;

import com.qiu.firechill.action.CudSqlAction;
import com.qiu.firechill.action.DefaultSelfSqlAction;
import com.qiu.firechill.action.ReadSqlAction;
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

    CudSqlAction getCudSqlAction(Class<?> clazz) throws Exception;

    ReadSqlAction getReadSqlAction(Class<?> clazz) throws Exception;

    DefaultSelfSqlAction getDefalutSqlAction();

}
