package com.incubator.task.servlet;

import com.incubator.task.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RichServlet  extends HttpServlet {

    BankService service = new BankService();
    private String richestuser;

    @Override
    public void init() throws ServletException {

        try {
            richestuser = service.getRichestUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.print("<html>" + "<body>" + "The richest user is:  " + richestuser + "</body>" + "</html>");

    }
}
