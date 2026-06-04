package com.employeeap.servlet;

import com.employeeap.dao.EmployeeDao;
import com.employeeap.entity.EmployeeEntity;
import com.employeeap.util.ConnectManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/insertUpdate")
public class insertUpdate extends HttpServlet {
    /**
     * 選択された従業員の詳細情報を取得し、編集画面を表示します。
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        // 選択された従業員IDを取得
        String selectEmployeeStr = request.getParameter("selectEmployee");
        if (selectEmployeeStr == null || selectEmployeeStr.isEmpty()) {
            response.sendRedirect("selectUpdate");
            return;
        }

        int updateEmployeeId = Integer.parseInt(selectEmployeeStr);
        try (Connection conn = ConnectManager.getConnection()) {
            EmployeeDao edao = new EmployeeDao(conn);
            // IDをキーに従業員情報を取得
            EmployeeEntity employee = edao.getEmployeeById(updateEmployeeId);

            if (employee != null) {
                // 取得したデータをセットして編集画面（JSP）へ
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("/WEB-INF/insertUpdate.jsp").forward(request, response);
            } else {
                // 該当なしの場合は選択画面に戻す
                response.sendRedirect("selectUpdate");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
