package com.qiu.firechill.session;

import javax.sql.DataSource;

/**
 * @Author qiu
 * @create 2020/9/26 11:49
 * @Des 总体配置类  今后为了配合spring
 */
public class ConnectBean {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
