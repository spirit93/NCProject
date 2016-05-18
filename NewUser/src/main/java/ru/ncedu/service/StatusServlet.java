package ru.ncedu.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dan on 08.05.2016.
 */
@WebServlet("/stservlet/*")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int orderId = Integer.parseInt(req.getParameter("id"));
        int status = Integer.parseInt(req.getParameter("status"));
        MarketService.changeStatusOfOrder(orderId,status);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(Servlet.getHTMLOrders());
    }
}
