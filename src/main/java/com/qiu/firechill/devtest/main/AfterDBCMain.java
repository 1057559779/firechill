package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.devtest.pojo.QiuUser;
import com.qiu.firechill.session.impl.CommonDBConnectFactory;
import javax.sql.DataSource;
import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {
        //得到配置信息
        DataSource dataSource = new MyDataSourceConfig().config();

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(dataSource,QiuUser.class);

        List<QiuUser> list = action.selectAll();

        System.out.println(list);
    }
}
