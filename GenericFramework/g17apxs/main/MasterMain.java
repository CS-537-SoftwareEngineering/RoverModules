package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import server.APXSClient;
import server.APXSServer;
import json.Constants;
import module1.ModuleOneClient;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_seventeen = Constants.PORT_SEVENTEEN;
		
		try {
			
			// create a thread for module one
			APXSServer serverAPXS = new APXSServer(port_seventeen);
			Thread server_APXS = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverAPXS);
			
			// create a thread for module two
			
			// each server begins listening
			server_APXS.start();
		
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			ModuleOneClient clientOne = new ModuleOneClient(port_seventeen, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			// client two server sending messages to server one
			
			// start the thread which communicates through sockets
			client_1.start();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
