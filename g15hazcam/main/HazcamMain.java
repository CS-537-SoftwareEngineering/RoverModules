package main;
import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import power.*;
import server.*;


		public class HazcamMain {

		public static void main(String[] args) {
		

		int port_hcam=Constants.PORT_HAZCAM;
		int port_power=Constants.PORT_POWER;
		
		try {
			
			// create a thread for HazCam
			HazcamServer hserver = new HazcamServer(port_hcam);
			Thread server_hazcam = RoverThreadHandler.getRoverThreadHandler().getNewThread(hserver);
			
			// create a thread for POWER
			PowerServer pserver = new PowerServer(port_power);
			Thread server_power = RoverThreadHandler.getRoverThreadHandler().getNewThread(pserver);
						
			// each server begins listening
			server_hazcam.start();
			server_power.start();
			
			// Control client  sending messages to HazCam server 
			TempClient Tempclient = new TempClient(port_hcam, null); // notice port_one
			Thread client_Temp = RoverThreadHandler.getRoverThreadHandler().getNewThread(Tempclient);
			
			// start the thread which communicates through sockets
			// each server begins listening
			client_Temp.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}



