package server;

import generic.RoverServerRunnable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import calc.PowCalc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import module1.MyClassHere;

public class PowerServer extends RoverServerRunnable{
	

		public PowerServer(int port) throws IOException {
			super(port);
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
		            System.out.println("Server: Message Received from Client - " + message.toUpperCase());
		            //create ObjectOutputStream object
		            ObjectOutputStream oos = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
		            //write object to Socket
		            oos.writeObject("Server says Send request Client - "+message);
		            //close resources
		            ArrayList<Integer> PowerNeed = new ArrayList<Integer>();
		            ArrayList<String> GroupNames = new ArrayList<String>();
		            
		            String myFilePath = "13.json";
		    		
		    		// JSONParser is used to parse the data
		    		JSONParser parser = new JSONParser();
		    		
		    		// notice JSON passes integers as Longs
		    		long myPower = 0;
		    		
		    		
		    		// the double list is passed as its own object
		    		Object myList = null;
		    		
		    		try {
		    			/*Object obj = parser.parse(new FileReader(myFilePath));
		    			JSONObject jsonObject = (JSONObject) obj;
		    			myPower = (long) jsonObject.get("power");
		    			System.out.println(">>>>>>>"+myPower);
		    			*/
		    			 JSONParser jsonParser = new JSONParser();
		    			 JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(myFilePath));
		    			 
		    			 JSONArray lang= (JSONArray) jsonObject.get("info");
		    			 
		    			 /*for(int i=0; i<lang.size(); i++){
		                   System.out.println("The " + i + " element of the array: "+lang.get(i));
		    			 }*/
		    			 
		    			 Iterator i = lang.iterator();
		    			
		    			  while (i.hasNext()) {
		    			    JSONObject innerObj = (JSONObject) i.next();
		    			      //System.out.println("Group-"+ innerObj.get("name")+" needs " + innerObj.get("power"));
		    			      Long z = (Long) innerObj.get("power");
		    			      String GroupName = (String) innerObj.get("name");
		    			      
		    			      //System.out.println("--"+z.intValue());
		    			      GroupNames.add(GroupName);		
		    			      PowerNeed.add(z.intValue());
		    			      
		    			  }
		    			 System.out.println(GroupNames+">>>"+PowerNeed);
		    			PowCalc.calc(PowerNeed,GroupNames);
		    		} catch (ParseException e) {
		    			System.out.println("Parse exception found.");
		    			e.printStackTrace();
		    		}
		            ois.close();
		            oos.close();
		            //getRoverServerSocket().closeSocket();
		            //terminate the server if client sends exit request
		            if(message.equalsIgnoreCase("POW_CALC"))
		            	break;
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
