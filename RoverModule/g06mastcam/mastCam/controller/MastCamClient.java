package mastCam.controller;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;


public class MastCamClient extends RoverClientRunnable{
	
	int outgoingCommandNumber;
	public MastCamClient(int port, InetAddress host, int commandNumber)
			throws UnknownHostException {
		super(port, host);
		outgoingCommandNumber = commandNumber; 
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //write to socket using ObjectOutputStream
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
            
            System.out.println("MastCam Client: Sending request to Socket Server");
            
            if(outgoingCommandNumber == 1){
            	outputToAnotherObject.writeObject("POW_CALC 13");
            }
            else if(outgoingCommandNumber == 2){
            	//outputToAnotherObject.writeObject("POW_OFF");
            }

            outputToAnotherObject.close();
            Thread.sleep(5000);
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
