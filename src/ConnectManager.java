import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/EmployeeAp";
        String user = "root";       // あなたの設定したユーザー名
        String password = "syunto0912";
        return DriverManager.getConnection(url, user, password);
    }
}
