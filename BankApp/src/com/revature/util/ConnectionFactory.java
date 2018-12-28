package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Connections are the vital tool in JDBC
 * We use the ConnectionFactory to establish a connection with the database
 * 
 * This will use a lazy singleton design pattern to return teh same single connectionfactory
 * instance each tiem sone is requested so that we can monitor the amount of connections that our
 * connectionfactory generates.
 * 
 * In order to establish a connection, we need four things 
 * Driver, DB location (URL), DB username, db passoword
 */

public class ConnectionFactory {
	
	private static ConnectionFactory cf = null;
	
	private ConnectionFactory() {
		
		
	}
	
	public static synchronized ConnectionFactory getInstance() {
		if(cf==null) cf = new ConnectionFactory();
		return cf;
	}
	
	
	/*
	 * Connection - one of the core interfaces in the JDBC API
	 * -manage our connection to (session with) the database
	 * - allows
	 * - nas info about DB tables, stored procedures, and all other related db objects.
	 * 
	 */
	
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		String path = "/Users/thienle/Documents/JavaFundamentals/bookstore.jdbc/src/main/resources/database.properties";
		//String path = "src/main/resources/database.properties";
		try {
			prop.load(new FileReader(path));
			//The following line of code uses reflection and the
			//.properties file in order to instantiate our driver
			//class listed in the file
			Class.forName(prop.getProperty("driver")); 
			
			/*
			 * The DriverManager provides a basic service for managing a set of JDBC drivers.
			 * As part of its initilization, the DriverManager class will attempt
			 * to load the driver class referenced previously
			 */
			
			conn = DriverManager.getConnection(
					prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
