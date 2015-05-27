package main;


import generic.RoverThreadHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;




import controlmodule.ControlSystemClient;
import controlmodule.ControlSystemServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;



public class CommandMain 
{
public static void main(String[] args) 
{	
	
	
	int port_one = 9009;
	int port_two = 9002;
	
	try {
		
		// create a thread for module one
		  ControlSystemServer controlServer = new ControlSystemServer(port_one);
		Thread controlServerThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(controlServer);
		
		// create a thread for module two
		ModuleTwoServer telecomServer = new ModuleTwoServer(port_two);
		Thread telecomServerThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(telecomServer);
		
		// each server begins listening
		controlServerThread.start();
		telecomServerThread.start();
		
		// The following commands are examples of sending data: 
		// from module 1 client to module 2 server
		// and from module 2 client to module 2 server
		
		// client one server sending messages to server two
		ControlSystemClient clientOne = new ControlSystemClient(port_two, null); // notice port_two
		Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
		
		// client two server sending messages to server one
		ModuleTwoClient clientTwo = new ModuleTwoClient(port_one, null); // notice port_one
		Thread teleCommClient = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientTwo);
		
		// start the thread which communicates through sockets
		//client_1.start();
		teleCommClient.start();
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
