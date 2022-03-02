package com.thocode.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thocode.blog.entities.Category;
import com.thocode.blog.entities.Post;
import com.thocode.blog.entities.User;

public class PostDao {
	private Connection con;

	public PostDao(Connection con) {
		super();
		this.con = con;
	}

	public List<Category> getAllCategories() {
		ArrayList<Category> list = new ArrayList<Category>();

		try {
			String query = "select * from categories";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Category item = new Category();
				item.setCid(rs.getInt("cid"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean savePost(Post post) {
		boolean rs = false;

		try {
			String query = "INSERT INTO `thocodeblog`.`posts` (`pTitle`, `pContent`, `pCode`, `pPic`, `cateId`, `userId`) VALUES (?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, post.getpTitle());
			stmt.setString(2, post.getpContent());
			stmt.setString(3, post.getpCode());
			stmt.setString(4, post.getpPic());
			stmt.setInt(5, post.getcId());
			stmt.setInt(6, post.getUserId());
			stmt.executeUpdate();
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public List<Post> getAllPost() {
		List<Post> list = new ArrayList<Post>();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from posts order by pid desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setPid(rs.getInt("pid"));
				post.setpTitle(rs.getString("pTitle"));
				post.setpContent(rs.getString("pContent"));
				post.setpCode(rs.getString("pCode"));
				post.setpPic(rs.getString("pPic"));
				post.setpDate(rs.getTimestamp("pDate"));
				post.setcId(rs.getInt("cateId"));
				post.setUserId(rs.getInt("userId"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Post> getPostByCatId(int catId){

		List<Post> list = new ArrayList<Post>();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from posts where cateId = ?");
			stmt.setInt(1, catId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setPid(rs.getInt("pid"));
				post.setpTitle(rs.getString("pTitle"));
				post.setpContent(rs.getString("pContent"));
				post.setpCode(rs.getString("pCode"));
				post.setpPic(rs.getString("pPic"));
				post.setpDate(rs.getTimestamp("pDate"));
				post.setcId(rs.getInt("cateId"));
				post.setUserId(rs.getInt("userId"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Post getPostByPostId(int id) {
		Post post = null;
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from posts where pid = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				post = new Post();
				post.setPid(rs.getInt("pid"));
				post.setpTitle(rs.getString("pTitle"));
				post.setpContent(rs.getString("pContent"));
				post.setpCode(rs.getString("pCode"));
				post.setpPic(rs.getString("pPic"));
				post.setpDate(rs.getTimestamp("pDate"));
				post.setcId(rs.getInt("cateId"));
				post.setUserId(rs.getInt("userId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return post;
	}
}
