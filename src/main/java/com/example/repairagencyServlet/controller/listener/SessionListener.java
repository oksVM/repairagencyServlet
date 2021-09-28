package com.example.repairagencyServlet.controller.listener;

import com.example.repairagencyServlet.model.entity.Role;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute("userName");
        loggedUsers.remove(userName);
        httpSessionEvent.getSession().removeAttribute("userName");
        httpSessionEvent.getSession().removeAttribute("user");
        httpSessionEvent.getSession().setAttribute("role", Role.UNKNOWN);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
