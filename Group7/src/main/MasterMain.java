package main;
import generic.RoverThreadHandler;

import java.io.IOException;

import module1.ModuleOneClient;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;
import module7.ModuleSevenClient;
import module7.ModuleSevenServer;
import module7.tempClient;
import module7.tempServer;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		//int port_one = 3332;
		//int port_two = 3334;
		//int port_seven=3336;
		int port_temp=3338;
		
		try {
			
			// create a thread for module one
			/*ModuleOneServer serverOne = new ModuleOneServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);*/
			
			/*// create a thread for module two
			ModuleTwoServer serverTwo = new ModuleTwoServer(port_two);
			Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);*/
			
			tempServer tempServer7 = new tempServer(port_temp);
			Thread tempServer_thread=RoverThreadHandler.getRoverThreadHandler().getNewThread(tempServer7);
			
			
			//ModuleSevenServer serverSeven = new ModuleSevenServer(port_seven);
			//Thread server_7 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverSeven);
			// each server begins listening
			//server_1.start();
			//server_2.start();
			//server_7.start();
			tempServer_thread.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			/*ModuleOneClient clientOne = new ModuleOneClient(port_temp, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);*/
			
			/*// client two server sending messages to server one
			ModuleTwoClient clientTwo = new ModuleTwoClient(port_one, null); // notice port_one
			Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientTwo);
			*/
			//ModuleSevenClient clientSeven = new ModuleSevenClient(port_one, null); // notice port_one
			//Thread client_7 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientSeven);
			
			
			tempClient tempClient7=new tempClient(port_temp,null);
			Thread tempClient_thread=RoverThreadHandler.getRoverThreadHandler().getNewThread(tempClient7);
			// start the thread which communicates through sockets
			//client_1.start();
			//client_2.start();
			//client_7.start();
			tempClient_thread.start();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
