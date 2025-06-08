package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out = null;

	String url = "jdbc:mysql://localhost:3306/user_b1";
	String dbUsername = "root";
	String dbPassword = "root";

	public LoginPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();
		out.println("<h1>Login Page</h1>");
		out.println("<form action='login' method='post'>");
		out.println("<label>Username </label>");
		out.println("<input type='text' name='username'>");
		out.println("<br/>");
		out.println("<label>Password </label>");
		out.println("<input type='password' name='password'>");
		out.println("<br/>");
		out.println("<input type='submit'>");
		out.println("</form>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			ps = con.prepareStatement("select *  from Person1 where username = ? and password = ?");

			ps.setString(1, username);
			ps.setString(2, password);
			
			System.out.println(username+" ---- "+password);

			rs = ps.executeQuery();

			if (rs.next()) {
				
				String loggedInUsername = rs.getString("username"); 

			    
			    request.getSession().setAttribute("loggedInUser", loggedInUsername);
			    
				response.sendRedirect("home");
			} else {
				response.setContentType("text/html");
				out.println("<h2>Login not Successful!</h2>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
