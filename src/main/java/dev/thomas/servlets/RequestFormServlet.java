package dev.thomas.servlets;

import dev.thomas.models.Reimbursement;
import dev.thomas.models.Status;
import dev.thomas.models.User;
import dev.thomas.repositories.ReimbursementDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class RequestFormServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        
        // Add reimbursement to database
        if(session == null){
            // no session, so we can't get the user object
            PrintWriter out = response.getWriter();
            out.write("<a href='index.html'>Click Here</a>");
        } else {
            int cost = Integer.parseInt(request.getParameter("cost"));
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            String justification = request.getParameter("justification");

            User author = (User) session.getAttribute("user");
            Status status = Status.PENDING;
            User resolver = new User();
        try {
            Reimbursement newReimbursement = new Reimbursement(0, status, author, resolver, cost, date, time, location, description, justification);
            ReimbursementDAO.update(newReimbursement);
            ReimbursementDAO.updateAllowance(newReimbursement);
            response.sendRedirect("employee.html"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    }
}
