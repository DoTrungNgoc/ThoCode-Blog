<%@page import="com.thocode.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.thocode.blog.helper.ConnectionProvider"%>
<%@page import="com.thocode.blog.dao.PostDao"%>

<div class="row">
	<%
		PostDao postDao = new PostDao(ConnectionProvider.getConnection());
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Post> posts= null;
		if (cid==0){
			posts = postDao.getAllPost();
		}else{
			posts = postDao.getPostByCatId(cid);
		}
		
		if (posts.size()==0){
			out.print("<h3 class = 'display-3 text-center'>No post in this category</h3>");
		}
		for (Post p : posts) {
	%>
		<div class="col-md-6 mt-2">
			<div class="card">
				<img class="cart-img-top" alt="Card image cap" src="blog_pic/<%=p.getpPic()%>">
				<div class="card-body">
					<b><%= p.getpTitle() %></b>
					<p><%= p.getpContent() %></p>
					<pre><%= p.getpCode() %></pre>
				</div>
				
				<div class="card-footer bg-dark">
					<a href="show_blog_page.jsp?post_id=<%= p.getPid() %>" class="btn btn-outline-light btn-sm">Read more...</a>
					<a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i><span>10</span></a>
					<a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o"></i><span>20</span></a>
				</div>
			
			</div>
		</div>
	<%
		}
	%>
</div>