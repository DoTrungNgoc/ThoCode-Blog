package com.thocode.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PublicKey;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.thocode.blog.dao.PostDao;
import com.thocode.blog.entities.Post;
import com.thocode.blog.entities.User;
import com.thocode.blog.helper.ConnectionProvider;
import com.thocode.blog.helper.Helper;

/**
 * Servlet implementation class AddPostServlet
 */
@MultipartConfig
@WebServlet("/add-post")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			int cid = Integer.parseInt(request.getParameter("cid"));
			String pTitle = request.getParameter("pTitle");
			String pContent = request.getParameter("pContent");
			String pCode = request.getParameter("pCode");
			Part part = request.getPart("pic");
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			
			Post post = new Post(pTitle, pContent, pCode, part.getSubmittedFileName(), cid, user.getId());
			PostDao postDao = new PostDao(ConnectionProvider.getConnection());
			if (postDao.savePost(post)) {
				out.print("done");
				String path = request.getRealPath("/") + "blog_pic" + File.separator + part.getSubmittedFileName();
				Helper.saveFile(part.getInputStream(), path);
				System.out.println(path);
			}else {
				out.print("error");
			}
			
			
		}
	}

}
