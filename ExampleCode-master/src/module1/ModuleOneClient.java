package module1;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class ModuleOneClient extends RoverClientRunnable{

	public ModuleOneClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		  //Send 3 commands to the Server
	        for(int i = 0; i < 4; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Module 1 Client: Sending request to Socket Server");
	            
	            if(i == 3){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else if(i == 2){
	            	outputToAnotherObject.writeObject("TURN_OFF_ARM");
	            }
	            else if(i == 1) {
	            	outputToAnotherObject.writeObject("MOVE_INSTRUMENT");
	            }
	            else if(i == 0) {
	            	outputToAnotherObject.writeObject("TURN_ON_ARM");
	            }
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
	            
	            // The server sends us a JSON String here
	            String jsonString = (String) inputFromAnotherObject.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + jsonString.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            
	            //my stuff based on JSON format
	            boolean ARM_ON = (boolean)jsonObject.get("ARM_ON");
	            boolean CAMERA_ON = (boolean)jsonObject.get("CAMERA_ON");
	            
	            Long long_angle_theta1 = (Long) jsonObject.get("shoulder_arm_angle_theta1");
	            Integer angle_theta1 = new Integer(long_angle_theta1.intValue());
	            
	            Long long_angle_theta2 = (Long) jsonObject.get("arm_wrist_angle_theta2");
	            Integer angle_theta2 = new Integer(long_angle_theta2.intValue());
	            
	            Long long_DRT = (Long) jsonObject.get("DRT");
	            Integer DRT = new Integer(long_DRT.intValue());
	            
	            Long long_MAHLI = (Long) jsonObject.get("MAHLI");
	            Integer MAHLI = new Integer(long_MAHLI.intValue());
	            
	            Long long_DRILL = (Long) jsonObject.get("DRILL");
	            Integer DRILL = new Integer(long_DRILL.intValue());
	            
	            Long long_APXS = (Long) jsonObject.get("APXS");
	            Integer APXS = new Integer(long_APXS.intValue());
	            
	            Long long_CHIMRA = (Long) jsonObject.get("CHIMRA");
	            Integer CHIMRA = new Integer(long_CHIMRA.intValue());
	            
	            Long arm_power = (Long) jsonObject.get("arm_power");
	            
	            Long arm_time = (Long) jsonObject.get("arm_time");
	            
	            //end of my stuff based on JSON format
	            // Integers are passed as longs
				//Long myLong = (Long) jsonObject.get("myInteger");
				
				// Pass the long back into an integer
				//Integer myInteger = new Integer(myLong.intValue());
				//String myString = (String) jsonObject.get("myString");
				
				System.out.println("");
				System.out.println("<Start> Client 1 now has: <Start>");
				System.out.println("===========================================");
				System.out.println("This is Class " + Constants.SIXTEEN + "'s object ");
				System.out.println("ARM_ON = " + ARM_ON);
				System.out.println("CAMERA_ON = " + CAMERA_ON);
				System.out.println("shoulder_arm_angle_theta1 = " + angle_theta1);
				System.out.println("arm_wrist_angle_theta2 = " + angle_theta2);
				System.out.println("DRT = " + DRT);
				System.out.println("MAHLI = " + MAHLI);
				System.out.println("DRILL = " + DRILL);
				System.out.println("APXS = " + APXS);
				System.out.println("CHIMRA = " + CHIMRA);
				System.out.println("ARM_POWER = " + arm_power);
				System.out.println("ARM_TIME = " + arm_time);
				System.out.println("===========================================");
				System.out.println("<End> Client 1 now has: <End>");
				System.out.println("");
	            
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	        }
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}
