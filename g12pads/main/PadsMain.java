package main;

import generic.RoverThreadHandler;
import server.PadsServer;
import json.Constants;
import java.io.IOException;

import other.TestClientForPads;


public class PadsMain {
public static void main(String[] args) {
		
		try {
			
			//Our module server
			PadsServer server12 = new PadsServer(Constants.PORT_TWELVE);
			Thread server_12 = RoverThreadHandler.getRoverThreadHandler().getNewThread(server12);
			server_12.start(); 
			
			//Testing client from which we are getting command to drill on the surface of the mars
			TestClientForPads testClient = new TestClientForPads(Constants.PORT_TWELVE, null); 
			Thread test = RoverThreadHandler.getRoverThreadHandler().getNewThread(testClient);
			test.start();
			
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
