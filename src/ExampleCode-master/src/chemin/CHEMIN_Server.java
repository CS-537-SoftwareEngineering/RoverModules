package chemin;


import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.MyWriter;

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
				String message=inputFromAnotherObject.readObject().toString();
				System.out.println(message);
				if(message.contains("CHEMIN_TURN_ON")){
				MyWriter jsonwrite=new MyWriter(message,Constants.ROOT_PATH+"readonlyjson");
				
				ObjectOutputStream ostr=new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				ostr.writeObject("CHEMIN --> Successfull file received !!");
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(message);
				JSONObject ob=(JSONObject) obj;
				if(ob.containsKey("CHEMIN_TURN_ON")){
					System.out.println("I got yes from CCU and turning on the instrument now");
				}
				}
				System.out.println(message);
				switch(message){
				case "ccu_Chemin_on":
					CHEMIN_Client client=new CHEMIN_Client(9013,null);
					Thread power=RoverThreadHandler.getRoverThreadHandler().getNewThread(client);
					power.start();
					break;
				case "Power_On":
					new CHEMIN();
					break;
				
				
				}
				// close resources
				inputFromAnotherObject.close();
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}

}