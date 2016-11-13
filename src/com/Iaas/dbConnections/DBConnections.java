/**
 * 
 */
package com.Iaas.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Iaas.Util.UtilConstants;
import com.Iaas.VO.SensorVO;
import com.Iaas.VO.WeatherDataVO;

/**
 * @author Rahul
 *
 */
public class DBConnections {
	
	public static Connection createDbConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(UtilConstants.URL + UtilConstants.DB, UtilConstants.USER,
					UtilConstants.PASS);
			System.out.println("Success");
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
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
			}
	}
	
	public void insertWeatherData(WeatherDataVO weatherData) throws ClassNotFoundException, SQLException{
		Connection dBConnection = createDbConnection();
		String insertData = "insert into sensor_data "+"(location_id, pressure, temp_min, temp_max, humidity, wind_speed, wind_degree, last_update_time)"
				+ " values"+ "(1,?,?,?,?,?,?,?)";
		PreparedStatement ps = dBConnection.prepareStatement(insertData);
		ps.setString(1, weatherData.getPressure());
		ps.setString(2, weatherData.getMin_temp());
		ps.setString(3, weatherData.getMax_temp());
		ps.setString(4, weatherData.getHumidity());
		ps.setString(5, weatherData.getWindSpeed());
		ps.setString(6, weatherData.getWindDirection());
		ps.setString(7, weatherData.getTimeStamp());
		ps.executeUpdate();
	}
	
	public void insertSensorData(SensorVO sensor) throws ClassNotFoundException, SQLException{
		Connection dBConnection = createDbConnection();
		String insertData = "insert into sensor"+"(type,city)"
				+ " values"+ "(?,?)";
		PreparedStatement ps = dBConnection.prepareStatement(insertData);
		ps.setString(1, sensor.getType());
		ps.setString(2, sensor.getLocation());
		ps.executeUpdate();
	}
}
