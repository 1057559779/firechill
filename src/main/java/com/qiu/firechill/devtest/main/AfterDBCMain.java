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
        //得到配置信息
        ConnectBean config = new MyDataSourceConfig().config();

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(config,QiuUser.class);

        //自定义mapper
        TestMapper interFace = action.getInterFace(TestMapper.class);

        List<QiuUser> all = interFace.findAll();

        //使用自带的方法
//        List<QiuUser> list = action.selectAll();
//
//        System.out.println(list);
    }
}
