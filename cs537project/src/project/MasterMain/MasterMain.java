package project.MasterMain;

import project.Power.PowerClient;
import project.Power.PowerServer;
import project.Control.ControlClient;
import project.Control.ControlServer;
import project.Mobility.MobilityClient;
import project.Mobility.MobilityServer;
import project.generic.RoverThreadHandler;
import project.json.Constants;

import java.io.IOException;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = Constants.PORT_ONE;
		int port_two = Constants.PORT_TWO;
		int port_three=Constants.PORT_THREE;
		int port_four=Constants.PORT_FOUR;
		
		
		try {
			
			PowerServer serverone = new PowerServer(port_one);
			Thread server_01 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) serverone);
			
			MobilityServer servertwo = new MobilityServer(port_two);
			Thread server_02 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) servertwo);
			
			
			ControlServer serverthree = new ControlServer(port_three);
			Thread server_03 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) serverthree);
		
			server_01.start();
			server_02.start();
			server_03.start();
			
			PowerClient clientone = new PowerClient(port_four, null);
			Thread client_01 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clientone);
		
			MobilityClient clienttwo = new MobilityClient(port_one, null); // notice port_one
			Thread client_02 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clienttwo);
			
//			client_01.start();
			client_02.start();
			ControlClient clientthree = new ControlClient(port_two, null); // notice port_two
			Thread client_03 = RoverThreadHandler.getRoverThreadHandler().getNewThread((Runnable) clientthree);
		
			client_03.start();
			} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
