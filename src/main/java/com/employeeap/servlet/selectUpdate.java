package com.employeeap.servlet;

import com.employeeap.util.ConnectManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/selectUpdate")
public class selectUpdate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            String sql = "select employee_id from employees";
            conn = ConnectManager.getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Integer> employeeNumber = new ArrayList<>();
            while(rs.next()) {
                employeeNumber.add(rs.getInt("employee_id"));
            }

            request.setAttribute("employeeNumber",employeeNumber);
            request.getRequestDispatcher("/WEB-INF/selectUpdate.jsp").forward(request,response);

        }
         catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
