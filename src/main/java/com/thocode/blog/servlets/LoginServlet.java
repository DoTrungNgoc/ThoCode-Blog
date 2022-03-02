package com.thocode.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thocode.blog.dao.UserDao;
import com.thocode.blog.entities.Message;
import com.thocode.blog.entities.User;
import com.thocode.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			User user = userDao.getUserByEmailAndPassword(email, password);
			
			if (user == null) {
				Message msg = new Message("Invalid Details ! Try with another", "error", "alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
				
				response.sendRedirect("login_page.jsp");
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", user);
				response.sendRedirect("profile.jsp");
			}
		}
	}

}
