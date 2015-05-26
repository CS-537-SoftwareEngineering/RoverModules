package power;



import generic.RoverClientRunnable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

public class Power_Client extends RoverClientRunnable{
	private static String prefix = "Power client:";
	private String createdby = "";
	private String message = "";

	public Power_Client(int port, InetAddress host, String createdBy ,String message)
			throws UnknownHostException {
		super(port, host);
		this.createdby = createdBy;
		this.message = message;
	}

	@Override
	public void run() {
		try{
				
			 
				System.out.println(prefix+" Sending requirement - read 8.json:: From Chemin");
				
				
				//ObjectInputStream inputFromAnotherObject=new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				ObjectOutputStream outputObjectOutput=new ObjectOutputStream(getRoverSocket().getSocket().getOutputStream());

				//String Message=(String)inputFromAnotherObject.readObject();
				//System.out.println(Message);
				if(this.createdby.equalsIgnoreCase(Constants.CHEMIN)){
					outputObjectOutput.writeObject(Constants.POW_CALC);
					//outputObjectOutput.writeObject(this.createdby);
					//misha insert who is sneding r
				}
			
				
				//Trying to send Json file 1.json
				
				/*GlobalReader read=new GlobalReader(Constants.ROOT_PATH+Constants.ONE);
				JSONObject jread=read.getJSONObject();
				outputToAnotherObject.writeObject(jread);
				*/
				
	            //close resources
	          //  inputFromAnotherObject.close();
				outputObjectOutput.close();
			
	            Thread.sleep(5000);
	            closeAll();
		}	 catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client CCU: Error:" + error.getMessage());
		}  
		
	}

}
