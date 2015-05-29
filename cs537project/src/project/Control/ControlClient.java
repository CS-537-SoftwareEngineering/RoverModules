package project.Control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import project.generic.RoverClientRunnable;

public class ControlClient extends RoverClientRunnable{

	public ControlClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(1000);
		    int sum = 0;
		    //Send 5 messages to the Server
	        for(int i = 100; i < 103; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("=================================================");
	            System.out.println("CONTROL Client: Sending request to Mobility Server");
	            System.out.println("=================================================");
	            
	            //Dummy data ll be sent to the server from here //
	             
	            
	            if(i == 102){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else if(i == 101) {
	            	outputToAnotherObject.writeObject("MBLTY_MOVE[137,265]");
	            	Thread.sleep(10000);
	            }
	            else if(i == 100) {
	            	outputToAnotherObject.writeObject("MBLTY_MOVE[135,263]");
	            	Thread.sleep(10000);
	            }
	            
	            
	            //close resources
	            outputToAnotherObject.close();
	            Thread.sleep(7000);
	        }
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("CONTROL Client: Error:" + error.getMessage());
		}
		
	}

}
