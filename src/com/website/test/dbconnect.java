package com.website.test;

import java.sql.*;

public class dbconnect {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	dbconnect(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gallery","root","");
			st = con.createStatement();
			
		}catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public void getData() {
		try {
			
			String query = "SELECT * FROM `visitor` WHERE Name='asas'";
			rs = st.executeQuery(query);
			while (rs.next())
			{
				String name = rs.getString("aname");
				String pass = rs.getString("pwd");
				System.out.println("Name: " + name + "  " + "Password: " + pass );
			}
			
		}catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
}
