package mastCam.controller;

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
import generic.RoverThreadHandler;
import mastCam.hardware.*;

public class MastCamServer extends RoverServerRunnable {

	Controller cameraController;
	
	public MastCamServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		cameraController = new Controller();
		
		try {

			while (true) {
				
				System.out.println("MastCam Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("MastCam Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("MastCam Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(cameraController.currentMastCam);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase(Commands.MCAM_PWR_ON.toString())){
					// Call Power
					MastCamClient mastCamAsClient = new MastCamClient(9001, null, 1);
					Thread mastCamClientThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(mastCamAsClient);
					mastCamClientThread.start();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_PWR_OFF.toString())){
					// Call Power
					MastCamClient mastCamAsClient = new MastCamClient(9001, null, 1);
					Thread mastCamClientThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(mastCamAsClient);
					mastCamClientThread.start();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_STILL.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_PANRMA.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_THBMNL.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_CMPRSD.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_ORIG.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_STAT.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_FLTR.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_CAM.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CLR.toString())){
					
				}
				
				
				
				if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server One>");
					System.out.println("This is module " + Constants.ONE + "'s object at the start");
					cameraController.currentMastCam.toString();
					System.out.println("<Server One>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("SELECT_CAM_100")) {
					cameraController.setCurrentCamera(CameraTypes.MastCam100);
				}
				else if(message.equalsIgnoreCase("SELECT_CAM_34")) {
					cameraController.setCurrentCamera(CameraTypes.MastCam34);
				}
				else if(message.equalsIgnoreCase("MODULE_TWO_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.TWO);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					Long myLong = (Long) thatOtherObject.get("myInteger");
					
					// Pass the long back into an integer
					Integer myInteger = new Integer(myLong.intValue());
					String myString = (String) thatOtherObject.get("myString");
					
					System.out.println("");
					System.out.println("<Start> Module 1 Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.TWO + "'s object ");
					System.out.println("myInteger = " + myInteger);
					System.out.println("myString = " + myString);
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
