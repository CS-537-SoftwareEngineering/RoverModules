package chemCam;

import generic.RoverClientRunnable;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.json.simple.JSONObject;

public class ccamClient extends RoverClientRunnable{

	public ccamClient(int port, InetAddress host) throws UnknownHostException {
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
	   
		
	      
	    //  System.out.println(start_time);
	            //establish socket connection to server
	            //socket = new Socket(host.getHostName(), 9876);
	            //write to socket using ObjectOutputStream
	            
	            
	      oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
          System.out.println("Chemcam starting.....");
          
      	//cwl warm
      	oos.writeObject("CCAM_POWER_ON");
      	//read the server response message
          ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
          String power_on = (String) ois.readObject();
          System.out.println(power_on.toUpperCase());
          ois.close();
          oos.close();
          Thread.sleep(2000);
	      
	      
	      
	      if(distance < 7){
	            if(temp < -10){
	            	
	            	oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            System.out.println("Client: Sending request to Socket Server");
		            
	            	//cwl warm
	            	oos.writeObject("CCAM_CWL_WARM");
	            	//read the server response message
		            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String cwl = (String) ois.readObject();
		            System.out.println(cwl.toUpperCase());
		            ois.close();
		            oos.close();
		            Thread.sleep(2000);
		            
		            
		            
		            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            System.out.println("Setting focus on surface......");
		            //telescop focus
		            
		            
		            oos.writeObject("CCAM_SET_FOCUS");
		            
		            
		          //read the server response message
		            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String telescop = (String) ois.readObject();
		            System.out.println( telescop.toUpperCase());
		            System.out.println("Targetting Micro image.....");
		         
		            ois.close();
		            oos.close();
		            Thread.sleep(2000);
		            
	            }  
	            else{
	            	
	            	oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	                System.out.println("LIBS warming......");
	                
	            	//cwl warm
	            	oos.writeObject("CCAM_LIBS_WARM");
	            	//read the server response message
	                ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	                String libs = (String) ois.readObject();
	                System.out.println(libs.toUpperCase());
	                ois.close();
	                oos.close();
	                Thread.sleep(2000);
	            	
	            }
		            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            System.out.println("Startting laser...");
		            //laser
		            
		            
		            oos.writeObject("CCAM_LASER_ON");
		            
		            
		          //read the server response message
		            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String laser = (String) ois.readObject();
		            System.out.println(laser.toUpperCase());
		           // Date start_time = new Date();
		            	
		            
		            	if(laser.toUpperCase().equals("CCAM_FIRE_LASER")){
		            
		            		System.out.println("Laser beam fire the laser on mars surface");
		            		 Date start_time = new Date();
		            		 Date end_time = new Date(start_time.getTime()+5*60*1000);
		            		 Thread.sleep(2000);
		            		
		            		boolean lsr = true;
		            		System.out.println("Taking images");
		            		
		            		
		            	
		            		
		            		System.out.println("turn off laser beam");
		            		
		            		oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
				            System.out.println("Startting CCD cooler...");
		            		
				            oos.writeObject("CCAM_COOLER_ON");
				            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				            String ccd_start = (String) ois.readObject();
				            System.out.println(ccd_start.toUpperCase());
				            Thread.sleep(2000);
				            
				            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
				            System.out.println("CCD cooler turn off...");
		            		
				            oos.writeObject("CCAM_COOLER_OFF");
				            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				            String ccd = (String) ois.readObject();
				            System.out.println(ccd.toUpperCase());
				            
				            
				            
				            
				            
		            		
		            		
				           // end: System.out.println();
		            	}
		            	
		            
		            ois.close();
		            oos.close();
		            Thread.sleep(2000);
		            
		            
		            
		            JSONObject obj = new JSONObject();
		    		
		    		obj.put("silicon", 100.23);
		    		obj.put("megnasium",234.56);
		    		obj.put("iron",333.45);
		    		obj.put("sodium", 3544.77);
		    		JSON.MyWriter.MyWriter(obj,11);
		            
		            
		            
		            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            System.out.println("Sending data to communication device");
		            //telescop focus
		            
		            
		            oos.writeObject("CCAM_DATA_SEND");
		            
		            
		          //read the server response message
		            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String send = (String) ois.readObject();
		            System.out.println( send.toUpperCase());
		            System.out.println("Sending data");
		         
		            ois.close();
		            oos.close();
		            Thread.sleep(2000);
		            
		            
		            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            System.out.println("After sending data chemcam tring to turn off....");
		            //telescop focus
		            
		            
		            oos.writeObject("CCAM_POWER_OFF");
		            
		            
		          //read the server response message
		            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
		            String power_off = (String) ois.readObject();
		            System.out.println( power_off.toUpperCase());
		            System.out.println("CHEMCAM TURN OFF");
		         
		            ois.close();
		            oos.close();
		            Thread.sleep(2000);
		            
		            
		            
	            
	            	
	            
	            
	           
	          //  Thread.sleep(1000);
	        
	        closeAll();
	      }
	      else{
	    	  System.out.println("Setting Distance less than the 7 meters from chemcam to suface area");
	    	  
	    	  
	    	  oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            System.out.println("After sending data chemcam tring to turn off....");
	            //telescop focus
	            
	            
	            oos.writeObject("CCAM_POWER_OFF");
	            
	            
	          //read the server response message
	            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String power_off = (String) ois.readObject();
	            System.out.println( power_off.toUpperCase());
	            System.out.println("CHEMCAM TURN OFF");
	         
	            ois.close();
	            oos.close();
	            Thread.sleep(2000);
	      }
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
