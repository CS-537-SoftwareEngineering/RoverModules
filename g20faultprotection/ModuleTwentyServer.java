

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class ModuleTwentyServer extends RoverServerRunnable {

	public ModuleTwentyServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		FaultProtection moduleTwentyClass = new FaultProtection();
		
		try {
			
			while (true) {
				
				System.out.println("Module 20 Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Module 20 Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Module 20 Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleTwentyClass);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server Twenty>");
					System.out.println("This is module " + Constants.TWENTY + "'s object at the start");
					moduleTwentyClass.printObject();
					System.out.println("<Server Twenty>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("MODULE_TWENTY_DO_SOMETHING")) {
					// The server does some example calculations
					
					//moduleTwentyClass.addOne();
					//moduleTwentyClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleTwentyClass, Constants.TWENTY);
					System.out.println("");
					System.out.println("<Server Twenty>");
					moduleTwentyClass.printObject();
					System.out.println("<Server Twenty>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("MODULE_ONE_GET")) {
					// The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.ONE);
					JSONObject thatOtherObject = JSONReader.getJSONObject();

					// Integers are passed as longs
					Long oldLong = (Long) thatOtherObject.get("myInteger");
					
					// Pass the long back into an integer
					Integer myInteger = new Integer(oldLong.intValue());
					
					boolean myBoolean = (boolean) thatOtherObject.get("myBoolean");
					double myDouble = (double) thatOtherObject.get("myDouble");
					long myLong = (long) thatOtherObject.get("myLong");
					String myString = (String) thatOtherObject.get("myString");
					
					System.out.println("");
					System.out.println("<Start> Module 2 Server Receiving <Start>");
					System.out.println("===========================================");
					System.out.println("This is Class " + Constants.ONE + "'s object ");
					System.out.println("myInteger = " + myInteger);
					System.out.println("myBoolean = " + myBoolean);
					System.out.println("myDouble = " + myDouble);
					System.out.println("myLong = " + myLong);
					System.out.println("myString = " + myString);
					System.out.println("===========================================");
					System.out.println("<End> Module 2 Server Receiving <End>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket server 20!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}

}
