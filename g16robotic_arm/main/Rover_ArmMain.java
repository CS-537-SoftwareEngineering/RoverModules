package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import module1.ModuleOneClient;
import module16.ModuleSixteenServer;
//import module2.ModuleTwoClient;
//import module2.ModuleTwoServer;

public class Rover_ArmMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_sixteen = Constants.PORT_SIXTEEN;
		
		try {

			
			ModuleSixteenServer serverSixteen = new ModuleSixteenServer(port_sixteen);
			Thread server_16 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverSixteen);
			
			server_16.start();
			
			// client one server sending messages to server sixteen
			ModuleOneClient clientOne = new ModuleOneClient(port_sixteen, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
	
			// start the thread which communicates through sockets
			client_1.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
