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
	public static final String accessKeyId = "AKIAJQUCASMTRNNMWPCA";
	public static final String secretAccessKey = "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";
	public static final String endPoint = "ec2.us-west-1.amazonaws.com";
	
	// Amazon instance creation constants	
	public static final String ec2ImageId = "ami-23e8a343";
	public static final String ec2InstanceType = "t2.micro";
	
	// Database credentials
	public static final String URL = "jdbc:mysql://team18-instance1.c2s2dfvr9r2j.us-west-1.rds.amazonaws.com:3306";
	public static final String USER = "team18user";
	public static final String PASS = "team18pass";
	public static final String DB = "team18dB1";
}

