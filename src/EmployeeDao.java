import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {
    private Connection conn;
    private PreparedStatement stmt;
    public EmployeeDao(Connection conn) {
        this.conn = conn;
    }


    public void addEmployee(EmployeeEntity employee) throws SQLException {
        String sql = "insert into employees (employee_name, age, department_id) values (?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(2, employee.getEmployeeName());
            stmt.setInt(3, employee.getAge());
            stmt.setInt(4, employee.getDepartmentId());
            int rows = stmt.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("異常が発生しました");
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }



    public void showEmployeeTable() throws SQLException{
        ArrayList<EmployeeEntity> list = new ArrayList<>();
        String sql = "select * from employees";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                EmployeeEntity emp = new EmployeeEntity(rs.getString(2),rs.getInt(3),rs.getInt(4));

                list.add(emp);
            }
            for (EmployeeEntity emp : list) {
                System.out.print("従業員番号:" + emp.getEmployeeId() + ",");
                System.out.print("名前:" + emp.getEmployeeName() + ",");
                System.out.print("年齢:" + emp.getAge() + ",");
                System.out.print("部署番号:" + emp.getDepartmentId() );
                System.out.println(" ");
            }

        }
        catch(SQLException e) {
            System.out.println("異常が発生しました");
        }

        finally {

            if(stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

    }}
