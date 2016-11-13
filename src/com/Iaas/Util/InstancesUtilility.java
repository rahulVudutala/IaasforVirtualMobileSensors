/**
 * 
 */
package com.Iaas.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Iaas.VO.SensorVO;
import com.Iaas.dbConnections.DBConnections;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

/**
 * @author Rahul
 *
 */
public class InstancesUtilility {
	
	private AmazonEC2 initializeConnection(){
		AmazonEC2 amazonEC2Client = new AmazonEC2Client(new BasicAWSCredentials(UtilConstants.accessKeyId, UtilConstants.secretAccessKey));
		amazonEC2Client.setEndpoint(UtilConstants.endPoint);
		return amazonEC2Client;
	}
	
	public void createSensorInstance(String sensorType, String location) throws ClassNotFoundException, SQLException {
		AmazonEC2 amazonEC2Client = initializeConnection();
		/* Creating KeyPairs */
		String ec2KeyPair="Ec2InstanceKey1";
		CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
		// Should have unique key name always. Should make it dynamic. Make it
		createKeyPairRequest.withKeyName(ec2KeyPair);
		amazonEC2Client.createKeyPair(createKeyPairRequest);
//		CreateKeyPairResult createKeyPairResult = amazonEC2Client.createKeyPair(createKeyPairRequest);
//		KeyPair keyPair = new KeyPair();
//		keyPair = createKeyPairResult.getKeyPair();
//		String privateKey = keyPair.getKeyMaterial();
//		System.out.println(privateKey);
		
		/* Creating Amazon EC2 instance. */
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
		runInstancesRequest.withImageId(UtilConstants.ec2ImageId).withInstanceType(UtilConstants.ec2InstanceType).withMinCount(1).withMaxCount(1)
				.withKeyName(ec2KeyPair).withSecurityGroups("default");
		RunInstancesResult runInstancesResult = amazonEC2Client.runInstances(runInstancesRequest);
		System.out.println(runInstancesRequest);
		
		DescribeInstancesRequest ir = new DescribeInstancesRequest();
		ir.withInstanceIds(runInstancesResult.getReservation().getInstances().get(0).getInstanceId());
		System.out.println(runInstancesResult.getReservation().getInstances().get(0).getInstanceId());
		SensorVO sensorVO = new SensorVO();
		sensorVO.setLocation(location);
		sensorVO.setType(sensorType);
		
		DBConnections dBConnections = new DBConnections();
		dBConnections.insertSensorData(sensorVO);
	}
	
	public void startSensorInstance(String instanceId){
		AmazonEC2 ec2 = initializeConnection();
		StartInstancesRequest startRequest = new StartInstancesRequest().withInstanceIds(instanceId);
//	    StartInstancesResult startResult = ec2.startInstances(startRequest);
		ec2.startInstances(startRequest);
	}

	public void stopSensorInstance(String instanceId){
		AmazonEC2 ec2 = initializeConnection();
		List<String> instancesToStop = new ArrayList<String>();
		instancesToStop.add(instanceId);
		StopInstancesRequest stoptr = new StopInstancesRequest();       
		stoptr.setInstanceIds(instancesToStop);
		ec2.stopInstances(stoptr);
	}
	
	public void terminateSensorInstance(String instanceId){
		AmazonEC2 ec2 = initializeConnection();
		List<String> instancesToTerminate = new ArrayList<String>();
		instancesToTerminate.add(instanceId);
		TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest();
		terminateInstancesRequest.setInstanceIds(instancesToTerminate);
//		TerminateInstancesResult terminateInstancesResult = ec2.terminateInstances(terminateInstancesRequest);
		ec2.terminateInstances(terminateInstancesRequest);
	}
	
	public static int keyNumGen(int value){
		UtilConstants.keyNum = value+1;
		return UtilConstants.keyNum;
	}
}
