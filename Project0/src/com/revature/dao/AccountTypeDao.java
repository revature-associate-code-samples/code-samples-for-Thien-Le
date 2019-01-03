package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.AccountType;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

/**
 * Handling CRUD with database.
 * @author thienle
 *
 */
public class AccountTypeDao implements DAO<AccountType, Integer> {

	
	/**
	 * Return a list of account type.
	 */
	@Override
	public List<AccountType> findAll() {
		
		List<AccountType> type = new ArrayList<AccountType>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String query ="select * from account_type";
			
			//STATEMENT INTERFACE - implementation exposed via connection
			Statement statement =  conn.createStatement();
			
			//ResultSet Interface - represents the result set of a DB query
			ResultSet rs =  statement.executeQuery(query);
			
			//return false when there are no more rows in result set
			
			while(rs.next()) {
				AccountType temp = new AccountType();
				temp.setTypeId(rs.getInt(1));
				temp.setAccountType(rs.getString(2));
				type.add(temp);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.DAO#findById(java.io.Serializable)
	 * Use Callablestatement to call database store procedure
	 */
	public AccountType findById(Integer id) {
		
		AccountType accType = new AccountType();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{ call get_account_type(?, ?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.executeUpdate();
			
			ResultSet rs = (ResultSet) cs.getObject(2);
	
			while(rs.next()) {
			
				
				
				accType.setTypeId(rs.getInt(1));
				accType.setAccountType(rs.getString(2));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accType;
	}

	@Override
	public AccountType save(AccountType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountType update(AccountType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AccountType obj) {
		// TODO Auto-generated method stub
		
	}

}
