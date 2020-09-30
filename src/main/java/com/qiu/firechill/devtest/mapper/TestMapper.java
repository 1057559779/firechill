package com.qiu.firechill.devtest.mapper;

import com.qiu.firechill.ann.Select;
import com.qiu.firechill.ann.SqlMapper;
import com.qiu.firechill.ann.SqlParam;
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

    @Select(sql = "select * from qiu_user where id=1",result = QiuUser.class)
    QiuUser findOne(@SqlParam(index = "1") Integer id);
}
