package com.incubator.task.servlet;

import com.incubator.task.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AccServlet extends HttpServlet {

    BankService service = new BankService();
    private int accSum;

    @Override
    public void init() throws ServletException {

        try {
            accSum = service.accSum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.print("<html>" + "<body>" + "Sum of all the accounts in the bank =  " + accSum + "</body>" + "</html>");

    }
}
