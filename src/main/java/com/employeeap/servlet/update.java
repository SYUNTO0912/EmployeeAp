package com.employeeap.servlet;

import com.employeeap.util.ConnectManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/update")
public class update extends HttpServlet  {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            String sql = "update employee set employee_id = ?,age = ?,employee_name = ?,";
            conn = ConnectManager.getConnection();
            stmt= conn.prepareStatement(sql);

            String name = request.getParameter("name");
            String age = request.getParameter("age");
            if(age.isEmpty()) {
                int employeeName = -1;
            }
            else{
                int employeeName = Integer.parseInt(age);
            }
            String department = request.getParameter("departmentName");

            if (name.isEmpty() && age.isEmpty()) {

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
