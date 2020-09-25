package com.qiu.firechill.devtest.pojo;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;

/**
 * @Author qiu
 * @create 2020/9/25 11:39
 * @Des 实体类测试
 */

@TableName("qiu_user")
public class QiuUser {

    @ColumnName("id")
    private Integer id;

    @ColumnName("uname")
    private String uname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
