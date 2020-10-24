package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.devtest.pojo.User;
import com.qiu.firechill.session.DbConnectFactory;
import com.qiu.firechill.session.DbConnectFactoryBuilder;
import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDbMain {

    public static void main(String[] args) throws Exception {
        //测试
        DbConnectFactory build = DbConnectFactoryBuilder.build(new MyDataSourceConfig());
        SqlAction<User> action = build.getAction(User.class);
        User users = action.selectById("1");
        System.out.println(users);
    }
}
