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
		@WebInitParam(name = "user", value = "Jeeva"), @WebInitParam(name = "password", value = "BridgeLabz") })

public class LoginServlet extends HttpServlet {

	/*
	 * Purpose : Method to allow a servlet to handle a POST request.
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get request parameters for userID and password
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");

		// get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");

		/*
		 * Purpose : Validate username using Regular Expression Name starts with Cap and
		 * has minimum 3 characters
		 */

		String regexName = "^[A-Z]{1}[a-zA-Z]{2,}$";
		/*
		 * Purpose : Validate password using Regular Expression Rule1 – minimum 8
		 * Characters Rule2 – Should have at least 1 Upper Case Rule3 – Should have at
		 * least 1 numeric number in the password Rule4 – Has exactly 1 Special
		 * Character
		 */

		String regexPassword = "^(?=.*[0-9])(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

		if (userID.equals(user) && userID.matches(regexName) && password.equals(pwd)
				&& password.matches(regexPassword)) {
			req.setAttribute("user", user);
			req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = resp.getWriter();
			out.println("<font color=red>Either username or password is incorrect!</font>");
			rd.include(req, resp);
		}
	}

}