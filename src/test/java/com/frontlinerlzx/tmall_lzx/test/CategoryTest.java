package com.frontlinerlzx.tmall_lzx.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lzx
 * @create 2019-08-27-10:25
 */
public class CategoryTest {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tmall_springboot?useUnicode=true&characterEncoding=utf8",
                    "root", "root");
            Statement statement = conn.createStatement();


            for (int i = 1; i <= 1; i++) {
                String sqlFormat = "insert into category values (null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                statement.execute(sql);
            }

            System.out.println("已经成功创建1条分类测试数据");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
