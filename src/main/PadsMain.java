package main;

import generic.RoverThreadHandler;
import pads.PadsServer;

import java.io.IOException;

import testmodule.TestClient;


public class PadsMain {
public static void main(String[] args) {
		
		int port_one = 9012;
		
		try {
			
			//Our module server
			PadsServer server12 = new PadsServer(port_one);
			Thread server_12 = RoverThreadHandler.getRoverThreadHandler().getNewThread(server12);
			server_12.start(); 
			
			//Testing client from which we are getting command to drill on the surface of the mars
			TestClient testClient = new TestClient(port_one, null); // notice port_one
			Thread test = RoverThreadHandler.getRoverThreadHandler().getNewThread(testClient);
			test.start();
			
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
