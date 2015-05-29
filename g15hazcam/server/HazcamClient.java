package server;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class HazcamClient extends RoverClientRunnable{

	public HazcamClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    Boolean Heater_ON=false;
		    Random randomGenerator = new Random();
		    int ran = randomGenerator.nextInt(2);
		    if(ran==1)
		    	Heater_ON=true;
		    
		    
		    
		    
		  //Send 3 commands to the Server
	        for(int i = 0; i < 2; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Hazcam Client: Sending request to Socket Server");
	              if(i == 1){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("MODULE_PRINT");
	            }
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            if(Heater_ON)
	            	System.out.println("Hazcam Camera Need Heater.So Hazcam Need 3.5 Watt".toUpperCase());
	            else
	            	System.out.println("Hazcam Camera NO Need Heater.So Hazcam Need 2.2 Watt".toUpperCase());
	            Thread.sleep(1000);
	            System.out.println("Hazcam Client: Message from Server - " + message.toUpperCase());
	            
	            
	            
	            
	            // The server sends us a JSON String here
	            String jsonString = (String) inputFromAnotherObject.readObject();
	            System.out.println("Hazcam Module Client: Message from Server - " + jsonString.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            // Integers are passed as longs
				Long myLong = (Long) jsonObject.get("myInteger");
				
				// Pass the long back into an integer
				Integer myInteger = new Integer(myLong.intValue());
				String myString = (String) jsonObject.get("myString");
				
				System.out.println("");
				System.out.println("<Start>Hazcam Client now has: <Start>");
				System.out.println("===========================================");
				System.out.println("This is Class " + Constants.FIFTEEN + "'s object ");
				System.out.println("myInteger = " + myInteger);
				System.out.println("myString = " + myString);
				System.out.println("===========================================");
				System.out.println("<End>Hazcam Client now has: <End>");
				System.out.println("");
	            
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	        }
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			//System.out.println("Hazcam Client: Error:" + error.getMessage());
		}
		
	}

}
