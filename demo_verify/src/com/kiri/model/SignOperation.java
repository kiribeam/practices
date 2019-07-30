package com.kiri.model;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.kiri.BinaryTools;
//to sign in , sign up (key,file....)
public class SignOperation {
	final String JDBC_DRIVER;
	final String DB_URL;
	
	final String USER;
	final String PASS;


	public SignOperation(){
		JDBC_DRIVER = "com.mysql.jdbc.Driver";
		DB_URL = "jdbc:mysql://localhost:3306/demo_verify?serverTimezone=GMT";
		USER = "root";
		PASS = "Mysql@123";
	}
	public SignOperation(String jdbcDriver, String dbUrl, String user, String pass){
	    JDBC_DRIVER = jdbcDriver;
		DB_URL = dbUrl;
		USER = user;
		PASS = pass;
	}	

	public boolean checkUser(String username, String password)throws Exception{
Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String pass = "";
		try{
			pass= encryptPassword(password, username);
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    stmt = conn.createStatement();
		    String sql = "SELECT username, password FROM accounts WHERE BINARY username='" + 
		    		username + "'";
		    rs = stmt.executeQuery(sql);
		    if(!rs.next()) return false;
		    if(!rs.getString("password").equals(pass)) return false;
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
	}
	
	public int signUp(String username, String password, String mail)throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String pass = "";
		try{
			pass= encryptPassword(password, username);
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    stmt = conn.createStatement();
		    String sql = "SELECT username, password, mail FROM accounts WHERE  BINARY username='" + 
		    		username + "' or BINARY mail='" + mail +"'";
		    System.out.println(sql);
		    rs = stmt.executeQuery(sql);
		    if(rs.next()) return 0;//user/mail accout exists
		    sql = "INSERT INTO accounts VALUES ('" +
		    		0 + "', '" + username + "', '" +
		    		pass + "', '" + mail + "', " +
		    		0 + ", NOW());";
		    System.out.println(sql);
		    int result = stmt.executeUpdate(sql);
		    return result;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
	}

	public static void main(String[] args)throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;	
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/demo_verify?serverTimezone=GMT";
		String USER = "root";
		String PASS = "Mysql@123";
		try{
		  Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = conn.createStatement();
	      String sql="select * from accounts";
	      rs = stmt.executeQuery(sql);
	      System.out.println(rs.next());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
		System.out.println(encryptPassword("123", "456"));
	}
	//
  public static String encryptPassword(String password, String salt) throws Exception{
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] bytes = md.digest();
		return BinaryTools.bytesToHexLine(bytes);
	}
}
