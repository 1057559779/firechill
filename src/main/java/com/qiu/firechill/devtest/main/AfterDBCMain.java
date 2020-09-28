package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.devtest.mapper.TestMapper;
import com.qiu.firechill.devtest.pojo.QiuUser;
import com.qiu.firechill.session.ConnectBean;
import com.qiu.firechill.session.impl.CommonDBConnectFactory;
import javax.sql.DataSource;
import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(QiuUser.class);
//        //使用自带的方法
//        List<QiuUser> list = action.selectAll();
//
//        System.out.println(list);
        TestMapper interFace = action.getInterFace(TestMapper.class);
        List<QiuUser> all = interFace.findAll();
    }
}
