package main;

import java.io.IOException;

import server.PowerClient;
import server.PowerServer;
import usecase.UseCaseClient;
import usecase.UseCaseServer;
import generic.RoverThreadHandler;
import json.Constants;

public class PowerMain {

	public static void main(String[] args) {
	
			
		int port = 9897;
		
		try {
			
			PowerServer Powserver = new PowerServer(port);
			Thread server = RoverThreadHandler.getRoverThreadHandler().getNewThread(Powserver);
			
			PowerClient PowClient = new PowerClient(port, null);
			Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(PowClient);
			
			server.start();
			
			client.start();
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			}
}
