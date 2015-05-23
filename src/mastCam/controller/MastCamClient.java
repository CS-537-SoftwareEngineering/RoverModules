package mastCam.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class MastCamClient extends RoverClientRunnable{
	
	int outgoingCommandNumber;
	public MastCamClient(int port, InetAddress host, int commandNumber)
			throws UnknownHostException {
		super(port, host);
		outgoingCommandNumber = commandNumber; 
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //write to socket using ObjectOutputStream
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
            
            System.out.println("MastCam Client: Sending request to Socket Server");
            
            if(outgoingCommandNumber == 3){
            	// request power
            	outputToAnotherObject.writeObject("exit");
            }
            else if(outgoingCommandNumber == 2){
            	// turning off
            	outputToAnotherObject.writeObject("MODULE_ONE_GET");
            }
            
            //read the server response message
            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            String message = (String) inputFromAnotherObject.readObject();
            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
            
            // The server sends us a JSON String here
            String jsonString = (String) inputFromAnotherObject.readObject();
            System.out.println("Module 1 Client: Message from Server - " + jsonString.toUpperCase());
            
            // We can then parse the JSON String into a JSON Object
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            
            // Integers are passed as longs
			Long myLong = (Long) jsonObject.get("myInteger");
			
			// Pass the long back into an integer
			Integer myInteger = new Integer(myLong.intValue());
			String myString = (String) jsonObject.get("myString");
			
			System.out.println("");
			System.out.println("<Start> Client 1 now has: <Start>");
			System.out.println("===========================================");
			System.out.println("This is Class " + Constants.TWO + "'s object ");
			System.out.println("myInteger = " + myInteger);
			System.out.println("myString = " + myString);
			System.out.println("===========================================");
			System.out.println("<End> Client 1 now has: <End>");
			System.out.println("");
            
            //close resources
            inputFromAnotherObject.close();
            outputToAnotherObject.close();
            Thread.sleep(5000);
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}
