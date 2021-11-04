package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationInfoServlet", urlPatterns = "/confirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        String size = req.getParameter("size");
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        String clientAgent = req.getHeader("User-Agent");
        String ipAddress = req.getRemoteAddr();
        if (color == null) {
            resp.sendRedirect("/");
        }

        Order order = orderService.placeOrder(color,clientName,clientAddress);
        req.getSession().setAttribute("order",order);
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("ipAddress",ipAddress);
        context.setVariable("clientAgent",clientAgent);
        context.setVariable("size",size);
        context.setVariable("color",color);

        springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonSize = req.getParameter("size");
        req.getSession().setAttribute("size", balloonSize);
        resp.sendRedirect("/BalloonOrder");
    }
}
