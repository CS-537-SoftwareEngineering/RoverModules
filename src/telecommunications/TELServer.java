package telecommunications;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class TELServer extends RoverServerRunnable {

	public TELServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		MyClassHereTwo moduleTwoClass = new MyClassHereTwo(2);
		
		try {
			
			while (true) {
				
				System.out.println("Telecommunications Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Telecommunications : Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Telecommunications Server response Hi Client - " + message);
				
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
				else if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Telecommunications>");
					System.out.println("This is module " + Constants.TWO + "'s object at the start");
					moduleTwoClass.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("TEL_POWER_ON")) {
					System.out.println("Antenna power is turned ON");
				}
				else if(message.equalsIgnoreCase("TEL_POWER_OFF")) {
				    System.out.println("Antenna power is turned OFF");
				}
				else if(message.equalsIgnoreCase("TEL_POWER_OFF")) {
                    
                }
				else if(message.equalsIgnoreCase("TEL_RELAY_TO_EARTH")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject ccdObject = JSONReader.getJSONObject();

					String data = (String) ccdObject.get("data");
					
					System.out.println("");
					System.out.println("<Start> Telecommunications Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.ONE + "'s object ");
					System.out.println("data = " + data);
					Buffer b = new Buffer();
					b.add(data);
					System.out.println("Sending the data to the Earth...");
					System.out.println("===========================================");
					System.out.println("<End> Telecommunications server ends communication <End>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("TEL_RELAY_TO_ROVER")) {
                    // The server reads another a JSON Object in memory
                    GlobalReader JSONReader = new GlobalReader(10000);
                    JSONObject ccdObject = JSONReader.getJSONObject();

                    String data = (String) ccdObject.get("order");
                    
                    System.out.println("");
                    System.out.println("<Start> Telecommunications Server Receiving <Start>");
                    System.out.println("===========================================");
                    System.out.println("This is data received from Earth ");
                    System.out.println("data = " + data);
                    System.out.println("===========================================");
                    System.out.println("<End> Telecommunications Server Receiving <End>");
                    System.out.println("");
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
