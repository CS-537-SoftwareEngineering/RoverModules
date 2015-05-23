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
				// close resources
				inputFromAnotherObject.close();
				
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
					byte[] binaryImage = cameraController.currentMastCam.capturePhoto("still");
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						returningStatus.writeObject("MastCam Server: Succesfully captured still image");
					}
					else {
						returningStatus.writeObject("MastCam Server Error: Failed to capture still image");
					}
					returningStatus.close();
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_PANRMA.toString())){
					byte[] binaryImage = cameraController.currentMastCam.capturePhoto("panorama");
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						returningStatus.writeObject("MastCam Server: Succesfully captured panorama");
					}
					else {
						returningStatus.writeObject("MastCam Server Error: Failed to capture panorama");
					}
					returningStatus.close();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_THBMNL.toString())){
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("thumbnail");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						returningImage.writeObject(binaryImage);
					}
					else {
						returningImage.writeObject("MastCam Server Error: Failed to return thumbnail");
					}
					returningImage.close();
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_CMPRSD.toString())){
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("compressed");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						returningImage.writeObject(binaryImage);
					}
					else {
						returningImage.writeObject("MastCam Server Error: Failed to return compressed");
					}
					returningImage.close();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_ORIG.toString())){
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("original");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						returningImage.writeObject(binaryImage);
					}
					else {
						returningImage.writeObject("MastCam Server Error: Failed to return raw image");
					}
					returningImage.close();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_STAT.toString())){
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_FLTR.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_CAM.toString())){
					cameraController.switchCamera();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CLR.toString())){
					cameraController.currentMastCam.clearMemory();
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
