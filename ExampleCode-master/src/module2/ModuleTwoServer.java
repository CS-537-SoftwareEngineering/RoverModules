package module2;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

import org.json.simple.JSONObject;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class ModuleTwoServer extends RoverServerRunnable {

	public ModuleTwoServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		//MyClassHereTwo moduleTwoClass = new MyClassHereTwo(2);
		Rover_Arm arm = new Rover_Arm();
		
		try {
			
			while (true) {
				
				System.out.println("Module 2 Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Module 2 Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Module 2 Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(arm);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("TURN_ON_ARM")) {
					// The server prints out its own object
					long time = arm.ARM_PWR_ON();
					
					System.out.println("ARM_PWR_ON");
					System.out.println("ARM_Heating....");
					System.out.println("Turning ON the ARM....");
					System.out.println("The arm took: " + time);
					
					System.out.println("");
					System.out.println("<Server Two>");
					System.out.println("This is module " + Constants.TWO + "'s object at the start");
					arm.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("MOVE_INSTRUMENT")) {
					// The server does some example calculations
					//moduleTwoClass.addOne();
					//moduleTwoClass.changeString();
					
					//getting the fake camera value and Rover value, whether is on or off from another team
					GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					boolean myBoolean = (boolean) thatOtherObject.get("myBoolean");
					
					//Getting a fake instrument value (whether is on or off)
					GlobalReader JSONReader1 = new GlobalReader(Constants.THREE);
					JSONObject thatOtherObject1 = JSONReader1.getJSONObject();
					
					boolean instrument = (boolean) thatOtherObject1.get("instrument");
					
					//for all instruments and check which one wants to move
					int drt = (instrument)?1:0;
					int mahli = 0;
					int drill = 0;
					int apxs = 0;
					int chimra = 0;
					
					//
					
					arm.setCAMERA_ON(myBoolean);
					
					arm.setROVER_MOVE(myBoolean);
					
					if(arm.CAMERA_ON == false && arm.ROVER_MOVE == false){
						arm = arm.move(50, 120, drt, mahli, drill, apxs, chimra);
						
						
						String inst_status = arm.INST_STATUS();
						
						if(inst_status.equals("OFF")){
							System.out.println("No instrument was selected");
						}else{
							//displaying appropriate messages
							if(drt == 1){
								System.out.println("The instrument in use is DRT with: ");
								System.out.println("It's temperature ranges from -10 to 0 Celsius");
								System.out.println("It will warm up to 15 minutes ");
							}
							else if (mahli == 1){
								System.out.println("The instrument in use is MAHLI");
								System.out.println("It's temperature is ranges from -40 to 40 Celsius");
							}
							else if (drill == 1){
								System.out.println("The instrument in use is DRILL");
								System.out.println("It's temperature is ranges from -150 to 50 Celsius");
								System.out.println("It will warm up to 15 minutes");
							}
							else if (apxs == 1){
								System.out.println("The instrument in use is APXS");
								System.out.println("It's temperature is ranges from -15 to -5 Celsius");
							}
							else if (chimra == 1){
								System.out.println("The instrument in use is CHIMRA");
								System.out.println("It's temperature is ranges from -40 to 20 Celsius");
								System.out.println("It operates at night");
							}
						}
						
						//end of displaying appropriate messages
						
					}else{
						arm.ARM_PWR_STOW();
						
						if(arm.ROVER_MOVE == true){
							System.out.println("Rover is MOVING, ARM cannot move, set to ARM_BREAK");
						}
						
						if(arm.CAMERA_ON == true){
							System.out.println("Camera is ON, ARM cannot move, set to ARM_STOW");
						}
						
					}
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(arm, Constants.TWO);
					System.out.println("");
					System.out.println("<Server Two>");
					arm.printObject();
					System.out.println("<Server Two>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("TURN_OFF_ARM")) {
					// The server reads another a JSON Object in memory
					
					//this writes to json to turn off arm
					//arm.ARM_PWR_OFF();
					//@SuppressWarnings("unused")
					//MyWriter JSONWriter = new MyWriter(arm, Constants.TWO);
					//end of turning off arm
					
					
					GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject thatOtherObject = JSONReader.getJSONObject();

					// Integers are passed as longs
					Long oldLong = (Long) thatOtherObject.get("myInteger");
					
					// Pass the long back into an integer
					Integer myInteger = new Integer(oldLong.intValue());
					
					boolean myBoolean = (boolean) thatOtherObject.get("myBoolean");
					double myDouble = (double) thatOtherObject.get("myDouble");
					long myLong = (long) thatOtherObject.get("myLong");
					String myString = (String) thatOtherObject.get("myString");
					
					System.out.println("");
					System.out.println("<Start> Module 2 Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.ONE + "'s object ");
					System.out.println("myInteger = " + myInteger);
					System.out.println("myBoolean = " + myBoolean);
					System.out.println("myDouble = " + myDouble);
					System.out.println("myLong = " + myLong);
					System.out.println("myString = " + myString);
					System.out.println("===========================================");
					System.out.println("<End> Module 2 Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket server 2!!");
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
