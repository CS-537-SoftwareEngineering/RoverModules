package module2;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

import org.json.simple.JSONObject;

import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class ModuleTwoServer extends RoverServerRunnable {

	public ModuleTwoServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		MyClassHereTwo moduleTwoClass = new MyClassHereTwo(2);
		
		
		try {
			
			while (true) {
				
				System.out.println("Module 2 Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Module 2 Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Module 2 Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleTwoClass);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("MBLTY_ON")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("This is module Mobility ");
					
					
					System.out.println("Message recieved from " + message);
					System.out.println("<Server Two>");
					System.out.println("");
										
					moduleTwoClass.setMyInteger(18);
					moduleTwoClass.setMyString("MOBILITY MODULE");
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleTwoClass, 18);
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("JSON created");
					//moduleTwoClass.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
					
					CallBack call_back = new CallBack();
					call_back.done();
					
				}
	
				else if(message.equalsIgnoreCase("MCAM_ON")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("This is MAST CAM module ");
					
					
					System.out.println("Message recieved from " + message);
					System.out.println("<Server Two>");
					System.out.println("");
					
					moduleTwoClass.setMyInteger(6);
					moduleTwoClass.setMyString("MCAM MODULE");
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleTwoClass, 6);
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("JSON created");
					//moduleTwoClass.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
					
					CallBack call_back = new CallBack();
					call_back.done();
				}
				else if(message.equalsIgnoreCase("ARM_ON")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("This is ARM MODULE ");
					
					System.out.println("Message recieved from " + message);
					System.out.println("<Server Two>");
					System.out.println("");
										
					moduleTwoClass.setMyInteger(16);
					moduleTwoClass.setMyString("ARM MODULE");
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleTwoClass, 16);
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("JSON created");
					//moduleTwoClass.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
					
					CallBack call_back = new CallBack();
					call_back.done();
					
				}
			}
			System.out.println("Server: Shutting down Socket server 2!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}

}
