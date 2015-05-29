package server;

import generic.RoverServerRunnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

import apxsCode.apxsSpectra;
import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.APXS;
import module1.MyClassHere;
import json.Constants;
import json.GlobalReader;
import json.MyWriter;

public class APXSServer extends RoverServerRunnable{

	public APXSServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		APXS apxs = new APXS();
		MyClassHere moduleOneClass = new MyClassHere(1);
		try {			
			while(true){				
	            System.out.println("APXS Server: Waiting for client request");	            
			
	            //creating socket and waiting for client connection
	            getRoverServerSocket().openSocket();
	           
	            //read from socket to ObjectInputStream object
	            ObjectInputStream ois = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
	     
	            //convert ObjectInputStream object to String
	            String message = (String) ois.readObject();
	            System.out.println("APXS Server: Message Received from Client - " + message.toUpperCase());
	        
	            //create ObjectOutputStream object
	            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
	         
	            //write object to Socket
	            oos.writeObject("APXS Server response Hi Client - "+message);
	            
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleOneClass);
				
				oos.writeObject(jsonString);
	          
	            //close resources
	            ois.close();
	            oos.close();
	            //getRoverServerSocket().closeSocket();
	            //terminate the server if client sends exit request
	            if(message.equalsIgnoreCase("exit")) {
	            	System.out.println();
					System.out.println();
					apxs.setAPXS_RESULT_SEND(true);
					apxs.setAPXS_ON(false);
					System.out.println("");
					System.out.println("<Server Seventeen>");
					System.out.println("This is module " + Constants.SEVENTEEN + "'s object at the start");
					apxs.printObject();
					System.out.println("<Server Seventeen>");
					System.out.println("");
					break;
	            }
	            	
	            else if(message.equalsIgnoreCase("STOP MOBILITY")) {
					// The server prints out its own object
					System.out.println();
					System.out.println();
					System.out.println("Check arm is near to SAMPLE..");
					
					System.out.println("");
					System.out.println("<Server Seventeen>");
					System.out.println("This is module " + Constants.SEVENTEEN + "'s object at the start");
					apxs.printObject();
					System.out.println("<Server Seventeen>");
					System.out.println("");
				}
	            else if(message.equalsIgnoreCase("STOP ARM")) {
	            	
	            	System.out.println();
					System.out.println();
	            	System.out.println("Check TEMP..");
					System.out.println("");
					System.out.println("<Server Seventeen>");
					System.out.println("This is module " + Constants.SEVENTEEN + "'s object at the start");
					apxs.printObject();
					System.out.println("<Server Seventeen>");
					System.out.println("");
				
	            }
	            else if(message.equalsIgnoreCase("APXS_TEMP")) {
					
	            	System.out.println();
					System.out.println();
	            	System.out.println("Turn on Peltier Cooler..");
	            	apxs.setAPXS_TEMP(-40);
					System.out.println("");
					System.out.println("<Server Seventeen>");
					System.out.println("This is module " + Constants.SEVENTEEN + "'s object at the start");
					apxs.printObject();
					System.out.println("<Server Seventeen>");
					System.out.println("");
				
	            }else if(message.equalsIgnoreCase("APXS_ON")) {
	            	
	            	System.out.println();
					System.out.println();
	            	System.out.println("Turn on X-Ray and APXS..");
	            	apxs.setAPXS_TEMP(-40);
	            	apxs.setAPXS_ON(true);
	            	apxs.setAPXS_CON_SENSOR_ON(true);
	            	apxs.setAPXS_XRAY_SPEC_READ(true);
					System.out.println("");
					System.out.println("<Server Seventeen>");
					System.out.println("This is module " + Constants.SEVENTEEN + "'s object at the start");
					apxs.printObject();
					System.out.println("<Server Seventeen>");
					System.out.println("");
					apxsSpectra apxsspectra = new apxsSpectra("Start APXS");
					CallBack cb = new CallBack();
					cb.done();
				
	            }
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
