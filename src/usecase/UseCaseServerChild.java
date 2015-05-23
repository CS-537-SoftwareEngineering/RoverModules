package usecase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import generic.RoverClientRunnable;
import generic.RoverServerRunnable;

public class UseCaseServerChild extends RoverClientRunnable {
	
	private RoverServerRunnable parent;
	
	private Socket socket;
	
	private String commandStr;
	
	private String modStr;
	
	private String commStr;
	
	private String deviceStr;

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
	
	public void processCommand(){
		//ModuleBase modBase = ThermalDataSector.getTempDataSector().getModule(Modules.valueOf(deviceStr));
		//
		//modBase.getCurrTemp();
		//
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
	        //ObjectOutputStream oos = new ObjectOutputStream(this.getSocket().getOutputStream());
	        //write object to Socket
	        //oos.writeObject("Server says Hi Client - " + commandStr);
	        //close resources
	        processCommand();
	        ois.close();
	        //oos.close();
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
