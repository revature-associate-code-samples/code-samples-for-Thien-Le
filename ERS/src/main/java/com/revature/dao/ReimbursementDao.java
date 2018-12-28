package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionFactory;

public class ReimbursementDao implements DAO<Reimbursement, Integer> {

	@Override
	public List<Reimbursement> findAll() {
		
		List<Reimbursement> reimb = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String query = "select * from ers_reimbursement";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setReimbId(rs.getInt(1));
				temp.setReimbAmount(rs.getDouble(2));
				temp.setReimbSubmitted(rs.getString(3));
				temp.setReimbResolved(rs.getString(4));
				temp.setReimbDescription(rs.getString(5));
				temp.setReimbAuthor(rs.getInt(6));
				temp.setReimbResolver(rs.getInt(7));
				temp.setReimbStatusId(rs.getInt(8));
				temp.setReimbTypeId(rs.getInt(9));
				reimb.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}

	@Override
	public Reimbursement findById(Integer id) {
		
		
		Reimbursement reimb = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt(1));
				reimb.setReimbAmount(rs.getDouble(2));
				reimb.setReimbSubmitted(rs.getString(3));
				reimb.setReimbResolved(rs.getString(4));
				reimb.setReimbDescription(rs.getString(5));
				reimb.setReimbAuthor(rs.getInt(6));
				reimb.setReimbResolver(rs.getInt(7));
				reimb.setReimbStatusId(rs.getInt(8));
				reimb.setReimbTypeId(rs.getInt(9));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}

	
	public List<Reimbursement> findByUserId(Integer id) {
		
		List<Reimbursement> reimb = new ArrayList<>();
		
		
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
			
				temp.setReimbId(rs.getInt(1));
				temp.setReimbAmount(rs.getDouble(2));
				temp.setReimbSubmitted(rs.getString(3));
				temp.setReimbResolved(rs.getString(4));
				temp.setReimbDescription(rs.getString(5));
				temp.setReimbAuthor(rs.getInt(6));
				temp.setReimbResolver(rs.getInt(7));
				temp.setReimbStatusId(rs.getInt(8));
				temp.setReimbTypeId(rs.getInt(9));
				reimb.add(temp);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}
	
	
	public List<Reimbursement> findByStatusId(Integer id) {
		
		List<Reimbursement> reimb = new ArrayList<>();
		
		
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from ers_reimbursement where reimb_status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
			
				temp.setReimbId(rs.getInt(1));
				temp.setReimbAmount(rs.getDouble(2));
				temp.setReimbSubmitted(rs.getString(3));
				temp.setReimbResolved(rs.getString(4));
				temp.setReimbDescription(rs.getString(5));
				temp.setReimbAuthor(rs.getInt(6));
				temp.setReimbResolver(rs.getInt(7));
				temp.setReimbStatusId(rs.getInt(8));
				temp.setReimbTypeId(rs.getInt(9));
				reimb.add(temp);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}
	
	
	@Override
	public Reimbursement save(Reimbursement obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) values (?, ?, ?, ?, ?,?)";
			String[] keyNames = {"Reimb_Id"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setDouble(1,  obj.getReimbAmount());
			ps.setString(2, obj.getReimbSubmitted());
			ps.setString(3, obj.getReimbDescription());
			ps.setInt(4, obj.getReimbAuthor());
			ps.setInt(5, obj.getReimbStatusId());
			ps.setInt(6, obj.getReimbTypeId());
			
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setReimbId(1);
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Reimbursement update(Reimbursement obj) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "update ers_reimbursement set reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? where reimb_id = ?";
			//String sql = "update bank_account set balance = ? where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, obj.getReimbResolved());
			ps.setInt(2, obj.getReimbResolver());
			ps.setInt(3, obj.getReimbStatusId());
			ps.setInt(4, obj.getReimbId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Reimbursement obj) {
		// TODO Auto-generated method stub
		
	}

}
