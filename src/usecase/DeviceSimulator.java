package usecase;

import generic.RoverClientRunnable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class DeviceSimulator extends RoverClientRunnable {
	private State state;
	Modules id;
	ThermalCommands thermalCommand;
	CommandData command;
	public int waitTime = 3000;
	
	public DeviceSimulator(int port, InetAddress host, Modules id)
			throws UnknownHostException {
		super(port, host);
		this.id = id;
		startUp();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			Thread.sleep(waitTime);
			// while the rover is ON the sensors work

			while (state == State.ON) {
				
				thermalCommand = getRandomThermalCommand();				
				oos = new ObjectOutputStream(getRoverSocket().getNewSocket()
						.getOutputStream());
				
				command = new CommandData(id.toString(), thermalCommand.toString());
				String data = command.jsonify();
				oos.writeObject(data);
				
				ois = new ObjectInputStream(this.getRoverSocket().getSocket().getInputStream());
				System.out.println("Data From Server To " + id.toString() + ", Respose Of "+ thermalCommand.toString() +": "+ (String) ois.readObject());
				
				oos.close();
				Thread.sleep(waitTime);
			}	
			closeAll();
		}

		catch (UnknownHostException e) {
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
	
	public ThermalCommands getRandomThermalCommand(){
		double cmd = (float)Math.random();
		ThermalCommands thrmCmd = null;
		if(cmd >= 0 && cmd < 0.25){
			thrmCmd =  ThermalCommands.CURRENT_TEMPERATURE;
		}else if(cmd >= 0.25 && cmd < 0.5){
			thrmCmd =  ThermalCommands.OUTSIDE_TEMPERATURE;
		}else if(cmd >= 0.5 && cmd < 0.75){
			thrmCmd = ThermalCommands.CURRENT_TEMPERATURES;
		}else if(cmd >= 0.75 && cmd <= 1){
			thrmCmd = ThermalCommands.PROCESS_TEMPERATURE;
		}
		
		return thrmCmd;
		
		
	}

}