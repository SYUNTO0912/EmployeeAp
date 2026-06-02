package com.employeeap.servlet;


import com.employeeap.dao.EmployeeDao;
import com.employeeap.entity.EmployeeEntity;
import com.employeeap.util.ConnectManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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

    /**
     * 【修正内容】
     * 1. メソッドを protected にし、@Override を付与（サーブレットの標準的な作法）。
     * 2. throws ServletException, IOException を追加（例外ハンドリングを適切に行うため）。
     * 3. メンバー変数（conn, stmt）を削除し、ローカル変数へ（スレッド安全性の確保）。
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 【追加】リクエストの文字化け対策（日本語入力に対応）
        request.setCharacterEncoding("UTF-8");

        // パラメータの取得
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String deptIdStr = request.getParameter("departmentId");

        /**
         * 【修正内容】入力チェックの追加
         * パラメータが空（未入力）の状態で Integer.parseInt を呼ぶとエラー(500)になるため、
         * 事前にチェックを行い、不備があればインデックスへ戻します。
         */
        if (name == null || name.isEmpty() || 
            ageStr == null || ageStr.isEmpty() || 
            deptIdStr == null || deptIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            int departmentId = Integer.parseInt(deptIdStr);

            // JSPの表示用属性をセット
            request.setAttribute("name", name);
            request.setAttribute("age", age);
            request.setAttribute("departmentId", departmentId);

            /**
             * 【修正内容】try-with-resources を使用
             * Connection を自動でクローズするようにし、リソース漏れを防ぎます。
             */
            try (Connection conn = ConnectManager.getConnection()) {
                EmployeeEntity emp = new EmployeeEntity(name, age, departmentId);
                EmployeeDao edao = new EmployeeDao(conn);

                // DBへの追加実行
                edao.addEmployee(emp);

                /**
                 * 【修正内容】遷移先パスの修正
                 * 前回のコードではスペルミス（addComfirm）があったため、正しいファイル名に変更。
                 */
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addConfirm.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // 数値変換エラーのログ出力
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            // データベースエラーのログ出力
            e.printStackTrace();
            throw new ServletException("データベース処理中にエラーが発生しました", e);
        }
    }
}

