package server;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class TempClient extends RoverClientRunnable{

	public TempClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //Send 3 commands to the Server
	        for(int i = 0; i < 7; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Control Client: Sending request to Socket Server");
	            
	            if(i == 6){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else if(i == 5){
	            	outputToAnotherObject.writeObject("MODULE_ONE_GET");
	            }
	            else if(i == 4) {
	            	outputToAnotherObject.writeObject("HAZCAM_REAR_CAMERA2__IMAGE");
	            }
	            else if(i == 3) {
	            	outputToAnotherObject.writeObject("HAZCAM_REAR_CAMERA1__IMAGE");
	            }
	            else if(i == 2) {
	            	outputToAnotherObject.writeObject("HAZCAM_FRONT_CAMERA2__IMAGE");
	            }
	            else if(i == 1) {
	            	outputToAnotherObject.writeObject("HAZCAM_FRONT_CAMERA1__IMAGE");
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("MODULE_PRINT");
	            }
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            System.out.println("Control Client: Message from Server - " + message.toUpperCase());
	            
	            // The server sends us a JSON String here
	           String jsonString = (String) inputFromAnotherObject.readObject();
	           if(i==5) 
	           System.out.println("Control Client: Message from Server - " + jsonString.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            // Integers are passed as longs
				Long myLong = (Long) jsonObject.get("myInteger");
				
				// Pass the long back into an integer
				Integer myInteger = new Integer(myLong.intValue());
				String myString = (String) jsonObject.get("myString");
			
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	        }
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			//System.out.println("Control Client: Error:" + error.getMessage());
		}
		
	}

}
