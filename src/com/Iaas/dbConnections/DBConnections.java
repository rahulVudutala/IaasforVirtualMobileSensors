/**
 * 
 */
package com.Iaas.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Iaas.Util.UtilConstants;

/**
 * @author Rahul
 *
 */
public class DBConnections {
	private Connection connection = null;

	public Connection createDbConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(UtilConstants.URL + UtilConstants.DB, UtilConstants.USER,
					UtilConstants.PASS);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("Connection Failed!:\n" + e.getMessage());
		}
		return connection;
	}
	
	public void closeConnection(Connection connection){
		if(connection!=null)
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
			}
	}
}
