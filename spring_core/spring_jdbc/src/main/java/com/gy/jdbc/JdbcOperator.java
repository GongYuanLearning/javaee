package com.gy.jdbc;

import com.gy.exception.CustomDataException;

import java.sql.*;

public class JdbcOperator {
    public static void main(String[] args) throws CustomDataException {
        query();
    }
    public static void query() throws CustomDataException {
        // 1. 注册数据库驱动jar
        String msyqlDriverClassName = "com.mysql.jdbc.Driver";
        try {
            Class.forName(msyqlDriverClassName);
        } catch (ClassNotFoundException e) {
            throw new CustomDataException(String.format("注册数据库驱动jar失败：%s", msyqlDriverClassName), e);
        }
        // 2. 建立连接
        // 3. 准备prepared statement或者statement
        String url = "jdbc:mysql://localhost:3306/spring?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC";
        String username = "root";
        String password = "topcoder";
        String sql = "SELECT * FROM user WHERE id=1";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement psmt = connection.prepareStatement(sql);
        ) {
            // 4. 执行statement
            ResultSet resultSet = psmt.executeQuery();
            // 5. 处理查询结果
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                System.out.println(String.format("学生：%s, 性别：%s", name, sex));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
