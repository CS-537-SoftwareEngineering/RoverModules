package dan_module;

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

public class DanServer extends RoverServerRunnable {

	public DanServer(int port) throws IOException {
		super(port);
	}
	
	public void run() {
		
		DanClass moduleOneClass = new DanClass(1);
		
		try {
			System.out.println("Server: Power is ON"); 
			
			while (true) {
				
				System.out.println("Server: Waiting for client request...");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("HELLO Client");
				
			//	Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//	gson.toJson(moduleOneClass);
				
			//	outputToAnotherObject.writeObject(jsonString);
					DanClass.DAN_PNG_ON = true;
				if(		DanClass.DAN_PNG_ON	==	true	)
				{
					DanClass.DAN_DE_ON	=	true;
					
					outputToAnotherObject.writeObject(" Calculating the speed of Neutrons!!! ");
				    
					DanClass.speed = 3;
					if( DanClass.speed < 5 )
				    {
				    	double a = (	Math.random() * DanClass.speed	);
				    	
				    	outputToAnotherObject.writeObject("	Percentage of Hydrogen found is : "  + a +  " at	");
				    }
					else
					{
				    	System.out.println("There is no Hydrogen content present");
				    }
				}
				else
					break;
			
					
					
					
			/*		GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					Long myLong = (Long) thatOtherObject.get("DAN_COLLECT_HYD");
					
					//Pass long back to an Integer
					new Integer(myLong.intValue());
					thatOtherObject.get("DAN_COLLECT_HYD");
					
					System.out.println("");
					System.out.println("myInteger = " + Integer);
					System.out.println("");
		} */
 
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
				
			System.out.println("Server: Shutting down Socket server!!");
			// close the ServerSocket object
			closeAll();
			
			}
		
		 catch (IOException e)
		{
			 e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception error) 
		{
			System.out.println("Server: Error: " + error.getMessage());
		}

	}
}







