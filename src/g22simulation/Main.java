package g22simulation;

import generic.RoverThreadHandler;

import java.io.IOException;

import usecase.RequestsManager;
import usecase.SensorManager;
import usecase.UseCaseServer;

public class Main {
	private static int port = 2002;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub			
		try {
			UseCaseServer useCaseServer = new UseCaseServer(port);
			Thread server = RoverThreadHandler.getRoverThreadHandler().getNewThread(useCaseServer);

			server.start();
			
			//SensorManager.getSensorManager();
			RequestsManager.getRequestsManager();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPort(){
		return port;
	}
}
