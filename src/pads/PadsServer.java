package pads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class PadsServer extends RoverServerRunnable{

	public PadsServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Pads pads = new Pads();
		
		String objectToClean = "CLEAN_SURFACE"; 
		Drt drt = new Drt(objectToClean); 
		
		try {
			while (true) {
				System.out.println("");
				System.out.println("PADS Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("");
				System.out.println("PADS Server: Message Received from Client - "+ message.toUpperCase());
				
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				
				
				
				if (message.equalsIgnoreCase("exit")){
					break;
					}
				
				//One of event which are going to performed by our instrument
				//In order to perform this event we are going to execute couple of 
				//commands in order to complete one task
				
				//All commands are written in PadsController.java class
				
				else if(message.equalsIgnoreCase("PADS_DRILL_START")) {
					
//					@SuppressWarnings("unused")
//					MyWriter JSONWriter = new MyWriter(pads, 1);
					String jsonString = gson.toJson(pads);
					outputToAnotherObject.writeObject(jsonString);
					PadsController controller = new PadsController();
					controller.action("PADS_SET_POSITION");
					Thread.sleep(5000);
					controller.action("PADS_DRILL_START");
					Thread.sleep(5000);
					controller.action("PADS_DRILL_STOP");
					Thread.sleep(5000);
											
				}
				else if(message.equalsIgnoreCase("PADS_DRT_START")) {
					String jsonString = gson.toJson(drt);
					outputToAnotherObject.writeObject(jsonString);
					PadsController controller = new PadsController();
					Thread.sleep(3000);
					controller.action("PADS_DRT_SET_MODE");
					Thread.sleep(5000);
					controller.action("PADS_DRT_START");
					Thread.sleep(5000);
					controller.action("PADS_DRT_STOP");
					Thread.sleep(5000);
					
				}else if(message.equalsIgnoreCase("PADS_REPLACE_BITS")) {
					String jsonString = gson.toJson(pads);
					outputToAnotherObject.writeObject(jsonString);
					PadsController controller = new PadsController();
					Thread.sleep(3000);
					controller.action("PADS_REPLACE_BITS");
					Thread.sleep(5000);
					
				}
				
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			
			System.out.println("Server: Shutting down Socket server 1!!");
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		} 
	}

}
