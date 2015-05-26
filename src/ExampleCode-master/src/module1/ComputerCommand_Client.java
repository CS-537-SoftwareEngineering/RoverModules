package module1;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

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
				
				ObjectOutputStream outputToAnotherObject=new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream()); 
				outputToAnotherObject.writeObject("CCU--> Hello CHEMIN I AM Sending you a JSON FILE");
				ObjectInputStream inputFromAnotherObject=new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				String Message=(String)inputFromAnotherObject.readObject();
				System.out.println(Message);
				//Trying to send Json file 1.json
				
				GlobalReader read=new GlobalReader(Constants.ROOT_PATH+Constants.ONE);
				JSONObject jread=read.getJSONObject();
				outputToAnotherObject.writeObject(jread);
				
				
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	            closeAll();
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
