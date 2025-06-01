package databasetesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.TestConfig;

public class DBManager {

	private DBManager() {
		
	}
	
	public static void setMySQLDBConnection() {
		
		try {
			Class.forName(TestConfig.mySqlDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(TestConfig.mySqlURL, TestConfig.mySqlUsername, TestConfig.mySqlPassword);
			if(!conn.isClosed()) {
				System.out.println("Successfully connected to MySQL server :-)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
