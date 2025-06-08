//package com.learn;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/home")
//public class HomePage extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//  
//    public HomePage() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//		
//		String username = request.getParameter("username");
//
//		out.println("<h1>Home Page</h1>");
//		out.println("<h3>Welcome to user page</h3>" + username);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doGet(request, response);
//		
//	}
//
//}

package com.learn;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 

@WebServlet("/home") 
public class HomePage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false); 
        if (session != null && session.getAttribute("loggedInUser") != null) {
            String username = (String) session.getAttribute("loggedInUser");
            out.println("<h1>Welcome, " + username + "!</h1>");
            
            out.println("<p><a href='logout'>Logout</a></p>"); 
        } else {
           
            response.sendRedirect("login"); 
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Typically, homepage doesn't need a doPost unless it has forms
        doGet(request, response);
    }
}
