package project.Mobility;

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

public class MobilityClient extends RoverClientRunnable{

	public MobilityClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(7000);
	 		
	 		int sum = 0;
	 		  for(int i = 100; i < 105; i++){
		            //write to socket using ObjectOutputStream
		            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		            
		            System.out.println("=================================================");
		            System.out.println("Mobility Client: Sending request to Attitude Server");
		            System.out.println("=================================================");
		            
		            //Dummy data ll be sent to the server from here //
		             
		            
		            String fileName = "C:/Users/Saloni/workspace/cs537Project/reading1.txt";

		            // This will reference one line at a time
		            String line = null;

		            try {
		                // FileReader reads text files in the default encoding.
		                FileReader fileReader = new FileReader(fileName);

		                // Always wrap FileReader in BufferedReader.
		                BufferedReader bufferedReader = new BufferedReader(fileReader);

		                while((line = bufferedReader.readLine()) != null) {
		                 //   System.out.println(line);
		                    
		                    outputToAnotherObject.writeObject(line);
		                }    

		                // Always close files.
		                bufferedReader.close();            
		            }
		 
		            catch(Exception ex) { }
		     
		            //close resources
		            outputToAnotherObject.close();
		            Thread.sleep(7000);
		        }
		        closeAll();
			}	        
	        catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception error) {
				System.out.println("NavCam Client: Error:" + error.getMessage());
			}
			
		}
	            
	       
}
