/**
 * 
 */
package com.Iaas.Util;

/**
 * @author Rahul
 *
 */
public class UtilConstants {
	// Amazon account access details with IAM credentials and end point
	public static final String accessKeyId = "AKIAIF3IXNPOUDLT4F5A";
	public static final String secretAccessKey = "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";
	public static final String endPoint = "ec2.us-west-1.amazonaws.com";
	
	// Weather API URL
	public static final String weatherURLLat = "http://api.openweathermap.org/data/2.5/weather?lat=";
	public static final String weatherURLLong = "&lon=";
	public static final String weatherURLAppID = "&APPID=df3d1d39556280e9221fd2cc2f7ebdfe";
	// Amazon instance creation constants	
	public static final String ec2ImageId = "ami-23e8a343";
	public static final String ec2InstanceType = "t2.micro";
	
	// Database credentials
	public static final String URL = "jdbc:mysql://team18-instance1.c2s2dfvr9r2j.us-west-1.rds.amazonaws.com:3306/";
	public static final String USER = "team18user";
	public static final String PASS = "team18pass";
	public static final String DB = "team18dB1";
	
	//  Key pair generator constant
	public static int keyNum;
}

