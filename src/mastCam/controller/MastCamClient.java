package mastCam.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

import mastCam.message.*;

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
		    Thread.sleep(5000);
		    
		  //write to socket using ObjectOutputStream
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
            
            System.out.println("MastCam Client: Sending request to Socket Server");
            
            if(outgoingCommandNumber == 1){
            	// request power
//            	Message message = new Message("POWER_TURN_ON");
//            	message.setTurnedOn(true);
//            	String jsonString = message.toJsonString(null);
//            	outputToAnotherObject.writeObject(jsonString);
            	outputToAnotherObject.writeObject("POW_CALC 13");
            }
            else if(outgoingCommandNumber == 2){
            	// turning off
//            	Message message = new Message("POWER_TURN_OFF");
//            	String jsonString = message.toJsonString(null);
//            	message.setTurnedOn(false);
//            	outputToAnotherObject.writeObject(jsonString);
            	outputToAnotherObject.writeObject("POW_OFF");
            }

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
