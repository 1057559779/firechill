package com.qiu.firechill.devtest.pojo;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.TableName;

/**
 * @Author qiu
 * @create 2020/10/5 11:54
 * @Des @Des 关联表测试 子表角色
 */
@TableName("qiu_role")
public class Role {

    @ColumnName("id")
    private Integer id;

    @ColumnName("rname")
    private String rname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rname='" + rname + '\'' +
                '}';
    }
}
