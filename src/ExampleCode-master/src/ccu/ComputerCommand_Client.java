package ccu;



import generic.RoverClientRunnable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;

import json.Constants;
import json.GlobalReader;

public class ComputerCommand_Client extends RoverClientRunnable{

	public ComputerCommand_Client(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		//while(true){
		try{
			ObjectOutputStream ostr=new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
			GlobalReader gr=new GlobalReader(Constants.ROOT_PATH+"1");
			JSONObject json= gr.getJSONObject();
			ostr.writeObject(json);
			ObjectInputStream instr=new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
			System.out.println(instr.readObject().toString());
			
			
		}	 catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client CCU: Error:" + error.getMessage());
		}  
	//}
        
		
	}

}
