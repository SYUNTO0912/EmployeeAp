package com.employeeap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
    public static Connection getConnection() throws SQLException {
        /**
         * 【修正内容】JDBCドライバの明示的なロード
         * Tomcat環境ではドライバが自動ロードされないことがあるため、
         * Class.forName で明示的にロードします。
         */
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MariaDBのJDBCドライバが見つかりません。", e);
        }

        String url = "jdbc:mariadb://localhost:3306/EmployeeAp";
        String user = "root";       // あなたの設定したユーザー名
        String password = "syunto0912";
        return DriverManager.getConnection(url, user, password);
    }
}
