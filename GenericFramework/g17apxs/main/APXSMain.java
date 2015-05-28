package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import server.APXSServer;
import module1.ModuleOneClient;
import json.Constants;

public class APXSMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port_seventeen = Constants.PORT_SEVENTEEN;
		
		try {
			
			APXSServer apxsServer = new APXSServer(port_seventeen);
			Thread server_APXS = RoverThreadHandler.getRoverThreadHandler().getNewThread(apxsServer);
			
			server_APXS.start();
			
			ModuleOneClient clientOne = new ModuleOneClient(port_seventeen, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
	
			client_1.start();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
