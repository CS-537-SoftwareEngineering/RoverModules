package chemin;




import generic.RoverClientRunnable;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;
import json.GlobalReader;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CHEMIN_Client extends RoverClientRunnable{

	private static String prefix = "Chemin Client: ";
	private String createdby = "";
	private String message = "";
	public CHEMIN_Client(int port, InetAddress host,String createdBy, String message)
			throws UnknownHostException {
		super(port, host);
		this.createdby = createdBy;
		this.message = message;
	}

	@Override
	public void run() {
		try{
		 

		   
		    ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getSocket().getOutputStream());
			
		    if(createdby.equalsIgnoreCase(Constants.CCU)){
		    	  //Written by CCU Step1
			    System.out.println(prefix+" Sending request to CheminServer "+Constants.CMIN_TURN_ON+" :: From CCU");
				outputToAnotherObject.writeObject(Constants.CMIN_TURN_ON);
		    }else if(createdby.equalsIgnoreCase(Constants.POWER)){
		    	// Written by Power Step2
		    	  System.out.println(prefix+" Sending request to CheminServer  :: From "+Constants.POWER);
				outputToAnotherObject.writeObject(message);
		    }else if(createdby.equalsIgnoreCase(Constants.THERMAL)){
		    	// Written by Power Step2
		    	  System.out.println(prefix+" Sending request to CheminServer  :: From "+Constants.THERMAL);
				outputToAnotherObject.writeObject(message);
		    }/*else if(createdby.equalsIgnoreCase(Constants.TELECOMMUNICATION)){
		    	// Written by Power Step2
		    	  System.out.println(prefix+" Sending request to CheminServer  :: From "+Constants.TELECOMMUNICATION);
				outputToAnotherObject.writeObject(message);
		    }*/
		  
			
			outputToAnotherObject.close();
			Thread.sleep(5000);
			closeAll();
		}	        
        catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client CHEMIN: Error:" + error.getMessage());
		}
		
	}

}
