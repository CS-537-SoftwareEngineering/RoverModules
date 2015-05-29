package ccam;

import generic.RoverClientRunnable;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import JSON.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UseCaseClient extends RoverClientRunnable{

	public UseCaseClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ObjectOutputStream oos = null;
		    ObjectInputStream ois = null;
		    Thread.sleep(2000);
	      int temp = -30;
	      float distance = 6;
	      
	      
	      Date e = new Date(System.currentTimeMillis()+5*60*1000);
	      System.out.println(e);
	      System.out.println("\n");
	      System.out.println("Target selection by Science Team or rover CPU...");
	      System.out.println("\n");
	   
		
	      
	    //  System.out.println(start_time);
	            //establish socket connection to server
	            //socket = new Socket(host.getHostName(), 9876);
	            //write to socket using ObjectOutputStream
	            
	      for(int i = 0; i < 7; i++){
	            //write to socket using ObjectOutputStream
	            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("Module 1 Client: Sending request to Socket Server");
	            
	            
	             if(i == 6) {
	            	oos.writeObject("TURN_OFF_ChemCam");
	            }  
	            else if(i == 5) {
	            	oos.writeObject("COOLER_ON");
	            }   
	            else if(i == 4) {
	            	oos.writeObject("LASER_ON");
	            }    
	            else if(i == 3){
	            	oos.writeObject("SET_FOCUS");
	            }
	            else if(i == 2) {
	            	oos.writeObject("LIBS_WARM");
	            }    
	            else if(i == 1) {
	            	oos.writeObject("CWL_ON");
	            }
	            else if(i == 0) {
	            	oos.writeObject("TURN_ON_ChemCam");
	            }    
	            
	            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) ois.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
	            
	            // The server sends us a JSON String here
	            String jsonString = (String) ois.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + jsonString.toUpperCase());
	            
	            // We can then parse the JSON String into a JSON Object
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	            
	            boolean POWER_ON = (boolean)jsonObject.get("POWER_ON");
	            boolean CWL_ON = (boolean)jsonObject.get("CWL_ON");
	            boolean LIBS_WARM = (boolean)jsonObject.get("LIBS_WARM");
	            boolean SET_FOCUS = (boolean)jsonObject.get("SET_FOCUS");
	            boolean LASER_ON = (boolean)jsonObject.get("LASER_ON");
	            boolean COOLER_ON = (boolean)jsonObject.get("COOLER_ON");
	            
	            
	            
				
				System.out.println("");
				System.out.println("Client 1 get :");
				System.out.println("--------------------------------------------");
				System.out.println("module " + Constants.ELEVEN + "'s object ");
				System.out.println("POWER_ON = " + POWER_ON);
				System.out.println("CWL_WARM = " + CWL_ON);
				System.out.println("LIBS_WARM = " + LIBS_WARM);
				System.out.println("SET_FOCUS = " + SET_FOCUS);
				System.out.println("LASER_ON = " + LASER_ON);
				System.out.println("COOLER_ON = " + COOLER_ON);
			    System.out.println("---------------------------------------------");
				System.out.println("");
	            
	            //close resources
	            ois.close();
	            oos.close();
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
