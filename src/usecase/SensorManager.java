package usecase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import generic.RoverThreadHandler;

public class SensorManager {
	int port = 9897;
	static String jsonFile;
	static Map<Modules, ModuleBase> moduleMap;
	public static SensorManager sensorManager;
	
	private SensorManager(){
		try {
			initSensorManager();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SensorManager getSensorManager(){
		
		if(sensorManager == null)
			sensorManager = new SensorManager();
		
		return sensorManager;		
		
	}
	
	private void initSensorManager() throws UnknownHostException{
		setModuleMap();
		for (Modules mod : moduleMap.keySet()) {
			Sensor sensor = new Sensor(port, null, mod, moduleMap.get(mod));
			Thread client = RoverThreadHandler.getRoverThreadHandler().getNewThread(sensor);
			client.start();
		}
		
	}
	public static void setModuleMap(){
		moduleMap = new HashMap<Modules, ModuleBase>();
		
		// JSONParser is used to parse the data
		jsonFile = "C:\\Users\\debasish\\git\\RoverModules\\data\\device_temp.json";
		JSONParser parser = new JSONParser();
		Object tempObjects;
		try {
			tempObjects = parser.parse(new FileReader(jsonFile));
			 JSONObject jsonObjects = (JSONObject) tempObjects;
			//from the JSONObject create the array of temperature
		    JSONArray tempArray = (JSONArray) jsonObjects.get("instruments");
		    
		    for (Object o : tempArray) {

				JSONObject jo = (JSONObject) o;

				//System.out.println(jo.get("maxTemp").toString());
				ModuleBase moduleBase = new ModuleBase();
				moduleBase.setMaxTemp(new Double(Double.parseDouble(jo.get(
						"maxTemp").toString())));
				moduleBase.setMinTemp(new Double(Double.parseDouble(jo.get(
						"minTemp").toString())));

				for (Modules mod : Modules.values()) {	
					moduleMap.put(mod, moduleBase);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   
	}

}
