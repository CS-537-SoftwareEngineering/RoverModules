package project.Power;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import project.coding.Mobility;
import project.coding.Mobility.Point;
import project.generic.RoverClientRunnable;

public class PowerClient extends RoverClientRunnable{

	public PowerClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(7000);
	 		
	 		int sum = 0;
	 		  for(int i = 100; i < 102; i++){
		            //write to socket using ObjectOutputStream
		            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            
		            System.out.println("=================================================");
		            System.out.println("Power Client: Sending request to Mobility Server");
		            System.out.println("=================================================");
		            
		            //Dummy data ll be sent to the server from here //
		             Thread.sleep(10000);
		            
		            if(i == 101){
		            	outputToAnotherObject.writeObject("exit");
		            }
		            else if(i==100)
		            {
		            	outputToAnotherObject.writeObject("MODULE_MOBILITY_GET");
		            }
		               //close resources
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
				System.out.println("Power Client: Error:" + error.getMessage());
			}
			
		}

	}