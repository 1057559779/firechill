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
public interface DbConnectFactory {

    /**
     * 获取有所有功能的sql处理类
     * @param clazz
     * @return
     * @throws Exception
     */
    SqlAction getAction(Class<?> clazz) throws Exception;

    /**
     * 增删改专用的sql处理类
     * @param clazz
     * @return
     * @throws Exception
     */
    CudSqlAction getCudSqlAction(Class<?> clazz) throws Exception;

    /**
     * 读专用sql处理类
     * @param clazz
     * @return
     * @throws Exception
     */
    ReadSqlAction getReadSqlAction(Class<?> clazz) throws Exception;

    /**
     * 自定义的sql处理类
     * @return
     */
    DefaultSelfSqlAction getDefalutSqlAction();

}
