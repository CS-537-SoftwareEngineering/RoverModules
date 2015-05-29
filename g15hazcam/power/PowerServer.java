package power;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;

public class PowerServer extends RoverServerRunnable {

	public PowerServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		PowerClass power_module_class = new PowerClass(1);

		try {

			while (true) {

				System.out.println("POWER Server: Waiting for client request");

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(
						getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out
						.println("POWER Server: Message Received from Client - "
								+ message.toUpperCase());

				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());

				// write object to Socket
				outputToAnotherObject
						.writeObject("POWER Server response Hi Client - "
								+ message);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(power_module_class);

				outputToAnotherObject.writeObject(jsonString);

				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if (message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server POWER>");
					System.out.println("This is module " + Constants.FIFTEEN
							+ "'s object at the start");
					power_module_class.printObject();
					System.out.println("<Server POWER>");
					System.out.println("");
				} 
			}
			System.out.println("Server: Shutting down Socket POWER!!");
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
