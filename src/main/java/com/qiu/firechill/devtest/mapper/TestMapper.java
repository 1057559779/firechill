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

    /**
     * 查询所有
     * @return
     */
    @Select(sql = "select * from qiu_user",result = QiuUser.class)
    List<QiuUser> findAll();

    /**
     * 条件查询
     * @param id  具体的值 --  index:参数所在的位置
     * @return
     */
    @Select(sql = "select * from qiu_user where id=?",result = QiuUser.class)
    QiuUser findOne(@SqlParam(index = "1") Integer id);

    /**
     * 根据id删除一条数据
     * @param id
     * @return  具体的值 --  index:参数所在的位置
     */
    @Delete(sql = "delete from qiu_user where id=?")
    Integer delById(@SqlParam(index = "1") Integer id);

    /**
     * 根据id更新uname这个字段的值
     * @param uname  具体的值 --  index:参数所在的位置
     * @param id
     * @return
     */
    @Update(sql = "update qiu_user set uname=?  where id=? ")
    Integer upById(@SqlParam(index = "1") String uname,@SqlParam(index = "2") Integer id);

    /**
     * 插入一条数据，数据中只有一个uname这个值
     * @param uname
     * @return
     */
    @Insert(sql = "insert into qiu_user (uname) values (?)")
    Integer insert(@SqlParam(index = "1") String uname);
}
