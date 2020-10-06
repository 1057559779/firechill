package com.qiu.firechill.devtest.pojo;

import com.qiu.firechill.ann.ColumnName;
import com.qiu.firechill.ann.OneToOne;
import com.qiu.firechill.ann.TableName;

/**
 * @Author qiu
 * @create 2020/10/5 11:46
 * @Des 关联表测试 主表用户
 */

@TableName("qiu_user")
public class User {

    @ColumnName("id")
    private Integer id;

    @ColumnName("uname")
    private String uname;

    @ColumnName("rid")
    private Integer rid;

    //关联子表  select * FROM qiu_user LEFT JOIN qiu_role ON qiu_user.rid = qiu_role.id
    @OneToOne(pkey = "rid",skey = "id")
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", rid='" + rid + '\'' +
                ", role=" + role +
                '}';
    }
}
