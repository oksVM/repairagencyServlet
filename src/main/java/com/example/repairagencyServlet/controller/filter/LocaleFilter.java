package com.example.repairagencyServlet.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*")
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("lang") == null || req.getParameter("lang") != null) {
            setLocale(req);
        }

        chain.doFilter(request, response);
    }

    private void setLocale(HttpServletRequest req) {
        String lang;
        if (req.getParameter("lang") != null) {
            lang = req.getParameter("lang");
        } else {
            lang = "en";
        }
        req.getSession().setAttribute("lang", lang);
    }

    @Override
    public void destroy() {
    }
}
