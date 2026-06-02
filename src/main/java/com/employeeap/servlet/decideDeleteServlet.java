package com.employeeap.servlet;

import com.employeeap.util.ConnectManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/decideDeleteEmployee")
public class decideDeleteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection conn = ConnectManager.getConnection()) {
            int deleteNumber = Integer.parseInt(request.getParameter("employeeId"));
            String sql = "delete from employees where employee_id = ?";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,deleteNumber);
            stmt.executeUpdate();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

        }
        catch (SQLException | NumberFormatException e) {

        }

    }
}
