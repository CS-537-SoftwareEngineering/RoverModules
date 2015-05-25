package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import module1.ModuleOneClient;
import module16.ModuleSixteenServer;
//import module2.ModuleTwoClient;
//import module2.ModuleTwoServer;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		//int port_one = Constants.PORT_ONE;
		//int port_two = Constants.PORT_TWO;
		int port_sixteen = Constants.PORT_SIXTEEN;
		
		try {
			
			// create a thread for module one
			//ModuleOneServer serverOne = new ModuleOneServer(port_one);
			//Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
			
			// create a thread for module two
			//ModuleTwoServer serverTwo = new ModuleTwoServer(port_sixteen);
			//Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);
			
			ModuleSixteenServer serverSixteen = new ModuleSixteenServer(port_sixteen);
			Thread server_16 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverSixteen);
			
			// each server begins listening
			//server_1.start();
			//server_2.start();
			
			server_16.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			ModuleOneClient clientOne = new ModuleOneClient(port_sixteen, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			
			// client two server sending messages to server one
			//ModuleTwoClient clientTwo = new ModuleTwoClient(port_one, null); // notice port_one
			//Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientTwo);
			
			// start the thread which communicates through sockets
			client_1.start();
			//client_2.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
