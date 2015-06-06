package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import main.Pads;

import org.json.simple.JSONObject;

import other.PadsController;
import callback.CallBack;

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
		CallBack cb = new CallBack();
		
		String objectToClean = "CLEAN_SURFACE"; 
		
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
				
				else if(message.equalsIgnoreCase("DRILL_STATUS")) {
					PadsController controller = new PadsController();
					pads = controller.action("PADS_DRILL_STATUS");
					String jsonString = gson.toJson(pads); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(3000); 
											
				}else if(message.equalsIgnoreCase("DRT_STATUS")) {
					PadsController controller = new PadsController();
					pads = controller.action("PADS_DRT_STATUS");
					String jsonString = gson.toJson(pads); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(3000); 
											
				}
				else if(message.equalsIgnoreCase("DRILL_START")) {
					
					PadsController controller = new PadsController();
					controller.action("PADS_SET_POSITION");
					Thread.sleep(5000);
					controller.action("PADS_DRILL_START");
					Thread.sleep(5000);
					pads = controller.action("PADS_DRILL_STOP");
					
					MyWriter JSONWriter = new MyWriter(pads, Constants.TWELVE); 
					String jsonString = gson.toJson(pads); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(5000); 
											
				}
				else if(message.equalsIgnoreCase("DRT_START")) {
					PadsController controller = new PadsController();
					Thread.sleep(3000);
					controller.action("PADS_DRT_SET_MODE");
					Thread.sleep(5000);
					controller.action("PADS_DRT_START");
					Thread.sleep(5000);
					pads = controller.action("PADS_DRT_STOP");
					
					String jsonString = gson.toJson(pads); 
					outputToAnotherObject.writeObject(jsonString);
					Thread.sleep(5000);
					        
				}else if(message.equalsIgnoreCase("BITS_STUCK")) {
					PadsController controller = new PadsController();
					Thread.sleep(3000);
					controller.action("PADS_DIS_ENGAGE_BITS");
					Thread.sleep(5000);
					pads = controller.action("PADS_LOAD_BITS");
					String jsonString = gson.toJson(pads); 
					outputToAnotherObject.writeObject(jsonString);
					
				}
				
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			cb.done();
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
