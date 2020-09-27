package com.itstyle.es.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {

	private static Connection connection;
	public static Connection getConnect(String url, String username, String password) {
		try {
			connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return connection;
	}

	public static void closeCon(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
