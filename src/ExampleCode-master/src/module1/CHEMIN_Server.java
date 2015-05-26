package module1;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverServerRunnable;
import generic.RoverServerSocket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.MyWriter;

import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CHEMIN_Server extends RoverServerRunnable {

	public CHEMIN_Server(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				
				System.out.println("CHEMIN Server: Waiting for client request");
				
				// creating socket and waiting for client connection or for CCU
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println(message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("CHEMIN SERVER - OKIE");
				
				//Reading JSON file from ccu Client and writing it to my disk
				String jsonread=inputFromAnotherObject.readObject().toString();
				MyWriter jsonwrite=new MyWriter(jsonread,Constants.ROOT_PATH+"savin");
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(jsonread);
				JSONObject ob=(JSONObject) obj;
				System.out.println(ob.containsKey("CHEMIN_TURN_ON"));
				System.out.println("I got yes from CCU and turning on the instrument now");
				
				
				//Sending a json file to power unit
				RoverServerSocket outputToPower=new RoverServerSocket(9010);
				System.out.println(outputToPower.getPort());
				ObjectOutputStream otp=(ObjectOutputStream) outputToPower.getSocket().getOutputStream();
				otp.writeObject("I need Power");
				
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				//Trying to print Json file in sequence
				
				
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("Server will pint its own object call PrintObject() Method");
				}
				else if(message.equalsIgnoreCase("commands")) {
					System.out.println("Read all the commands from Json provided by CCU and execute them sequentially ");
				}
				else if(message.equalsIgnoreCase("Read_JSon")) {
					// the Server will read another Json From another module and write it to memory
					System.out.println("Read JSon From Another Object");
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