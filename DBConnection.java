package com.voidmain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection
{
	
	private DBConnection()
	{
		
	}
	public static Connection getConnection() 
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(con==null) {
				con=DriverManager.getConnection("jdbc:mysql:///subbu","root","Subbu@123");
			}
		}
		catch(SQLException | ClassNotFoundException sql)
		{
			sql.printStackTrace();
		}
		return con;
	}
}
