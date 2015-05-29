package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	//@Override
	public void run() {
		try
		{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //Send 2 commands to the Server
		 //  for(int i = 0; i < 2; i++)
	     //  {
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Client: Sending request to Socket Server");
	            
	            if(	DanClass.DAN_ON == true )
	            {
	            	outputToAnotherObject.writeObject("HYDROGEN CONTENT??");
	            	
	            	 //read the server response message
		            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            
		            
		            String message = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message from Server - " + message.toUpperCase()); //Hello Client
	    	        
		           String message1 = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message1 from Server - " + message1.toUpperCase()); //calculating speed of neutrons
					
		            String message2 = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message2 from Server - " + message2.toUpperCase()); //% of Hydrogen
					
		            //String message3 = (String) inputFromAnotherObject.readObject();
		            //System.out.println("Client: Message from Server - " + message3.toUpperCase()); //% of Hydrogen not found
	            	
	            }
	          
	            
	            //close resources
	           	inputFromAnotherObject.close();
	           	outputToAnotherObject.close();
	           	Thread.sleep(5000);
	 
	        closeAll();
		}
        
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception error) 
		{
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}

