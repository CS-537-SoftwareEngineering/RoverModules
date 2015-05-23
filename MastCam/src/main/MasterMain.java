package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import mastCam.controller.MastCamServer;
import mastCam.controller.Ports;
import module1.ModuleOneClient;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;

public class MasterMain {

	public static void main(String[] args) {
		try {
			// create a thread for module one
			MastCamServer server = new MastCamServer(Ports.MASTCAM_PORT);
			Thread serverThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(server);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
