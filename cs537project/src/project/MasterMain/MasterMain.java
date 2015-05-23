package project.MasterMain;

import project.Attitude.AttitudeClient;
import project.Attitude.AttitudeServer;
import project.Mobility.MobilityClient;
import project.Mobility.MobilityServer;
import project.NavCam.NavCamClient;
import project.NavCam.NavCamServer;
import project.generic.RoverThreadHandler;

import java.io.IOException;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = 9897;
		int port_two = 9898;
		int port_three = 9896;
		int port_four = 9895;
		
		try {
			
			
			NavCamServer serverOne = new NavCamServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) serverOne);
		
			MobilityServer serverTwo = new MobilityServer(port_two);
			Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) serverTwo);
			
			AttitudeServer serverThree = new AttitudeServer(port_three);
			Thread server_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) serverThree);
			
			//server_1.start();
			server_2.start();
			server_3.start();
		
			NavCamClient clientOne = new NavCamClient(port_two, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clientOne);
			
			MobilityClient clientTwo = new MobilityClient(port_three, null); // notice port_one
			Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clientTwo);
			
			AttitudeClient clientThree = new AttitudeClient(port_four, null);
			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clientThree);
		
			client_1.start();
			client_3.start();
			client_2.start();
			
			} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
