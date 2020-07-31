package com.onlineschool.demo.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setup connection variables
		String user = "springuser";
		String pass = "springuser";
		String jdbcUrl = "jdbc:mysql://localhost:3306/spring_online_school?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// get connection
		try {
			PrintWriter out = response.getWriter();
		
			out.println("Connecting to database");
			Class.forName(driver);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("Success");
			myConn.close();
			
		} catch (Exception ex) {
			System.out.println("SOMETHING WRONG");
			ex.printStackTrace();
		}
	}
}

