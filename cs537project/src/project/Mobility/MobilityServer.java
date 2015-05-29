package project.Mobility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.simple.JSONObject;
import project.generic.RoverServerRunnable;
import project.json.Constants;
import project.json.GlobalReader;
import project.json.MyWriter;

public class MobilityServer extends RoverServerRunnable {

	public MobilityServer(int port) throws IOException {
		super(port);
	}
	MyClassHere mobilityClass = new MyClassHere();
	@Override
	public void run() {

		try {
			while (true) {
				
				//System.out.println("Mobility Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
			
				String message = (String) inputFromAnotherObject.readObject();
			/*	String[] parts = message.split("-");
				String part1 = parts[0];
				String part2 = parts[1];
			*/	
				System.out.println("MOBILITY Server: Message Received from CONTROL CLIENT - "+ message.toUpperCase());
				
			
				// Our computation is suppose to be done here ..
				/*String content = null ;
				content = message;
				
				File file = new File("coordinates.txt");
				System.out.println("Here");
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();*/
				Thread.sleep(15000);
				System.out.println("Done");
				//System.out.println("Done");
				
				
			
			
		 		ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
		 	/*	Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleOneClass);
				
				outputToAnotherObject.writeObject(jsonString);
			*/	inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				if (message.equalsIgnoreCase("exit"))
					break;
				
				
				else if(message.equalsIgnoreCase("MBLTY_MOVE_X_Y")) {
					
				
					mobilityClass.commandperforms("MBLTY_POW_ON");
					Thread.sleep(5000);			
					mobilityClass.commandperforms("MBLTY_TURNRIGHT");
					Thread.sleep(5000);			
					mobilityClass.commandperforms("MBLTY_TURNLEFT");
					Thread.sleep(5000);			
					mobilityClass.commandperforms("MBLTY_FWRD");
					Thread.sleep(5000);
					mobilityClass.commandperforms("MBLTY_TOTALDISTANCE");
					Thread.sleep(5000);
					mobilityClass.commandperforms("MBLTY_POW_OFF");
					
					mobilityClass.getResult();
						@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(mobilityClass, Constants.TWO);
					System.out.println("");
					System.out.println("<Server MOBILITY>");
					
					mobilityClass.getSlope();
					mobilityClass.getPrevious_slope();
					mobilityClass.getSlope_degree();
					mobilityClass.getTurn_angle();
					mobilityClass.getDistance();
					mobilityClass.getTotal_distance();
					mobilityClass.getRunning_time();
					mobilityClass.getTotal_running_time();
					System.out.println("<Server MOBILITY>");


					System.out.println("");
				}
				
				else if(message.equalsIgnoreCase("MODULE_MOBILITY_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject thatOtherObject = JSONReader.getJSONObject();
					
					// Integers are passed as longs
					Double power = (Double) thatOtherObject.get("power");
			
					System.out.println("");
					System.out.println("<Start> Module MOBILITY Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.ONE + "'s object ");
					System.out.println("Power = " + power);
					
					System.out.println("===========================================");
					System.out.println("<End> Module MOBILITY Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("MOBILITY Server: Error:" + error.getMessage());
		}

	}

}
