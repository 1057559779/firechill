package com.qiu.firechill.devtest.mapper;

import com.qiu.firechill.ann.*;
import com.qiu.firechill.devtest.pojo.QiuUser;

import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/26 12:27
 */

@SqlMapper
public interface TestMapper {

    @Select(sql = "select * from qiu_user",result = QiuUser.class)
    List<QiuUser> findAll();

    @Select(sql = "select * from qiu_user where id=?",result = QiuUser.class)
    QiuUser findOne(@SqlParam(index = "1") Integer id);

    @Delete(sql = "delete from qiu_user where id=?")
    Integer delById(@SqlParam(index = "1") Integer id);
}
