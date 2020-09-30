package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.SqlAction;
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
        //使用自带的方法
        QiuUser qiuUser = action.selectOneByCol("uname","firechildren");
        System.out.println(qiuUser);

    }
}
