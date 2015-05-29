package project.Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

import project.coding.Mobility;
import project.generic.RoverServerRunnable;
import project.json.Constants;
import project.json.GlobalReader;

public class ControlServer extends RoverServerRunnable {

	public ControlServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		try {
			while (true) {
				
				System.out.println("Control Server: Waiting for Mobility client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Mobility Server: Message Received from Client - "+ message.toUpperCase());
				
				// Our computation is suppose to be done here ..
				
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Control Server response Hi Client - " + message);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
			/*	else if(message.equalsIgnoreCase("MODULE_CONTROL_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.TWO);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					Double power = (Double) thatOtherObject.get("power");
			
					System.out.println("");
					System.out.println("<Start> Module MOBILITY Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.TWO + "'s object ");
					System.out.println("Power = " + power);
					
					System.out.println("===========================================");
					System.out.println("<End> Module MOBILITY Server Receiving <End>");
					System.out.println("");
				}*/
			}
			System.out.println("Server: Shutting down Socket server 3!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Control Server: Error:" + error.getMessage());
		}

	}

}
