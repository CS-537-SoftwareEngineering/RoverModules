package chemCam;

import generic.RoverServerRunnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ccamServer extends RoverServerRunnable{

	public ccamServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {			
			
			while(true){				
	            System.out.println("Server: Waiting for client request");	            
				//creating socket and waiting for client connection
	            getRoverServerSocket().openSocket();
	            //read from socket to ObjectInputStream object
	            ObjectInputStream ois = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
	            //convert ObjectInputStream object to String
	            String message = (String) ois.readObject();
	            System.out.println(message.toUpperCase());
	            
	            if(message.toUpperCase().equals("CCAM_POWER_ON"))
	            {
	            	 //create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("CHEMCAM TRUN ON");
		            oos.close();
	            }
	            else if(message.toUpperCase().equals("CCAM_CWL_WARM"))
	            {
	            	 //create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("CWL TRUN ON");
		            oos.close();
	            }
	            else if(message.toUpperCase().equals("CCAM_LIBS_WARM"))
	            {
	            	 //create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("LIBS warm");
		            oos.close();
	            }
	            else if(message.toUpperCase().equals("CCAM_SET_FOCUS")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("SET FOCUS");
		            oos.close();
	            }
	            else if(message.toUpperCase().equals("CCAM_LASER_ON")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		           
		            //Date end_time = new Date(start_time.getTime()+5*60*1000);
		            oos.writeObject("CCAM_FIRE_LASER");
		            
		          //  Thread.sleep(2000);
		           // oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //oos.writeObject("CCAM_LASER_OFF");
		            oos.close(); 
	            }
	            
	            else if(message.toUpperCase().equals("CCAM_COOLER_ON")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("Start cooler to cool the laser");
		            oos.close();
	            }
	            
	            else if(message.toUpperCase().equals("CCAM_COOLER_OFF")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("cooler turnig off");
		            oos.close();
	            }
	            
	            else if(message.toUpperCase().equals("CCAM_DATA_SEND")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("communication with chemcam and communication device");
		            oos.close();
	            }
	            
	            
	            else if(message.toUpperCase().equals("CCAM_POWER_OFF")){
	            	//create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("CHEMCAM turing off");
		            oos.close();
	            }
	            
	            
	            //close resources
	            ois.close();
	           
	            //getRoverServerSocket().closeSocket();
	            //terminate the server if client sends exit request
	            if(message.equalsIgnoreCase("CCAM_POWER_OFF")) break;
	        }
			System.out.println("Server: Shutting down Socket server!!");
	        //close the ServerSocket object
	        closeAll();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
        catch(Exception error){
        	System.out.println("Server: Error:" + error.getMessage());
        }
		
	}
}
