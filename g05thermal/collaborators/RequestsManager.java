package collaborators;

import generic.RoverThreadHandler;

import java.net.UnknownHostException;

import constant.Modules;
import main.MasterMain;
import model.DeviceSimulator;


public class RequestsManager {
	private int port = MasterMain.getPort();
	private static String jsonFile;
	public static RequestsManager requestsManager;
	
	private RequestsManager(){
		try {
			initRequestsManager();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static RequestsManager getRequestsManager(){
		
		if(requestsManager == null)
			requestsManager = new RequestsManager();
		
		return requestsManager;		
		
	}
	
	private void initRequestsManager() throws UnknownHostException{
		int toggle = -2; 
		for (Modules mod : Modules.values()) {
			if(toggle < 0){
				//CURRENT_TEMPERATURES
				DeviceSimulator device = new DeviceSimulator(port, null, mod, toggle++);
				Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(device);
				client.start();
				//ATMSPHERIC/OUTSIDE_TEMPERATURES
				DeviceSimulator device2 = new DeviceSimulator(port, null, mod, toggle++);
				Thread client2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(device2);
				client2.start();
			}
			DeviceSimulator device = new DeviceSimulator(port, null, mod, toggle);
			Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(device);
			client.start();
		}

	}

	public static String getJsonFile() {
		return jsonFile;
	}

	public static void setJsonFile(String jsonFile) {
		RequestsManager.jsonFile = jsonFile;
	}

}
