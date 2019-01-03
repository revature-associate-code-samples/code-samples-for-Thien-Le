package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.AccountType;
import com.revature.pojos.BankAccount;
import com.revature.pojos.BankUser;
import com.revature.util.ConnectionFactory;

/**
 * Handling CRUD with database
 * @author thienle
 *
 */
public class BankUserDao implements DAO<BankUser, Integer> {

	/**
	 * Return a list of BankUser
	 */
	@Override
	public List<BankUser> findAll() {

		List<BankUser> user = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String query ="select * from bank_user order by first_name";
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement =  conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB query
			ResultSet rs =  statement.executeQuery(query);
			
			//return false when there are no more rows in result set
			
			while(rs.next()) {
				BankUser temp = new BankUser();
				temp.setUserId(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUsername(rs.getString(4));
				temp.setPassword(rs.getString(5));
				user.add(temp);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	/**
	 * Return a BankUser by id
	 */
	@Override
	public BankUser findById(Integer id) {

		BankUser a = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from bank_account where user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = new BankUser();
				a.setUserId(rs.getInt(1));
				a.setFirstName(rs.getString(2));
				a.setLastName(rs.getString(3));
				a.setUsername(rs.getString(4));
				a.setPassword(rs.getString(5));

			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	/**
	 * Check if userName exist
	 * @param username
	 * @return
	 */
	public boolean checkUserName(String username) {

		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from bank_user where user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
 			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Check userName and PassWord for login
	 * @param username
	 * @param password
	 * @return
	 */
	public BankUser checkUserNamePass(String username, String password) {
		
		BankUser a = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from bank_user where user_name = ? and pass_word = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				a = new BankUser();
				a.setUserId(rs.getInt(1));
				a.setFirstName(rs.getString(2));
				a.setLastName(rs.getString(3));
				
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	/**
	 * Save a BankUser
	 */
	@Override
	public BankUser save(BankUser obj) {
		
			try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO BANK_USER(first_name, last_name, user_name, pass_word) VALUES(?,?,?,?)";
			String[] keyNames = {"User_Id"};
			
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
	public BankUser update(BankUser obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BankUser obj) {
		// TODO Auto-generated method stub
		
	}

}
