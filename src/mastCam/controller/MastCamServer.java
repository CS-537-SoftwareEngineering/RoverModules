package mastCam.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.MyWriter;
import generic.RoverServerRunnable;
import generic.RoverThreadHandler;
import mastCam.message.*;

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
				
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase(Commands.MCAM_PWR_ON.toString())){
					// Call Power: request power
					System.out.println("MastCam powered on");
					
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					Message returnMessage = new Message("No Command");
					returnMessage.setTurnedOn(true);
					String jsonString = returnMessage.toJsonString(null);
					returningStatus.writeObject(jsonString);
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
					
					MastCamClient mastCamAsClient = new MastCamClient((int)Ports.POWER_ENERGY_PORT, null, 1);
					Thread mastCamClientThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(mastCamAsClient);
//					mastCamClientThread.start();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_PWR_OFF.toString())){
					// Call Power: turn off
					System.out.println("MastCam powered off");
					
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					Message returnMessage = new Message("No Command");
					returnMessage.setTurnedOn(false);
					String jsonString = returnMessage.toJsonString(null);
					returningStatus.writeObject(jsonString);
					
					cameraController.currentMastCam.setOn(false);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
					
					MastCamClient mastCamAsClient = new MastCamClient((int)Ports.POWER_ENERGY_PORT, null, 2);
					Thread mastCamClientThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(mastCamAsClient);
//					mastCamClientThread.start();
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_STILL.toString())){
					System.out.println("MastCam captured still image");
					
					byte[] binaryImage = cameraController.currentMastCam.capturePhoto("still");
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						Message returnMessage = new Message("No Command");
						returnMessage.setTurnedOn(true);
//						returnMessage.setData(binaryImage);
						String jsonString = returnMessage.toJsonString(binaryImage);
						returningStatus.writeObject(jsonString);
					}
					else {
//						returningStatus.writeObject("MastCam Server Error: Failed to capture still image");
					}
					returningStatus.close();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_PANRMA.toString())){
					System.out.println("MastCam captured panorama");
					
					byte[] binaryImage = cameraController.currentMastCam.capturePhoto("panorama");
					ObjectOutputStream returningStatus = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						Message returnMessage = new Message("No Command");
						returnMessage.setTurnedOn(true);
//						returnMessage.setData(binaryImage);
						String jsonString = returnMessage.toJsonString(binaryImage);
						returningStatus.writeObject(jsonString);
					}
					else {
//						returningStatus.writeObject("MastCam Server Error: Failed to capture panorama");
					}
					returningStatus.close();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CAPTR_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_THBMNL.toString())){
					System.out.println("MastCam returned thumbnail");
					
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("thumbnail");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						Message returnMessage = new Message("No Command");
						returnMessage.setTurnedOn(true);
//						returnMessage.setData(binaryImage);
						String jsonString = returnMessage.toJsonString(binaryImage);
//						returnMessage.displayImage(binaryImage);
						returningImage.writeObject(jsonString);
					}
					else {
//						returningImage.writeObject("MastCam Server Error: Failed to return thumbnail");
					}
					returningImage.close();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_CMPRSD.toString())){
					System.out.println("MastCam returned compressed image");
					
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("compressed");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						Message returnMessage = new Message("No Command");
						returnMessage.setTurnedOn(true);
//						returnMessage.setData(binaryImage);
						String jsonString = returnMessage.toJsonString(binaryImage);
						returningImage.writeObject(jsonString);
					}
					else {
//						returningImage.writeObject("MastCam Server Error: Failed to return compressed");
					}
					returningImage.close();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_ORIG.toString())){
					System.out.println("MastCam returned raw image");
					
					byte[] binaryImage = cameraController.currentMastCam.returnPhoto("original");
					// create ObjectOutputStream object
					ObjectOutputStream returningImage = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
					if(binaryImage != null){
						Message returnMessage = new Message("No Command");
						returnMessage.setTurnedOn(true);
//						returnMessage.setData(binaryImage);
						String jsonString = returnMessage.toJsonString(binaryImage);
						returningImage.writeObject(jsonString);
					}
					else {
//						returningImage.writeObject("MastCam Server Error: Failed to return raw image");
					}
					returningImage.close();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_VID.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_RTN_STAT.toString())){
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_FLTR.toString())){
					
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_SLCT_CAM.toString())){
					System.out.println("MastCam camera switched");
					cameraController.switchCamera();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
				}
				else if(message.equalsIgnoreCase(Commands.MCAM_CLR.toString())){
					cameraController.currentMastCam.clearMemory();
					
					cameraController.currentMastCam.setOn(true);
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(cameraController, 6);
				}
				
				// close resources
				inputFromAnotherObject.close();
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
