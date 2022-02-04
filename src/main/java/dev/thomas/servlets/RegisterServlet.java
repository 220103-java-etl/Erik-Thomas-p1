package dev.thomas.servlets;

import dev.thomas.models.User;
import dev.thomas.repositories.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Add user to database
        User u = UserDAO.getByUsername(username);
        if(u == null) {
            try {
                User newUser = new User(0, firstName, lastName, username, password, "EMPLOYEE");
                UserDAO.create(newUser);
                System.out.println("User created successfully");
            } catch (Exception e) {
                System.out.println("User creation unsuccessful");
            }
            System.out.println("Registration successful");    
        }
        // Redirect to login page
        response.sendRedirect("index.html");

    }
}