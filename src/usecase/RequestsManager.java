package usecase;

import g22simulation.Main;
import generic.RoverThreadHandler;

import java.net.UnknownHostException;
import java.util.ArrayList;


public class RequestsManager {
	int port = Main.getPort();
	static String jsonFile;
	static ArrayList<Modules> moduleNames;
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
		setModuleNames();
		for (Modules mod : moduleNames) {
			DeviceSimulator device = new DeviceSimulator(port, null, mod);
			Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(device);
			client.start();
		}
	}
	
	public static void setModuleNames(){
		moduleNames = new ArrayList<Modules>();
		
		for (Modules mod : Modules.values()) {	
			moduleNames.add(mod);
		}
	   
	}

}
