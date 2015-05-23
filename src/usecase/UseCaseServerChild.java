package usecase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverClientRunnable;
import generic.RoverServerRunnable;

public class UseCaseServerChild extends RoverClientRunnable {
	
	private RoverServerRunnable parent;
	
	private Socket socket;
	
	private String commandStr;
	
	private String moduleName;
	
	private String responseString;
	
	//private String commStr;
	
	private String moduleCommand;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public UseCaseServerChild() throws IOException {
		super(0, null);
		// TODO Auto-generated constructor stub
	}
	public void checkCommand()
	{
	  String jsonString = "{\"data\":{\"name\":\"Chemcam\",\"Command2\":\"CURRENT_TEMP\"}}";
	  // String jsonString = "{'data':{'name':'Chemcam','Command':'CURRENT_TEMP'}}";
	  org.json.JSONObject jObject;
	  
	  //converting string to JSON
      try
      {
	    jObject = new org.json.JSONObject(jsonString);
	    org.json.JSONObject data = jObject.getJSONObject("data"); // get data object
		moduleName = data.getString("name"); // get the name from data.
		moduleCommand = data.getString("command");
		
		System.out.println("Name: "+moduleName);
		System.out.println("Command: " + moduleCommand);
		  
      } catch (org.json.JSONException e)
      {
	    e.printStackTrace();
      } // json
	  
	  	  
	}
	public void processCommand(){
		//
		//
		//
		//
		checkCommand();
		ModuleBase modBase = null;
		if(moduleName.isEmpty() != true)
			modBase = ThermalDataSector.getTempDataSector().getModule(Modules.valueOf(moduleName.toUpperCase()));
		switch(ThermalCommands.valueOf(moduleCommand.toUpperCase())){
			case CURRENT_TEMPERATURE:
				responseString = ThermalDataSector.getTempDataSector().getOutsideTemperature();
				System.out.println("CURRENT_TEMPERATURE" +responseString);
				break;
			case OUTSIDE_EMPERATURE:
				modBase.getCurrTemp();
				TemperatureResponse tempResp =  new TemperatureResponse(moduleName, modBase.getCurrTemp());
				responseString = tempResp.jsonify();
				System.out.println("OUTSIDE_EMPERATURE" +responseString);
				break;
			case CURRENT_TEMPERATURES:
				responseString = ThermalDataSector.getTempDataSector().getModTemps();
				System.out.println("CURRENT_TEMPERATURES" +responseString);
				break;
			default:
				break;
		}
		ThermalDataSector.getTempDataSector().getModTemps();
		//
		
	}	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
					
	        //read from socket to ObjectInputStream object
	        ObjectInputStream ois = new ObjectInputStream(this.getSocket().getInputStream());
	        //convert ObjectInputStream object to String
	        commandStr = (String) ois.readObject();
	        System.out.println("Server: Message Received from Client - " + commandStr.toUpperCase());
	        //create ObjectOutputStream object
	        ObjectOutputStream oos = new ObjectOutputStream(this.getSocket().getOutputStream());
	        //write object to Socket
	        oos.writeObject("Server says Hi Client - " + commandStr);
	        //close resources
	        processCommand();
	        ois.close();
	        oos.close();
	        //getRoverServerSocket().closeSocket();
	        //terminate the server if client sends exit request
	        socket.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RoverServerRunnable getParent(){
		return parent;
	}
	
	public void setParent(RoverServerRunnable parent){
		this.parent = parent;
	}

}
