package com.employeeap.servlet;


import com.employeeap.dao.EmployeeDao;
import com.employeeap.entity.EmployeeEntity;
import com.employeeap.util.ConnectManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addEmployee")
public class addServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement stmt;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

    try{
        conn = ConnectManager.getConnection();
        EmployeeEntity emp = new EmployeeEntity(name,age,departmentId);
        EmployeeDao edao = new EmployeeDao(conn);

        edao.addEmployee(emp);
        response.sendRedirect(request.getContextPath() + "index.jsp");
        }


        catch(SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
        throw new RuntimeException(e);
    }


    }
}
