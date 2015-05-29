package module1;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;

public class ModuleOneServer extends RoverServerRunnable {

	public ModuleOneServer(int port) throws IOException {
		super(port);
	}
	
	@Override
	public void run() {
		
		MyClassHere moduleOneClass = new MyClassHere(9);
		
		try {

			while (true) {
				
				System.out.println("Power Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Power Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Power Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleOneClass);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("POW_ON")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server One>");
					System.out.println("This is module " + Constants.ONE + "'s object at the start");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("MODULE_ONE_DO_SOMETHING-S1")) {
					//our methods
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server One>");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("MODULE_TWO_GET-S1")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.TWO);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					//Long myLong = (Long) thatOtherObject.get("myPower");
					Integer myPower = (Integer) thatOtherObject.get("myPower");
					
					
					//String myString = (String) thatOtherObject.get("myString");
					
					System.out.println("");
					System.out.println("<Start> Module 1 Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.TWO + "'s object ");
					System.out.println("myPower = " + myPower);
					//System.out.println("myTime = " + myTime);
					System.out.println("===========================================");
					System.out.println("<End> Module 1 Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket server 1!!");
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