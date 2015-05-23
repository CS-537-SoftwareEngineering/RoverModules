package usecase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TemperatureResponse {
	String name;
	double temperature;
	
	TemperatureResponse(String name, double temperature){
		setName(name);
		setTemperature(temperature);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public String jsonify(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	
}
