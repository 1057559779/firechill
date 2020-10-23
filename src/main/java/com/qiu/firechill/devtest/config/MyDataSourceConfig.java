package com.qiu.firechill.devtest.config;

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
    public ConnectBean config() throws Exception {

            int initialCapacity=8;
            Map<String ,Object> map = new HashMap<>(initialCapacity);
            map.put("url","jdbc:mysql://120.55.88.202:3306/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
            map.put("username","root");
            map.put("password","123456");
            map.put("initialSize","5");
            map.put("maxActive","10");
            map.put("maxWait","3000");
            map.put("driverClassName","com.mysql.jdbc.Driver");
            DataSource ds = DruidDataSourceFactory.createDataSource(map);

            ConnectBean connectBean = new ConnectBean();
            //装入数据库连接池
            connectBean.setDataSource(ds);

        return connectBean;

    }

}
