package rad;

import java.util.Calendar;
import java.util.HashMap;

public class Rad {

	private final static double POWER_LEVEL_ON = 4.2;

	private final static double POWER_LEVEL_SLEEP = 1.2;
	
	private final static double POWER_LEVEL_OFF = 0.0;
	
	public final static double MIN_RADIATION = 0.1;
	
	public final static double MAX_RADIATION = Math.pow(10, 4);
	
	private double powerLevel;

	private HashMap<Long, Double> data = new HashMap<Long, Double>();
	
	private String state = "RAD_OFF";

	Rad() {
	}
	
	// Change state
	
	void off() {
		setState("RAD_OFF");
	}
	
	void bootup() {
		setState("RAD_BOOTUP");
	}
	
	void science() {
		setState("RAD_SCIENCE");
	}

	void checkout() {
		setState("RAD_CHECKOUT");
	}
	
	void shutdown() {
		setState("RAD_SHUTDOWN");
	}
	
	void sleep() {
		setState("RAD_SLEEP");
	}
	
	// Rover interaction
	
	boolean isOn() {
		return state.equals("RAD_BOOTUP") || state.equals("RAD_SCIENCE")
				|| state.equals("RAD_CHECKOUT");
	}

	double getPowerConsumption() {
		if (state.equals("RAD_OFF")) {
			return POWER_LEVEL_OFF;
		}
		if (state.equals("RAD_SLEEP")) {
			return POWER_LEVEL_SLEEP;
		} else {
			return POWER_LEVEL_ON;
		}
	}
	
	void addMeasurement(Double radiationLevel) {
		data.put(Calendar.getInstance().getTimeInMillis(), radiationLevel);
	}
	
	void clearData() {
		data.clear();
	}
	
	// Getters/Setters
	
	public HashMap<Long, Double> getData() {
		if (state.equals("RAD_CHECKOUT")) {
			return data;
		} else {
			return null;
		}
	}

	public void setData(HashMap<Long, Double> data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	void setState(String state) {
		this.state = state;
		
		setPowerLevel(getPowerConsumption());
	}

	public double getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(double powerLevel) {
		this.powerLevel = powerLevel;
	}

}
