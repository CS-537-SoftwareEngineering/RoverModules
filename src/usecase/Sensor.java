package usecase;

import generic.RoverClientRunnable;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Sensor extends RoverClientRunnable {
	Modules ID;
	public int waitTime = 2000;
	Modules name;
	ModuleBase moduleBase;
	public double currentTemp;
	
	private State state;

	public Sensor(int port, InetAddress host, Modules ID, ModuleBase moduleBase)
			throws UnknownHostException {
		super(port, host);
		startUp();
		this.ID = ID;
		this.moduleBase = moduleBase;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream oos = null;
			Thread.sleep(waitTime);
			// while the rover is ON the sensors work

			while (state == State.ON) {

				currentTemp = randomTemperature(moduleBase.minTemp, moduleBase.maxTemp);
				
				oos = new ObjectOutputStream(getRoverSocket().getNewSocket()
						.getOutputStream());
				TemperatureResponse tempResp = new TemperatureResponse(ID.toString(), currentTemp);
				String data = tempResp.jsonify();
				
				//Gson gson = new GsonBuilder().setPrettyPrinting().create();

				//String jsonString = gson.toJson(data);
				//System.out.println(jsonString);
				oos.writeObject(data);
				
				oos.close();
				Thread.sleep(waitTime);
			}
			//if(state == State.OFF)
			closeAll();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println(": Error:"
					+ error.getMessage());
		}
	}
	
	public void shutDown(){
		this.state = State.OFF;
	}
	
	public void startUp(){
		this.state = State.ON;
	}
	
	public double randomTemperature(double min, double max){
		// add a temperature between -1 and 1 to the range
		double randomTemp = (float)Math.random()*2 - 1;
		return (double)Math.random()*(max) + min + randomTemp;
	}
}