package com.employeeap.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/selectProcess")
public class selectServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = "";
        String action = request.getParameter("process");
        if (action.equals("add")) {
            next = "/WEB-INF/addEmployee.jsp" ;
        }

        else if(action.equals("delete")) {
            next = "/WEB-INF/deleteEmployee.jsp" ;

        }

        else if (action.equals("update")) {
            next = "selectUpdate" ;
        }

        else if (action.equals("view")) {
            next = "/WEB-INF/showDepartmentName.jsp" ;
        }

        else {
            next = "/WEB-INF/index.jsp" ;
        }

        if (! next.isEmpty()) {
            request.getRequestDispatcher(next).forward(request,response);
        }
    }
}
