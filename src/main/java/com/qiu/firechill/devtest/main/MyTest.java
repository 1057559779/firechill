package com.qiu.firechill.devtest.main;


import com.qiu.firechill.action.SqlActionProxy;
import com.qiu.firechill.devtest.mapper.TestMapper;
import com.qiu.firechill.devtest.pojo.QiuUser;

import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/27 10:50
 */
public class MyTest {
    public static void main(String[] args) {
        SqlActionProxy<TestMapper> sqlActionProxy = new SqlActionProxy(TestMapper.class);
        TestMapper proxy = sqlActionProxy.getProxy();
        List<QiuUser> all = proxy.findAll();
        System.out.println(all);
    }
}
