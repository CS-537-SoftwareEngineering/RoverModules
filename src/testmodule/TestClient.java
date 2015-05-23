package testmodule;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class TestClient extends RoverClientRunnable{

	public TestClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{

			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
	        for(int i = 0; i < 3; i++){
	        	System.out.println("TEST Client: Sending request to Socket Server");
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            if(i == 2){
//	            	outputToAnotherObject.writeObject("exit");
	            	outputToAnotherObject.writeObject("PADS_REPLACE_BITS");
	            	Thread.sleep(5000);
	            }
	            else if(i == 1){
	            	outputToAnotherObject.writeObject("PADS_DRT_START");
	            	Thread.sleep(25000);
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("PADS_DRILL_START");
	            	Thread.sleep(25000);
	            }
	             
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	          
	            inputFromAnotherObject.close(); 
	            outputToAnotherObject.close();
	            
	        }
	        closeAll();
			
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("TEST client: Error:" + error.getMessage());
		}
	}

}
