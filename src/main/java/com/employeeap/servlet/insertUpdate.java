package com.employeeap.servlet;

import com.employeeap.dao.DepartmentDao;
import com.employeeap.util.ConnectManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class insertUpdate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int updateEmployee = (int)request.getAttribute("selectEmployee");
        try{
            Connection conn = ConnectManager.getConnection();
            PreparedStatement stmt = null;
            String sql = "select * from employees where employee_id = ?";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String name = rs.getString("employee_name");
            int age = rs.getInt("age");
            DepartmentDao ddao = new DepartmentDao(conn);
            String department = ddao.showDepartmentName(rs.getInt("department_id"));

            request.setAttribute("name",name);
            request.setAttribute("age",age);
            request.setAttribute("department",department);

            request.getRequestDispatcher("/WEB-INF/insertUpdate.jsp").forward(request,response);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
