package com.thocode.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.thocode.blog.dao.UserDao;
import com.thocode.blog.entities.Message;
import com.thocode.blog.entities.User;
import com.thocode.blog.helper.ConnectionProvider;
import com.thocode.blog.helper.Helper;

@MultipartConfig
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
	
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("user_email");
			String name = request.getParameter("user_name");
			String password = request.getParameter("user_password");
			String about = request.getParameter("user_about");
			Part part = request.getPart("user_image");

			User user = (User) request.getSession().getAttribute("currentUser");
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			user.setAbout(about);
			String oldFile = user.getProfile();
			if (part!=null) {
				String image = String.valueOf(LocalDateTime.now().getNano()) + ".png";
				user.setProfile(image);
			}

			UserDao userDao = new UserDao(ConnectionProvider.getConnection());

			if (userDao.updateUser(user)) {

				String path =  request.getRealPath("/") + "img" + File.separator + user.getProfile();
				String pathOld = request.getRealPath("/") + "img" + File.separator + oldFile;
				System.out.println(request.getRealPath("/") + "img");
				
				if (!part.getSubmittedFileName().equals("")) {
					Helper.deleteFile(pathOld);
					
					if (Helper.saveFile(part.getInputStream(), path)) {
						Message msg = new Message("Profile details updated", "success", "alert-success");
						HttpSession session = request.getSession();
						session.setAttribute("msg", msg);
					} else {
						Message msg = new Message("Something went wrong...", "error", "alert-danger");
						HttpSession session = request.getSession();
						session.setAttribute("msg", msg);
					}
				}
				
				

			} else {
				Message msg = new Message("Something went wrong...", "error", "alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
			}
		
			response.sendRedirect("profile.jsp");
		}

	}

}
