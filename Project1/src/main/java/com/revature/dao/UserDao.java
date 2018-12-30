package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDao implements DAO<User, Integer> {

	@Override
	public List<User> findAll() {
		
		List<User> user = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String query ="select * from ers_users order by user_first_name";
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement =  conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB query
			ResultSet rs =  statement.executeQuery(query);
			
			//return false when there are no more rows in result set
			
			while(rs.next()) {
				User temp = new User();
				temp.setUserId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRoleId(rs.getInt(7));
				user.add(temp);
				
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User findById(Integer id) {

		User temp = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from ers_users where ers_users_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new User();
				temp.setUserId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRoleId(rs.getInt(7));

			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	
	public User validateUser(String username, String password) {
		
		User temp = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from ers_users where ers_username = ? and ers_user_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				temp = new User();
				temp.setUserId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRoleId(rs.getInt(7));
				
			}
			
		
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public User save(User obj) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ERS_USERS(ers_username, ers_user_password, user_first_name, user_last_name, user_email, user_role_id) VALUES(?,?,?,?,?,?,?)";
			String[] keyNames = {"Ers_Users_Id"};  //working on sequence and trigger.
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getUsername());
			ps.setString(4, obj.getPassword());
			
			int numRows = ps.executeUpdate();
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setUserId(pk.getInt(1));
				}
				conn.commit();
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

}
