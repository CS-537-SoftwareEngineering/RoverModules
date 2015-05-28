package chemin;


import generic.RoverThreadHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import power.Power_Server;
import telecommunication.Telecommunication_Server;
import thermal.Thermal_Server;
import json.Constants;
import ccu.ComputerCommand_Server;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port		
		try {
			
			// create a thread for Server
			CHEMIN_Server serverChemin = new CHEMIN_Server(Constants.PORT_CHEMIN);
			Thread server_chemin = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverChemin);
			server_chemin.start();
		
			ComputerCommand_Server serverCCU = new ComputerCommand_Server(Constants.PORT_CCU); 
			Thread server_ccu = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverCCU);
			server_ccu.start();
		
			CCU_SendsMessage();
			
			Power_Server serverPower = new Power_Server(Constants.PORT_POWER); 
			Thread server_power = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverPower);
			server_power.start();
		
			Thermal_Server serverThermal = new Thermal_Server(Constants.PORT_THERMAL); 
			Thread server_thermal = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverThermal);
			server_thermal.start();
			
			Telecommunication_Server serverTelecommunication = new Telecommunication_Server(Constants.PORT_TELECOMMUNICAION); 
			Thread server_tele = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTelecommunication);
			server_tele.start();
		
			/*ComputerCommand_Client clientCCU = new ComputerCommand_Client(Constants.PORT_CCU, null); 
			Thread client_ccu = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientCCU);
			client_ccu.start();*/
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void CCU_SendsMessage() throws UnknownHostException {
		
		CHEMIN_Client clientChemin = new CHEMIN_Client(Constants.PORT_CHEMIN, null,Constants.CCU,Constants.CMIN_TURN_ON); 
		Thread client_chemin = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientChemin);
		client_chemin.start();
	}

}
