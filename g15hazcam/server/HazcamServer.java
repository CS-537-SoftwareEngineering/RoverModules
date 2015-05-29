package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread.State;
import java.util.Random;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;
import generic.RoverThreadHandler;
import callback.*;
import main.HazcamClass;
public class HazcamServer extends RoverServerRunnable {

	public HazcamServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		HazcamClass Hazcam_Class = new HazcamClass(1);
		int port_power=Constants.PORT_POWER;
		boolean power =false;
		
		
		
		boolean FRONT_CAM1_STATUS =true;
		boolean FRONT_CAM2_STATUS =true;
		boolean REAR_CAM1_STATUS =true;
		boolean REAR_CAM2_STATUS =true;
		
		
		Random randomGenerator = new Random();
		int status=randomGenerator.nextInt(4);
		if(status==0)
			 FRONT_CAM1_STATUS =false;
		if(status==1)
		 FRONT_CAM2_STATUS =false;
		if(status==2)
		 REAR_CAM1_STATUS =false;
		if(status==3)
		 REAR_CAM2_STATUS =false;	
		
		try {

			while (true) {

				System.out.println("Hazcam Server: Waiting for client request");

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(
						getRoverServerSocket().getSocket().getInputStream());
					
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out
						.println("Hazcam Server: Message Received from Client - "
								+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject
						.writeObject("Hazcam Server response Hi Client - "
								+ message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(Hazcam_Class);
				
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
					System.out.println("<Server HazCam>");
					System.out.println("This is module " + Constants.FIFTEEN
							+ "'s object at the start");
					//Hazcam_Class.printObject();
					System.out.println("<Server HazCam>");
					System.out.println("");
				} else{
					
					
					
					if(!power)
					{
					// Hazcam client sending messages to Power server one
					HazcamClient Hcamclient = new HazcamClient(port_power, null); // notice port_one
					Thread client_HCAM = RoverThreadHandler.getRoverThreadHandler().getNewThread(Hcamclient);
					
					// start the thread which communicates through sockets
					client_HCAM.start();
					
					while(client_HCAM.getState() != State.TERMINATED)
					{
						Thread.sleep(2000);
					}
					power=true;
					}	
						
					
					
					
					if (message.equalsIgnoreCase("HAZCAM_FRONT_CAMERA1__IMAGE")) {
								@SuppressWarnings("unused")
								MyWriter JSONWriter = new MyWriter(Hazcam_Class,
										Constants.FIFTEEN);
								System.out.println("");
								System.out.println("<HazCam Server>");
								Thread.sleep(1000);
								if(FRONT_CAM1_STATUS)
								{
								// HazcamClass.printObject();
								System.out.println("HCAM_FRONT_CAM1_ON (to turn on the front camera1)");
								Thread.sleep(3000);
								System.out.println("HCAM_FRONT_CAM1_CAPT (To take a picture from front cam1)");
								Thread.sleep(1000);
								System.out.println("HCAM_FRONT_CAM1_OFF (to turn off the front camera1)");
								Thread.sleep(1000);
								}
								else
								{
									System.out.println("HAZCAM Front Camera 1 is not Working Condition . So we use Front Backup Camera1");
								Thread.sleep(1000);
									System.out.println("HCAM_FRONT_BACKUP_CAM1_ON (to turn on front backup camera1)");
									Thread.sleep(3000);
									System.out.println("HCAM_FRONT_BACKUP_CAM1_CAPT (To take a picture from front backup cam1)");
									Thread.sleep(1000);
									System.out.println("HCAM_FRONT_BACKUP_CAM1_OFF (to turn off front backup camera1)");
									Thread.sleep(1000);	
									
								}
								System.out.println("<HazCam Server>");
								System.out.println("");
							}
					
					
					
					
					
					
					else if (message.equalsIgnoreCase("HAZCAM_FRONT_CAMERA2__IMAGE")) {
																@SuppressWarnings("unused")
								MyWriter JSONWriter = new MyWriter(Hazcam_Class,
										Constants.FIFTEEN);
								System.out.println("");
								System.out.println("<HazCam Server>");
								Thread.sleep(1000);
						
								if(FRONT_CAM2_STATUS)
								{		
								System.out
										.println("HCAM_FRONT_CAM2_ON (to turn on the front camera2)");
								Thread.sleep(3000);
								System.out
										.println("HCAM_FRONT_CAM2_CAPT (To take a picture from front cam2)");
								Thread.sleep(1000);
								System.out
										.println("HCAM_FRONT_CAM2_OFF (to turn off the front camera2)");
								Thread.sleep(1000);
								}
								else
								{
						System.out.println("HAZCAM Front Camera 2 is not Working Condition . So we use Front Backup Camera2");
						Thread.sleep(1000);
						System.out.println("HCAM_FRONT_BACKUP_CAM2_ON (to turn on front backup camera2)");
						Thread.sleep(3000);
						System.out.println("HCAM_FRONT_BACKUP_CAM2_CAPT (To take a picture from front backup cam2)");
						Thread.sleep(1000);
						System.out.println("HCAM_FRONT_BACKUP_CAM2_OFF (to turn off front backup camera2)");
						Thread.sleep(1000);	
						
					}
								
								System.out.println("<HazCam Server>");
								System.out.println("");
							} 
					
					
					else if (message.equalsIgnoreCase("HAZCAM_REAR_CAMERA1__IMAGE")) {
								

							
								@SuppressWarnings("unused")
								MyWriter JSONWriter = new MyWriter(Hazcam_Class,
										Constants.FIFTEEN);

								System.out.println("");
								System.out.println("<HazCam Server>");
								Thread.sleep(1000);
								if(REAR_CAM1_STATUS)
								{
								System.out.println("HCAM_REAR_CAM1_ON (to turn on the REAR camera1)");
								Thread.sleep(1000);
								System.out.println("HCAM_REAR_CAM1_CAPT (To take a picture from REAR cam1)");
								Thread.sleep(1000);
								System.out.println("HCAM_REAR_CAM1_OFF (to turn off the REAR camera1)");
								Thread.sleep(1000);
					}
					else
					{
						System.out.println("HAZCAM Rear Camera 1 is not Working Condition . So we use Rear Backup Camera1");
						Thread.sleep(1000);
						System.out.println("HCAM_REAR_BACKUP_CAM1_ON (to turn on rear backup camera1)");
						Thread.sleep(3000);
						System.out.println("HCAM_REAR_BACKUP_CAM1_CAPT (To take a picture from rear backup cam1)");
						Thread.sleep(1000);
						System.out.println("HCAM_REAR_BACKUP_CAM1_OFF (to turn off the rear backup camera1)");
						Thread.sleep(1000);	
						
					}				
								
								System.out.println("<HazCam Server>");
								System.out.println("");
							} 
					
					
					
					else if (message.equalsIgnoreCase("HAZCAM_REAR_CAMERA2__IMAGE")) {
								

								@SuppressWarnings("unused")
								MyWriter JSONWriter = new MyWriter(Hazcam_Class,
										Constants.FIFTEEN);

								System.out.println("");
								System.out.println("<HazCam Server>");
								Thread.sleep(1000);
								if(REAR_CAM2_STATUS)
								{
								
								System.out.println("HCAM_REAR_CAM2_ON (to turn on the REAR camera2)");
								Thread.sleep(3000);
								System.out.println("HCAM_REAR_CAM2_CAPT (To take a picture from REAR cam2)");
								Thread.sleep(1000);
								System.out.println("HCAM_REAR_CAM2_OFF (to turn off the REAR camera2)");
								Thread.sleep(1000);
							
							}
							else
							{
								System.out.println("HAZCAM Front Camera 2 is not Working Condition . So we use Front Backup Camera2");
								Thread.sleep(1000);
								System.out.println("HCAM_REAR_BACKUP_CAM2_ON (to turn on rear backup camera2)");
								Thread.sleep(3000);
								System.out.println("HCAM_REAR_BACKUP_CAM2_CAPT (To take a picture from rear backup cam2)");
								Thread.sleep(1000);
								System.out.println("HCAM_REAR_BACKUP_CAM2_OFF (to turn off rear backup camera2)");
								Thread.sleep(1000);	
							}		
							System.out.println("<HazCam Server>");
								System.out.println("");
							} 
							
							
							
							else if (message.equalsIgnoreCase("CLIENT_MODULE_GET")) {
								// The server reads another a JSON Object in memory
								GlobalReader JSONReader = new GlobalReader(Constants.FIFTEEN);
								JSONObject thatOtherObject = JSONReader.getJSONObject();

								// Integers are passed as longs
								Long myLong = (Long) thatOtherObject.get("myInteger");

								// Pass the long back into an integer
								Integer myInteger = new Integer(myLong.intValue());
								String myString = (String) thatOtherObject.get("myString");

								System.out.println("");
								System.out
										.println("<Start> Hazcam Server Receiving <Start>");
								System.out
										.println("===========================================");
								System.out.println("This is Class " + Constants.FIFTEEN
										+ "'s object ");
								System.out.println("myInteger = " + myInteger);
								System.out.println("myString = " + myString);
								System.out
										.println("===========================================");
								System.out.println("<End> Hazcam Server Receiving <End>");
								System.out.println("");
								
							}
					
					
					
					
					
					
				} 
				
				if (message.equalsIgnoreCase("CLIENT_MODULE_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.FIFTEEN);
					JSONObject thatOtherObject = JSONReader.getJSONObject();

					// Integers are passed as longs
					Long myLong = (Long) thatOtherObject.get("myInteger");
					WriteImage WI= new WriteImage();
					// Pass the long back into an integer
					Integer myInteger = new Integer(myLong.intValue());
					String myString = (String) thatOtherObject.get("myString");

					System.out.println("");
					System.out
							.println("<Start> Hazcam Server Receiving <Start>");
					System.out
							.println("===========================================");
					System.out.println("This is Class " + Constants.FIFTEEN
							+ "'s object ");
					Hazcam_Class.printObject();
					System.out
							.println("===========================================");
					System.out.println("<End> Hazcam Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket Hazcam!!");
			//CallBack cb = new CallBack();
			//cb.done();
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
