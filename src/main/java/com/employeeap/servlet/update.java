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

@WebServlet("/update")
public class update extends HttpServlet {
    /**
     * フォームから送信されたデータをもとにデータベースを更新します。
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // パラメータの取得
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String departmentIdStr = request.getParameter("departmentId");

        // バリデーション：未入力チェック
        if (idStr == null || name == null || ageStr == null || departmentIdStr == null ||
            idStr.isEmpty() || name.isEmpty() || ageStr.isEmpty() || departmentIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "すべてのフィールドを入力してください。");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        try (Connection conn = ConnectManager.getConnection()) {
            // 文字列から数値への変換
            int id = Integer.parseInt(idStr);
            int age = Integer.parseInt(ageStr);
            int departmentId = Integer.parseInt(departmentIdStr);

            // 更新処理の実行
            EmployeeDao edao = new EmployeeDao(conn);
            EmployeeEntity employee = new EmployeeEntity(id, name, age, departmentId);
            edao.updateEmployee(employee);

            // 完了後は選択画面へリダイレクト（再送信防止のため）
            // selectUpdateはdoGetをサポートするように修正済み
            response.sendRedirect("selectUpdate");

        } catch (SQLException | NumberFormatException e) {
            // 数値変換失敗やSQLエラーの場合は例外をスロー（またはエラー画面へ）
            throw new ServletException(e);
        }
    }
}
