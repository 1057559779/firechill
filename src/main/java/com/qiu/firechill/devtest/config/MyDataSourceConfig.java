package com.qiu.firechill.devtest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.qiu.firechill.session.ConnectBean;
import com.qiu.firechill.session.DataSourceInfoConfig;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author qiu
 * @create 2020/9/25 15:30
 * @Des 配置类
 */
public class MyDataSourceConfig implements DataSourceInfoConfig {

    @Override
    public ConnectBean config(){

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://120.55.88.202:3306/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");

            ConnectBean connectBean = new ConnectBean();
            //装入数据库连接池
            connectBean.setDataSource(dataSource);

        return connectBean;

    }

}
