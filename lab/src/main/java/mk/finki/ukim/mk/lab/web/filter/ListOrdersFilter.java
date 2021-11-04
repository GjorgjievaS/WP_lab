package mk.finki.ukim.mk.lab.web.filter;

import mk.finki.ukim.mk.lab.model.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class ListOrdersFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Order order = (Order) request.getSession().getAttribute("order");
        String PublicPath = request.getServletPath();

        if (!"".equals(PublicPath) && !"/BalloonOrder".equals(PublicPath) && !"/confirmationInfo".equals(PublicPath)
                && !"/logout".equals(PublicPath)&& !"/selectBalloon".equals(PublicPath)&&order == null) {
            response.sendRedirect("");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

