package com.example.repairagencyServlet.controller;

import com.example.repairagencyServlet.dao.AppUserDAO;
import com.example.repairagencyServlet.model.AppUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", value = "/registration")
public class AppUserServlet extends HttpServlet {
private AppUserDAO appUserDAO=new AppUserDAO();
    public AppUserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Server at: ").append(req.getContextPath());
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("/WEB-INF/view/registration.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setPassword(password);
        appUserDAO.registerAppUser(appUser);

        appUserDAO.registerAppUser(appUser);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("/WEB-INF/view/homepage.jsp");
        requestDispatcher.forward(req,resp);

    }
}
