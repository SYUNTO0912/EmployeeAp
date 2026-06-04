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
import java.util.ArrayList;

@WebServlet("/selectUpdate")
public class selectUpdate extends HttpServlet {
    /**
     * 更新対象の従業員を選択するための画面を表示します。
     * GET/POST両方で同じ選択画面を表示するようにします。
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ConnectManager.getConnection()) {
            EmployeeDao edao = new EmployeeDao(conn);
            // DBから全従業員を取得
            ArrayList<EmployeeEntity> employees = edao.showEmployeeTable();
            
            // JSPのセレクトボックス用にIDだけのリストを作成
            ArrayList<Integer> employeeNumber = new ArrayList<>();
            if (employees != null) {
                for (EmployeeEntity emp : employees) {
                    employeeNumber.add(emp.getEmployeeId());
                }
            }

            // IDリストをリクエストスコープにセット
            request.setAttribute("employeeNumber", employeeNumber);
            // 選択画面へフォワード
            request.getRequestDispatcher("/WEB-INF/selectUpdate.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
