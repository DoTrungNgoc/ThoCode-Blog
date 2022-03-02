package com.thocode.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thocode.blog.entities.User;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean saveUser(User user) {
		boolean rs = false;
		try {
			String query = "INSERT INTO `thocodeblog`.`user` (`name`, `email`, `password`, `gender`, `about`) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getAbout());
			
			stmt.executeUpdate();
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean updateUser(User user) {
		boolean rs = false;
		try {
			String query = "UPDATE `thocodeblog`.`user` SET `name` = ?, `email` = ?, `password` = ?, about = ?, profile = ? WHERE (`id` = ?);";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getAbout());
			stmt.setString(5, user.getProfile());
			
			stmt.setInt(6, user.getId());
			
			stmt.executeUpdate();
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		try {
			String query = "select * from user where email=? and password=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setPassword(rs.getString("password"));
				user.setAbout(rs.getString("about"));
				user.setDateTime(rs.getTimestamp("rdate"));
				user.setProfile(rs.getString("profile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserByUserId(int id) {
		User user = null;
		try {
			String query = "select * from user where id=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setPassword(rs.getString("password"));
				user.setAbout(rs.getString("about"));
				user.setDateTime(rs.getTimestamp("rdate"));
				user.setProfile(rs.getString("profile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
