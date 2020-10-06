package com.qiu.firechill.devtest.main;

import com.qiu.firechill.action.ReadSqlAction;
import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.ann.Insert;
import com.qiu.firechill.devtest.mapper.TestMapper;
import com.qiu.firechill.devtest.pojo.QiuUser;
import com.qiu.firechill.devtest.pojo.Role;
import com.qiu.firechill.devtest.pojo.User;
import com.qiu.firechill.session.impl.CommonDBConnectFactory;

import java.util.List;


/**
 * @Author VULCAN
 * @create 2020/9/25 15:51
 */
public class AfterDBCMain {

    public static void main(String[] args) throws Exception {

        ReadSqlAction read = new CommonDBConnectFactory().getReadSqlAction(User.class);

        List<User> list = read.selectAll();
    }
}
