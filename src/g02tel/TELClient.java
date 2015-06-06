package g02tel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class TELClient extends RoverClientRunnable{
    
    private String commandToSend = "";
    
	public TELClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    //Thread.sleep(5500);
		    
		    //Send 4 commands to the Server
	        for(int i = 0; i < 1; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            //System.out.println("=================================================");
	            System.out.println("Telecommunications: Sending request to Earth");
	            //System.out.println("=================================================");
	            
	            if(i == 0) {
	            	outputToAnotherObject.writeObject("RECEIVE_DATA");
	            }
	            
	            //close resources
	            // inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(7500);
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

    
    public void setCommandToSend( String commandToSend )
    {
        this.commandToSend = commandToSend;
    }
	

}
