package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InvalidClassException;


@WebServlet(name = "ListOrdersServlet", urlPatterns = "/listorders")
public class ListOrdersServlet extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListOrdersServlet(OrderService orderService,SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orders", orderService.listOrders());
        springTemplateEngine.process("listorders.html",context,resp.getWriter());
    }
}


