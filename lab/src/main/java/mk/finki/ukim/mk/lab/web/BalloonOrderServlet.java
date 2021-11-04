package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        String size = req.getParameter("size");
        if (color == null) {
            resp.sendRedirect("/");
        }
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("color",color);
        context.setVariable("size", size);

        springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String balloonSize = req.getParameter("size");
        req.getSession().setAttribute("size",balloonSize);
        resp.sendRedirect("/BalloonOrder");
    }
}
