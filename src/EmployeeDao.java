import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeeDao {
    private Connection conn;
    private PreparedStatement stmt;
    public EmployeeDao(Connection conn) {
        this.conn = conn;
    }

    public void addEmployee(String name,int age,int department_id) {

    }
}
