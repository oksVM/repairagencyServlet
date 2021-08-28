package com.example.repairagencyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "firstServlet", value = "/first-servlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        //pw.println("<html>");
        //pw.println("<h1>Oksana</h1>");
        //pw.println("</html>");*/
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/agencydb",
                    "admindb", "2021");
            //PreparedStatement preparedStatement=connection.prepareStatement(INSERT_APPUSER_SQL);
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT email FROM app_user");
            while (resultSet.next()){
                pw.println(resultSet.getString("email"));
            }
            statement.close();
        } catch (SQLException sqlException){
sqlException.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
