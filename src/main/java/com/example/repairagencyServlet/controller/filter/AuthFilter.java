package com.example.repairagencyServlet.controller.filter;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
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
        AppUser appUser = (AppUser) session.getAttribute("user");
        if (appUser == null) {
            appUser = new AppUser();
            appUser.setRole(Role.UNKNOWN);
            session.setAttribute("user", appUser);
        }

        String requestURI = req.getRequestURI();
        if (!isPermitted(appUser.getRole(), requestURI)) {
            req.getRequestDispatcher("/WEB_INF/view/homepage.jsp").forward(req, res);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPermitted(Role role, String path) {
        boolean permitted = false;
        switch (role) {
            case ADMIN:
                permitted = path.matches("(/repairagencyServlet/admin.*)|(/repairagencyServlet/logout)");
                break;
            case CUSTOMER:
                permitted = path.matches("(/repairagencyServlet/customer.*)|(/repairagencyServlet/logout)");
                break;
            case MASTER:
                permitted = path.matches("(/repairagencyServlet/master.*)|(/repairagencyServlet/logout)");
                break;
            case UNKNOWN:
                permitted = path.matches("(/repairagencyServlet/login)|(/repairagencyServlet/registration)|(/repairagencyServlet/logout)");
                break;
        }
        return permitted | path.matches("(/repairagencyServlet/home)|(/repairagencyServlet/first-servlet)");
    }

    @Override
    public void destroy() {

    }
}
