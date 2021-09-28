package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.model.entity.AppUser;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    public static void setUserSession(HttpServletRequest request, AppUser user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("user", user.getEmail());
        session.setAttribute("user", user);
    }

    public static AppUser getCurrentUser(HttpServletRequest request) {
        return ((AppUser) request.getSession().getAttribute("user"));
    }

    public static long getCurrentUserId(HttpServletRequest request) {
        return ((AppUser) request.getSession().getAttribute("user")).getId();
    }

    public static String getCurrentUserLanguage(HttpServletRequest request) {
        return request.getSession().getAttribute("lang").toString();
    }

    public static void removeUserSession(ServletContext context, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.remove(userName);
        context.setAttribute("loggedUsers", loggedUsers);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
