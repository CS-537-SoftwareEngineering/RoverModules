package project.Attitude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import project.coding.Mobility;
import project.coding.Mobility.Point;
import project.generic.RoverServerRunnable;

public class AttitudeServer extends RoverServerRunnable {

	public AttitudeServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		try {
			while (true) {
				
				System.out.println("ATTITUDE Server: Waiting for client request");
				
				getRoverServerSocket().openSocket();
				
				ObjectInputStream inputFromAnotherObject1 = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				String message1 = (String) inputFromAnotherObject1.readObject();
				System.out.println("ATTITUDE Server: Message Received from MOBILITY- "+ message1.toUpperCase());
			
				System.out.println("Here you are in attitude Server");
				ObjectOutputStream outputToAnotherObject1 = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				inputFromAnotherObject1.close();
				outputToAnotherObject1.close();
				
				if (message1.equalsIgnoreCase("exit"))
					break;
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			closeAll();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}

	}

}
