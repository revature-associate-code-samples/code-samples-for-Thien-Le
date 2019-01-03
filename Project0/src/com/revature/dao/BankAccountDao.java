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
import com.revature.util.ConnectionFactory;

/**
 * Handling CRUD with database.
 * @author thienle
 *
 */
public class BankAccountDao implements DAO<BankAccount, Integer> {
		
	
	/**
	 * Return a list of BankAccount
	 */
	@Override
	public List<BankAccount> findAll() {
		
		List<BankAccount> account = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String query ="select * from bank_account order by balance";
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement =  conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB query
			ResultSet rs =  statement.executeQuery(query);
			
			//return false when there are no more rows in result set
			
			while(rs.next()) {
				BankAccount temp = new BankAccount();
				temp.setAccountId(rs.getInt(1));
				temp.setTypeId(rs.getInt(2));
				temp.setUserId(rs.getInt(3));
				temp.setBalance(rs.getDouble(4));

				account.add(temp);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	/**
	 * Return a list of bank account of current user.
	 * @param id
	 * @return
	 */
	public List<BankAccount> findAllAccount(Integer id) {
		
		List<BankAccount> account = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql ="select * from bank_account where user_id = ?";
						
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount temp = new BankAccount();
				temp.setAccountId(rs.getInt(1));
				temp.setTypeId(rs.getInt(2));
				temp.setUserId(rs.getInt(3));
				temp.setBalance(rs.getDouble(4));
				
				account.add(temp);

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	/**
	 * Return a BankAccount by id
	 */
	@Override
	public BankAccount findById(Integer id) {
		
		BankAccount a = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from bank_account where account_id = ?";  //change user_id to account_id *******************
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				a = new BankAccount();
				a.setAccountId(rs.getInt(1));
				a.setTypeId(rs.getInt(2));
				a.setUserId(rs.getInt(3));
				a.setBalance(rs.getDouble(4));

			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}

	/**
	 * Saving a BankAccount
	 */
	@Override
	public BankAccount save(BankAccount obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO BANK_ACCOUNT(type_id, user_id, balance) VALUES(?,?,?)";
			String[] keyNames = {"Account_Id"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setInt(1, obj.getTypeId());
			ps.setInt(2, obj.getUserId());
			ps.setDouble(3, obj.getBalance());
			
			int numRows = ps.executeUpdate();
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setAccountId(pk.getInt(1));
				}
				conn.commit();
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Updating a BankAccount
	 */
	@Override
	public BankAccount update(BankAccount obj) {

		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "update bank_account set balance = ? where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, obj.getBalance());
			ps.setInt(2, obj.getAccountId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Delete a BankAccount
	 */
	@Override
	public void delete(BankAccount obj) {
		
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "delete from bank_account where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, obj.getAccountId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
