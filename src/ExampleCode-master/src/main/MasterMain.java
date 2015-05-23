package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import module1.CHEMIN_Client;
import module1.CHEMIN_Server;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = 9008;
		
		
		try {
			
			// create a thread for module one
			CHEMIN_Server serverOne = new CHEMIN_Server(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
			
			// create a thread for module two
						
			// each server begins listening
			server_1.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			CHEMIN_Client clientOne = new CHEMIN_Client(port_one, null); // notice port_two
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
