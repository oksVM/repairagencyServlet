package com.example.repairagencyServlet.controller.filter;

import com.example.repairagencyServlet.model.entity.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();
        Role role = (Role) session.getAttribute("role");
        if (role == null) {
            session.setAttribute("role", Role.UNKNOWN);
            role = (Role) session.getAttribute("role");
        }


        String requestURI = req.getRequestURI();

        if (!isPermitted(role, requestURI)) {
            req.getRequestDispatcher("/WEB_INF/view/homepage.jsp").forward(req, res);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPermitted(Role role, String path) {
        boolean permitted = false;
        switch (role) {
            case ADMIN:
                permitted = path.matches("(/admin.*)|(/logout)");
                break;
            case CUSTOMER:
                permitted = path.matches("(/customer.*)|(/logout)");
                break;
            case MASTER:
                permitted = path.matches("(/master.*)|(/logout)");
                break;
            case UNKNOWN:
                permitted = path.matches("(/login)|(/registration)|(/logout)");
                break;
        }
        return permitted | path.matches("(/)|(/main)");
    }

    @Override
    public void destroy() {

    }
}
