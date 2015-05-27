package usecase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommandData {
	
	String commandName;
	String deviceName;
	
	CommandData(String commandName, String deviceName){
		setCommandName(commandName);
		setDeviceName(deviceName);
	}
	
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String jsonify(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

}
