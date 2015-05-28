package chemin;


import generic.RoverClientRunnable;
import generic.RoverThreadHandler;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;
import json.GlobalReader;

import org.json.simple.JSONObject;

public class CHEMIN_Client extends RoverClientRunnable{
	
	public CHEMIN_Client(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			
			if(getRoverSocket().getSocket().getPort()==9013){
				ObjectOutputStream outstr=new ObjectOutputStream(getRoverSocket().getSocket().getOutputStream());
				System.out.println("CHEMIN-->Power Group");
				System.out.println("CHEMIN-->Sending you my requirements in a json file");
				GlobalReader gr=new GlobalReader(Constants.ROOT_PATH+"PowerRequirement");
				JSONObject json= gr.getJSONObject();
				outstr.writeObject(json);
			}
}	        
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
