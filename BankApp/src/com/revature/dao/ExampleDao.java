package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.util.ConnectionFactory;

public class ExampleDao {
	
	
	public static void main(String[] args) {
		
		System.out.println(getBooksByGenre("scifi"));
	}
	
	
	public static int getBooksByGenre(String genre) {
		
		int total = 0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "{? = call num_books_by_genre(?) }";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			
			
			cs.setString(2, genre);
			cs.execute();
			total = cs.getInt(1);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return total;
	}
}
