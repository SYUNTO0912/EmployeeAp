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
            stmt.setString(1, employee.getEmployeeName());
            stmt.setInt(2, employee.getAge());
            stmt.setInt(3, employee.getDepartmentId());
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

    public void deleteEmployee(int employeeId) throws  SQLException{
        try {
            String sql = "delete from employees where employee_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,employeeId);
            int rows = stmt.executeUpdate();
        }
        catch (SQLException e ) {
            System.out.println("異常終了");
            e.printStackTrace();
        }
        finally {
            stmt.close();

        }
    }


    public void showEmployeeTable() throws SQLException{
        ArrayList<EmployeeEntity> list = new ArrayList<>();
        String sql = "select * from employees";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                EmployeeEntity emp = new EmployeeEntity(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));

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

    }

    public String showDepartmentName(int showDepartmentEmployeeId) throws SQLException {
        try {
            DepartmentDao ddao = new DepartmentDao(conn);
            String departmentName = ddao.showDepartmentName(showDepartmentEmployeeId);
            return departmentName;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
