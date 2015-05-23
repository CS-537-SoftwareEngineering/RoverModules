package g22simulation;

import generic.RoverThreadHandler;

import java.io.IOException;

import usecase.Sensor;
import usecase.SensorManager;
import usecase.UseCaseServer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		int port = 9897;		

		try {

			UseCaseServer useCaseServer = new UseCaseServer(port);
			Thread server = RoverThreadHandler.getRoverThreadHandler().getNewThread(useCaseServer);

			server.start();
			
			SensorManager.getSensorManager();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
