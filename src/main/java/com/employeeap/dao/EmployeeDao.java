package com.employeeap.dao;

import com.employeeap.entity.EmployeeEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDao {
    private Connection conn;
    private PreparedStatement stmt;
    public EmployeeDao(Connection conn) {
        this.conn = conn;
    }


    public void addEmployee(EmployeeEntity employee) throws SQLException {
        String sql = "insert into employees (employee_name, age, department_id) values (?,?,?)";
        /**
         * 【修正内容】
         * DAO内で Connection をクローズしてしまうと、呼び出し元で再利用できなくなるため、
         * stmt (PreparedStatement) のみのクローズ管理に変更します。
         * Connection のクローズはサーブレット側の try-with-resources が行います。
         */
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getEmployeeName());
            stmt.setInt(2, employee.getAge());
            stmt.setInt(3, employee.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO: 登録処理中に異常が発生しました");
            e.printStackTrace();
            throw e; // エラーをサーブレットに伝播させて、処理を中断させる
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

    /**
     * 指定された従業員番号に一致する従業員情報を取得します。
     * @param employeeId 取得したい従業員のID
     * @return 該当する従業員情報（存在しない場合はnull）
     * @throws SQLException データベース操作エラー
     */
    public EmployeeEntity getEmployeeById(int employeeId) throws SQLException {
        String sql = "select * from employees where employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EmployeeEntity(
                            rs.getInt("employee_id"),
                            rs.getString("employee_name"),
                            rs.getInt("age"),
                            rs.getInt("department_id")
                    );
                }
            }
        }
        return null;
    }

    /**
     * 従業員情報を更新します。
     * @param employee 更新内容を含む従業員エンティティ（IDで更新対象を特定）
     * @throws SQLException データベース操作エラー
     */
    public void updateEmployee(EmployeeEntity employee) throws SQLException {
        String sql = "update employees set employee_name = ?, age = ?, department_id = ? where employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getEmployeeName());
            stmt.setInt(2, employee.getAge());
            stmt.setInt(3, employee.getDepartmentId());
            stmt.setInt(4, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    public ArrayList<EmployeeEntity> showEmployeeTable() throws SQLException{
        ArrayList<EmployeeEntity> list = new ArrayList<>();
        String sql = "select * from employees";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                EmployeeEntity emp = new EmployeeEntity(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getInt("age"),
                        rs.getInt("department_id")
                );
                list.add(emp);
            }
        }
        return list;
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
