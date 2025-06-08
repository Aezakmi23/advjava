package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out = null;

	String url = "jdbc:mysql://localhost:3306/user_b1";
	String dbUsername = "root";
	String dbPassword = "root";

	public RegisterPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();

		out.println("<h1>Register Page</h1>");
		out.println("<form action='register' method='post'>");
		out.println("<label>Email </label>");
		out.println("<input type='email' name='email'>");
		out.println("<br/>");
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
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		out = response.getWriter();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			ps = con.prepareStatement("insert into Person1(email, username, password) values(?,?,?)");
			ps.setString(1, email);
			ps.setString(2, username);
			ps.setString(3, password);

			int res = ps.executeUpdate();

			if (res > 0) {
//				  out.println("<h2>Registration Successful!</h2>");
				response.sendRedirect("login");
			} else {
				response.setContentType("text/html");
				out.println("<h2>Registration not Successful!</h2>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
