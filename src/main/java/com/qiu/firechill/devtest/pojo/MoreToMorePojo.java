package com.qiu.firechill.devtest.pojo;

import com.qiu.firechill.ann.OneToOne;
import com.qiu.firechill.ann.TableName;

import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/10/5 10:49
 */
@TableName("MoreToMorePojo")
public class MoreToMorePojo {

    @OneToOne(skey = "sid",pkey = "pid")
    private QiuUser qiuUser;

    private List<QiuUser> qiuUsers;


}
