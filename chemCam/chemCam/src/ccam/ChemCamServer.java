package ccam;
import generic.RoverServerRunnable;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.json.simple.JSONObject;

import JSON.Constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChemCamServer extends RoverServerRunnable{

	public ChemCamServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Chem_cam ccam = new Chem_cam();
		
		try {			
			
			while(true){				
	            System.out.println("Server: Waiting for client request");	            
				//creating socket and waiting for client connection
	            getRoverServerSocket().openSocket();
	            //read from socket to ObjectInputStream object
	            ObjectInputStream input = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
	            //convert ObjectInputStream object to String
	            String message = (String) input.readObject();
                System.out.println("Module 11 Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream output = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				output.writeObject("Module 11 Server response Hi Client - " + message);
			
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(ccam);
				
				output.writeObject(jsonString);
				
				// close resources
				input.close();
				output.close();
				
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("TURN_ON_ChemCam")) {
					// The server prints out its own object
					boolean get = ccam.PWR_ON();
					
					System.out.println("Turning on ChemCam!!");
					
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
				
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("CWL_ON")) {
					// The server prints out its own object
					boolean get = ccam.CWL_ON();
					
					System.out.println("Continuous wave-laer turning on!!");
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
					
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("LIBS_WARM")) {
					// The server prints out its own object
					boolean get = ccam.LIBS_WARM();
					
					System.out.println("Checking if LIBS is WARM?");
					System.out.println("Warming LIBS....");
					
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status:");
					ccam.printObject();
					
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("SET_FOCUS")) {
					// The server prints out its own object
					//TAKE DISTANCE AS WELL
					long distance = ccam.SET_FOCUS();
					
					System.out.println("Setting Focus by Telescope..");
					System.out.println("Getting Micro-Image of target object....");
					System.out.println("Determining the distance of object...");
					System.out.println("distance is - "+distance+ "meters..");
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
					
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("COOLER_ON")) {
					// The server prints out its own object
					boolean get = ccam.COOLER_ON();
					
					System.out.println("Turning on CCD COOLER...");
					
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
					
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("LASER_ON")) {
					// The server prints out its own object
					boolean get = ccam.LASER_ON();
					
					System.out.println("Fire Laser..");
					System.out.println("Collecting LIBS Spectra....");
					
					
					System.out.println("");
					
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
					
					System.out.println("");
					System.out.println("cool down lasers.....");
				}
				else if(message.equalsIgnoreCase("TURN_OFF_ChemCam")) {
					// The server prints out its own object
					
					JSONObject obj1 = ccam.CCAM_OFF();
					
					System.out.println("Turning off ChemCam!!!!");
					
					
					System.out.println("");
					System.out.println("module " + Constants.ELEVEN + "'s status");
					ccam.printObject();
					System.out.println("determining the data!!!!!!");
					System.out.println(obj1);
					
					System.out.println("");
					System.out.println("Analysis has been Complete.....");
				}
				
				
				
	            
	            
	            input.close();
	           
	           
	            
	        }
			System.out.println("Server: Shutting down Socket server!!");
	        //close the ServerSocket object
	        closeAll();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
        catch(Exception error){
        	System.out.println("Server: Error:" + error.getMessage());
        }
		
	}
}
