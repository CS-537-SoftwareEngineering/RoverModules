package testmodule;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class TestClient extends RoverClientRunnable{

	public TestClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{

			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
	        for(int i = 0; i < 4; i++){
	        	System.out.println("TEST Client: Sending request to Socket Server");
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            if(i == 3) {
                    outputToAnotherObject.writeObject("exit");
                    Thread.sleep(5000);
                }
	            else if(i == 2){
	            	outputToAnotherObject.writeObject("TEL_PRINT_INFO");
	            	Thread.sleep(25000);
	            }
	            else if(i == 1){
	            	outputToAnotherObject.writeObject("TEL_POWER_ON");
	            	Thread.sleep(25000);
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("TEL_POWER_OFF");
	            	Thread.sleep(25000);
	            }
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            inputFromAnotherObject.close(); 
	            outputToAnotherObject.close();
	        }
	        closeAll();
			
		}	        
        catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (Exception error) {
			// System.out.println("TEST client: Error:" + error.getMessage());
		}
	}

}
