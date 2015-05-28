package module1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;
import main.APXS;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class ModuleOneClient extends RoverClientRunnable{

	public ModuleOneClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		    for(int i = 0; i < 5; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Module 1 Client: Sending request to Socket Server");
	            
	            if(i == 4){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else if(i == 3){
	            	outputToAnotherObject.writeObject("APXS_ON");
	            }
	            else if(i == 2) {
	            	outputToAnotherObject.writeObject("APXS_TEMP");
	            }
	            else if(i == 1) {
	            	outputToAnotherObject.writeObject("STOP ARM");
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("STOP MOBILITY");
	            }
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
	            
	            String jsonString = (String) inputFromAnotherObject.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + jsonString.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            System.out.println("<Start> Client 1: <Start>");
				System.out.println("This is Class " + Constants.SEVENTEEN + "'s object ");
				System.out.println("APXS_ON = " + APXS.isAPXS_ON());
				System.out.println("<End> Client 1: <End>");
				
	            
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
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}
