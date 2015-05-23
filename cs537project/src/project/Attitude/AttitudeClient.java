package project.Attitude;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import project.generic.RoverClientRunnable;

public class AttitudeClient extends RoverClientRunnable{

	public AttitudeClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
		
			ObjectInputStream inputFromAnotherObject1 = null;
		    Thread.sleep(200000);
	                       
		    	inputFromAnotherObject1 = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message1 = (String) inputFromAnotherObject1.readObject();
	            
	            System.out.println("Attitude Client: Message from Server - " + message1.toUpperCase());
	            
	            inputFromAnotherObject1.close();
	            Thread.sleep(100000);
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client Attitude: Error:" + error.getMessage());
		}
		
	}

}

