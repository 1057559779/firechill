package com.qiu.firechill.session;

import com.qiu.firechill.session.impl.CommonDbConnectFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 创建数据库连接会话类
 */
public class DbConnectFactoryBuilder {

    private DbConnectFactoryBuilder(){

    }

    private static ConnectBean config=null;

    /**
     * 从connectBean中获取到datasource
     * @param config
     * @return
     */
    private static DataSource getDataSource(ConnectBean config){
        DataSource dataSource = config.getDataSource();
        return dataSource;
    }

    /**
     * 获得DataSource
     * @param dataSourceInfoConfig
     * @return
     * @throws Exception
     */
    private static DataSource getDataSource (DataSourceInfoConfig dataSourceInfoConfig) throws Exception {
        if(config == null){
            config = dataSourceInfoConfig.config();
        }
        DataSource dataSource = getDataSource(config);
        return dataSource;
    }

    public static DbConnectFactory build(DataSourceInfoConfig dataSourceInfoConfig) throws Exception {
        DataSource dataSource = getDataSource(dataSourceInfoConfig);
        Connection connection = dataSource.getConnection();
        DbConnectFactory dbConnectFactory = new CommonDbConnectFactory(connection);
        return dbConnectFactory;
    }

}
