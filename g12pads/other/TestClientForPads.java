package other;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.text.StyledEditorKit.BoldAction;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class TestClientForPads extends RoverClientRunnable{

	public TestClientForPads(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{

			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
	        for(int i = 0; i < 5; i++){
	        	System.out.println("TEST Client: Sending request to Socket Server");
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            if(i == 4){
//	            	outputToAnotherObject.writeObject("exit");
	            	outputToAnotherObject.writeObject("BITS_STUCK");
	            	Thread.sleep(5000);
	            	inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String jsonString = (String) inputFromAnotherObject.readObject();
		            JSONParser parser = new JSONParser();
		            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
					Long myLong = (Long) jsonObject.get("spare_bits");
					Integer spare_bits = new Integer(myLong.intValue());
				
	            	System.out.println("");
					System.out.println("===========================================");
					System.out.println("Message to TEST client: Bits Loaded");
					System.out.println("Remaining number of spare bits :"+spare_bits);
					System.out.println("===========================================");
					System.out.println("");
	            }
	            
	            else if(i == 3){
	            	outputToAnotherObject.writeObject("DRT_START");
	            	Thread.sleep(5000);
	            	inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String jsonString = (String) inputFromAnotherObject.readObject();
		            JSONParser parser = new JSONParser();
		            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
					Long myLong = (Long) jsonObject.get("drt_status");
					Integer drt_status = new Integer(myLong.intValue());
					boolean cleaning_completed = (boolean) jsonObject.get("cleaning_completed");
					
					System.out.println("");
					System.out.println("===========================================");
					System.out.println("Message to TEST client: Dust Removed");
					System.out.println("drt_status = " + drt_status);
					System.out.println("cleaning_completed = " + cleaning_completed);
					System.out.println("===========================================");
					System.out.println("");
	            }
	            else if(i == 2) {
	            	outputToAnotherObject.writeObject("DRILL_START");
	            	Thread.sleep(5000);
					inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String jsonString = (String) inputFromAnotherObject.readObject();
		            JSONParser parser = new JSONParser();
		            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
					Long myLong = (Long) jsonObject.get("drill_status");
					Integer drill_status = new Integer(myLong.intValue());
					boolean sample_exist = (boolean) jsonObject.get("sample_exist");
					
					System.out.println("");
					System.out.println("===========================================");
					System.out.println("TEST client successfully got the SAMPLE");
					System.out.println("drill_status = " + drill_status);
					System.out.println("sample_exist = " + sample_exist);
					System.out.println("===========================================");
					System.out.println("");
	            }
	            else if(i == 1) {
	            	outputToAnotherObject.writeObject("DRT_STATUS");
	            	Thread.sleep(3000); 
					inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String jsonString = (String) inputFromAnotherObject.readObject();
		            JSONParser parser = new JSONParser();
		            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
					Long myLong = (Long) jsonObject.get("drt_status");
					Integer drill_status = new Integer(myLong.intValue());
					
					System.out.println("");
					System.out.println("===========================================");
					System.out.println("DRT is currently sleeping");
					System.out.println("drt_status = " + drill_status);
					System.out.println("===========================================");
					System.out.println("");
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("DRILL_STATUS");
	            	Thread.sleep(3000);
					inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String jsonString = (String) inputFromAnotherObject.readObject();
		            JSONParser parser = new JSONParser();
		            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
					Long myLong = (Long) jsonObject.get("drill_status");
					Integer drill_status = new Integer(myLong.intValue());
					
					System.out.println("");
					System.out.println("===========================================");
					System.out.println("Drill is currently sleeping");
					System.out.println("drill_status = " + drill_status);
					System.out.println("===========================================");
					System.out.println("");
	            }
	             
	            
	            
	            inputFromAnotherObject.close(); 
	            outputToAnotherObject.close();
	            
	        }
	        closeAll();
			
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("TEST client: Error:" + error.getMessage());
		}
	}

}
