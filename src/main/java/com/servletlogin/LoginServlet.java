package com.servletlogin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Anant"), @WebInitParam(name = "password", value = "Anant@123") })

public class LoginServlet extends HttpServlet {

	/*
	 * Purpose : Method to allow a servlet to handle a POST request.
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * / Get Request parameters for userId and Password
		 */
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		/*
		 * / get servlet configuration init parameters
		 */
		String userId = getServletConfig().getInitParameter("userId");
		String pass = getServletConfig().getInitParameter("password");
		if (userId.equals(user) && pass.equals(pwd)) {
			req.setAttribute("user", user);
			req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = resp.getWriter();
			out.println("<font colour=red> Either User name or Password is Wrong</font>");
			rd.include(req, resp);

		}
	}

}