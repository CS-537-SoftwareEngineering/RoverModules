package module1;

import generic.RoverThreadHandler;

import java.io.IOException;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		
		int port_chemin = 9008;
		int port_ccu = 9009;
		int port_power = 9013;
		try {
			
			// create a thread for module one
			CHEMIN_Server serverChemin = new CHEMIN_Server(port_chemin);
			Thread server_chemin = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverChemin);
			server_chemin.start();
		
			ComputerCommand_Server serverccu = new ComputerCommand_Server(port_ccu); // notice port_two
			Thread server_ccu = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverccu);
			server_ccu.start();
			
			ComputerCommand_Server serverpower = new ComputerCommand_Server(port_ccu); // notice port_two
			Thread server_power = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverpower);
			server_power.start();
					
			ComputerCommand_Client clientCcu = new ComputerCommand_Client(port_ccu, null); // notice port_two
			Thread client_ccu = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientCcu);
			client_ccu.start();
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
