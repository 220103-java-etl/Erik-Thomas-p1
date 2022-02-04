package dev.thomas.servlets;

import dev.thomas.models.User;
import dev.thomas.repositories.UserDAO;
import dev.thomas.services.AuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    private static final AuthService authService = new AuthService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);

        try {
            // Check if user is valid
            User userAuth = authService.login(username, password);
            if(userAuth != null) {
                // Create a http session with that user info
                HttpSession session = request.getSession();

                // Set the session object
                User user = UserDAO.getByUsername(username);
                session.setAttribute("user", user);

                User userRole = UserDAO.getByUsername(username);
                String role = userRole.getRole();
                if(role.equals("EMPLOYEE")) { 
                    response.sendRedirect("employee.html");
                } else {
                    response.sendRedirect("manager.html");
                }
            } else {
                response.sendRedirect("index.html");
            }
        } catch (Exception e) {
            System.out.println("Invalid username or password");
        }
    }
}