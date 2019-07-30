package com.kiri.model;
import java.sql.*;

public class DataBaseSlayer {
	
	final String JDBC_DRIVER;
	final String DB_URL;
	
	final String USER;
	final String PASS;


	public DataBaseSlayer(){
		JDBC_DRIVER = "com.mysql.jdbc.Driver";
		DB_URL = "jdbc:mysql://localhost:3306/demo_verify";
		USER = "root";
		PASS = "Mysql@123";
	}
	
	public DataBaseSlayer(String jdbcDriver, String dbUrl, String user, String pass){
	    JDBC_DRIVER = jdbcDriver;
		DB_URL = dbUrl;
		USER = user;
		PASS = pass;
	}

	public int updater(String sql){ 
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return 1;
	} 
	public ResultSet selector(String sql) throws SQLException{
		Statement stmt = null;
		return stmt.executeQuery(sql);
	}

}
