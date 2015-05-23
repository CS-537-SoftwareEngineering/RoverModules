package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import dan_module.DanClient;
import dan_module.DanServer;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = Constants.PORT_ONE;
		
		try {
			
			// create a thread for module one
			DanServer serverOne = new DanServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
			
			// each server begins listening
			server_1.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			DanClient clientOne = new DanClient(port_one, null); // notice port_two
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
