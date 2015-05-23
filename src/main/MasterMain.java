package main;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverThreadHandler;

import java.io.IOException;

import mastCam.controller.MastCamServer;
import mastCam.controller.Ports;

public class MasterMain {

	public static void main(String[] args) {
		try {
			// create a thread for module one
			MastCamServer server = new MastCamServer(Ports.MASTCAM_PORT);
			Thread serverThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(server);
			serverThread.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
