package chemCam;

import generic.RoverThreadHandler;

import java.io.IOException;


public class main {
	public static void main(String args[]){
int port = 9011;
		
		try {
			
			ccamServer ccamServer = new ccamServer(port);
			Thread server = RoverThreadHandler.getRoverThreadHandler().getNewThread(ccamServer);
			
			ccamClient ccamClient = new ccamClient(port, null);
			Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(ccamClient);
			
			server.start();
			
			client.start();
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
