package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.AllSqlAction;
import com.qiu.firechill.action.ReadSqlAction;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.devtest.pojo.QiuUser;
import com.qiu.firechill.devtest.pojo.User;

import com.qiu.firechill.session.DbConnectFactory;
import com.qiu.firechill.session.DbConnectFactoryBuilder;
import com.qiu.firechill.session.impl.CommonDbConnectFactory;

import javax.sql.DataSource;
import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDbMain {

    public static void main(String[] args) throws Exception {
        DbConnectFactory build = DbConnectFactoryBuilder.build(new MyDataSourceConfig());
        AllSqlAction<User> action = build.getAction(User.class);
        List<User> users = action.selectAll();
        System.out.println(users);
    }
}
