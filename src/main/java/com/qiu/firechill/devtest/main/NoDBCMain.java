package com.qiu.firechill.devtest.main;

import java.sql.*;

/**
 * @Author qiu
 * @create 2020/9/25 9:44
 * @Des 没有数据库连接池的jdbc
 */
public class NoDBCMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url="jdbc:mysql://120.55.88.202:3306/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, uname FROM qiu_user");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println("用户名："+rs.getString("uname")+" id："+rs.getInt("id"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
