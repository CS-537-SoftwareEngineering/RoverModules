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
			MastCamServer server = new MastCamServer((int)Ports.MASTCAM_PORT);
//			MastCamServer server = new MastCamServer(9006);
			Thread serverThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(server);
			serverThread.start();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
