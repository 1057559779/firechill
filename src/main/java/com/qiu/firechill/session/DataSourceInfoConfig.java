package com.qiu.firechill.session;

import javax.sql.DataSource;

/**
 * @Author qiu
 * @create 2020/9/25 15:23
 * @Des 数据库连接信息的配置
 */
public interface DataSourceInfoConfig {

    /**
     * 用于返回一个ConnectBean ConnectBean当中有DataSource
     * @return
     */
    ConnectBean config();
}
