package com.employeeap.servlet;

import com.employeeap.dao.DepartmentDao;
import com.employeeap.dao.EmployeeDao;
import com.employeeap.util.ConnectManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet { // 【修正】HttpServletの継承が抜けていました

    @Override // 【修正】標準的な doPost のシグネチャに変更
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String deleteNumberStr = request.getParameter("deleteNumber");
        if (deleteNumberStr == null || deleteNumberStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        /**
         * 【修正】try-with-resources を使用して Connection, PreparedStatement, ResultSet を自動クローズ
         */
        try (Connection conn = ConnectManager.getConnection()) {
            int deleteId = Integer.parseInt(deleteNumberStr);
            
            String sql = "select employee_name, age, department_id from employees where employee_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, deleteId);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) { // 【修正】データが存在する場合のみ処理
                        String name = rs.getString("employee_name");
                        int age = rs.getInt("age");
                        int departmentId = rs.getInt("department_id");


                        request.setAttribute("employeeId", deleteId);
                        request.setAttribute("name", name);
                        request.setAttribute("age", age);
                        
                        DepartmentDao ddao = new DepartmentDao(conn);
                        String departmentName = ddao.showDepartmentName(departmentId);
                        request.setAttribute("departmentName", departmentName);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/deleteConfirm.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // 該当する従業員がいない場合
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

