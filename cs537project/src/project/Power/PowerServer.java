package project.Power;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import project.generic.RoverServerRunnable;
import project.json.Constants;
import project.json.MyWriter;
import project.Power.MyClassHere;

public class PowerServer extends RoverServerRunnable {

	public PowerServer(int port) throws IOException {
		super(port);
	}
	MyClassHere powerclass = new MyClassHere();
	@Override
	public void run() {

		try {
			while (true) {
				
				//System.out.println("Mobility Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				/*String[] parts = message.split("-");
				String part1 = parts[0];
				String part2 = parts[1];
				*/
				System.out.println("POWER Server: Message Received from MOBILITY CLIENT - "+ message.toUpperCase());
				
			
			

		 		ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
		 		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(powerclass);
				
				outputToAnotherObject.writeObject(jsonString);
				*/inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				if (message.equalsIgnoreCase("exit"))
					break;
				
				
				
				else if(message.equalsIgnoreCase("POWER_CAL_2.07")) {
					
					powerclass.setPower(2.07);
					powerclass.getPower();
					
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(powerclass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server Three>");
					System.out.println("<Server Three>");
					System.out.println("");
				}else if(message.equalsIgnoreCase("PRINT_POWER")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server Three>");
					System.out.println("This is module " + Constants.ONE + "'s object at the start");
					powerclass.print();
					System.out.println("<Server Three>");
					System.out.println("");
				}
				
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("powerServer: Error:" + error.getMessage());
		}

	}

}
