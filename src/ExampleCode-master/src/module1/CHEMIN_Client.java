package module1;


import generic.RoverClientRunnable;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;
import json.GlobalReader;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CHEMIN_Client extends RoverClientRunnable{

	public CHEMIN_Client(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
		 

		    System.out.println("Chemin Client:: Sending my Requirements to CCM");
		   
		    //WRITING A JSON FOR CCU
		 // create ObjectOutputStream object
			
		    ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getSocket().getOutputStream());
			
		    // write object to Socket
			outputToAnotherObject.writeObject(" \t I need power : CCSU- " );
			
			// Read Json from Directory and send it to output Stream
			System.out.println("Read  local JSON and send it to another module");
			
			
	            
	                    
	            //close resources
	            
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	            closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client CHEMIN: Error:" + error.getMessage());
		}
		
	}

}
