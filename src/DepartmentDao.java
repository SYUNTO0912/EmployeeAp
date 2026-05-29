import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
    private Connection conn;
    private PreparedStatement stmt;
    ResultSet rs;
    public DepartmentDao(Connection conn) {
        this.conn = conn;
    }

    public String showDepartmentName(int departmentId) throws SQLException {
        try {
            String sql = "select department_name from department  where department_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,departmentId);
            rs = stmt.executeQuery();
            rs.next();
            String departmentName = rs.getString(1);
            return  departmentName;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
