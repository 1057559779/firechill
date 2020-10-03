package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.ann.Insert;
import com.qiu.firechill.devtest.mapper.TestMapper;
import com.qiu.firechill.devtest.pojo.QiuUser;
import com.qiu.firechill.session.impl.CommonDBConnectFactory;

import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        SqlAction<QiuUser> action = new CommonDBConnectFactory().getAction(QiuUser.class);
        List<QiuUser> list = action.selectAll();
        System.out.println(list);
//        QiuUser qiuUser = new QiuUser();
//        qiuUser.setId(6);
//        qiuUser.setUname("helloqiu");
//        Integer integer = action.insertOne(qiuUser);

    }
}
