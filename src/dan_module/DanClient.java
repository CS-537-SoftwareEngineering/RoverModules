package dan_module;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	public void run() {
		try
		{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //Send 2 commands to the Server
	        for(int i = 0; i < 2; i++)
	        {
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Client: Sending request to Socket Server");
	            
	            if(	i == 1	)
	            {
	            	outputToAnotherObject.writeObject("exit");
	            }
	            
	            else if( i == 0 )
	            {
	            	outputToAnotherObject.writeObject("HYDROGEN CONTENT??");
	            }
	           
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            System.out.println("Client: Message from Server - " + message.toUpperCase());
	            
	            // The server sends us a JSON String here
	   //         String jsonString = (String) inputFromAnotherObject.readObject();
	   //         System.out.println("Client: Message from Server - " + jsonString.toUpperCase());
	            
	          //  String reply = (String) inputFromAnotherObject.readObject();
	          //  System.out.println("Client: Message from Server - " + reply.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	    //       JSONParser parser = new JSONParser();
	    //       JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            // Integers are passed as longs
		//		Long myLong = (Long) jsonObject.get("DAN_COLLECT_HYD");
				
				// Pass the long back into an integer
		//		Integer data = new Integer(myLong.intValue());
			//	String myString = (String) jsonObject.get("myString");
				
	            String message1 = (String) inputFromAnotherObject.readObject();
	            System.out.println("Client: Message from Server - " + message1.toUpperCase());
				 
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	        }
	        closeAll();
		}	        
        
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception error) 
		{
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}

