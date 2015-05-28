package controlmodule;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import main.CommandSubSystemClass;
import main.GenQueue;
import main.MyStack;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;

import org.json.simple.JSONObject;


public class ControlSystemServer extends RoverServerRunnable {

	public GenQueue<String> queue;
	public GenQueue<String> emergencyCommandsQueue;
	public MyStack stack;
	public Map<String, Integer> portsList;
	public Map<String, Integer> jsonFileList;
	
	
	public ControlSystemServer(int port) throws IOException {
		super(port);
	}
	
	//writes a text file called data.txt which will be read by TELECOMM team
	public void writeFile(JSONObject fileContent,String command) throws IOException
	{
		File file =new File("data.txt");
		 
		//if file doesnt exists, then create it
		if(!file.exists()){
			file.createNewFile();
		}

		//true = append file
		FileWriter fileWritter = new FileWriter(file.getName(),true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--");
	        bufferWritter.newLine();
	        bufferWritter.write("This is "+command+" module's data");
	        bufferWritter.newLine();
	        bufferWritter.write("----------------------------------------------------------------");
	        bufferWritter.newLine();
	        bufferWritter.write(fileContent.toString());
	        bufferWritter.newLine();
	        bufferWritter.close();

        System.out.println("Done");
	}
	
	//reads json file created by the respective modules after execution
	public JSONObject readJsonFile(int fileNo)
	{
		GlobalReader JSONReader = new GlobalReader(fileNo);
		JSONObject thatOtherObject = JSONReader.getJSONObject();
		
		return thatOtherObject;
	}

	public void createServer()
	{
		try {
			
		ModuleTwoServer REMS_server = new ModuleTwoServer(9001);
		Thread REMS_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(REMS_server);
		REMS_thread.start();
		
		ModuleTwoServer RAD_server = new ModuleTwoServer(9003);
		Thread RAD_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(RAD_server);
		RAD_thread.start();
		
		ModuleTwoServer ATT_server = new ModuleTwoServer(9004);
		Thread ATT_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(ATT_server);
		ATT_thread.start();
		
		ModuleTwoServer THRM_server = new ModuleTwoServer(9005);
		Thread THRM_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(THRM_server);
		THRM_thread.start();
		
		ModuleTwoServer MCAM_server = new ModuleTwoServer(9006);
		Thread MCAM_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(MCAM_server);
		MCAM_thread.start();
		
		ModuleTwoServer NCAM_server = new ModuleTwoServer(9007);
		Thread NCAM_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(NCAM_server);
		NCAM_server_thread.start();
		
		ModuleTwoServer CMIN_server = new ModuleTwoServer(9008);
		Thread CMIN_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(CMIN_server);
		CMIN_server_thread.start();		

		ModuleTwoServer MAHLI_server = new ModuleTwoServer(9010);
		Thread MAHLI_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(MAHLI_server);
		MAHLI_server_thread.start();
		
		ModuleTwoServer CCAM_server = new ModuleTwoServer(9011);
		Thread CCAM_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(CCAM_server);
		CCAM_server_thread.start();
		
		ModuleTwoServer PADS_server = new ModuleTwoServer(9012);
		Thread PADS_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(PADS_server);
		PADS_server_thread.start();
		
		ModuleTwoServer POW_server = new ModuleTwoServer(9013);
		Thread POW_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(POW_server);
		POW_server_thread.start();
		
		ModuleTwoServer SAM_server = new ModuleTwoServer(9014);
		Thread SAM_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(SAM_server);
		SAM_server_thread.start();
				
		ModuleTwoServer HCAM_server = new ModuleTwoServer(9015);
		Thread HCAM_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(HCAM_server);
		HCAM_server_thread.start();
		
		ModuleTwoServer ARM_server = new ModuleTwoServer(9016);
		Thread ARM_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(ARM_server);
		ARM_thread.start();		
		
		ModuleTwoServer APXS_server = new ModuleTwoServer(9017);
		Thread APXS_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(APXS_server);
		APXS_server_thread.start();
		
		ModuleTwoServer MBLTY_server = new ModuleTwoServer(9018);
		Thread MBLTY_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(MBLTY_server);
		MBLTY_server_thread.start();
		
		ModuleTwoServer DAN_server = new ModuleTwoServer(9019);
		Thread DAN_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(DAN_server);
		DAN_server_thread.start();
		
		ModuleTwoServer CCDS_server = new ModuleTwoServer(9020);
		Thread CCDS_server_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(CCDS_server);
		CCDS_server_thread.start();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//method communicate is used to connect to the portNo and send the command to respective module
	public void communicate(Integer portNo,String command)
	{
		
				int port_one = 9009;
				int port_two = portNo;  // portNo is the port number of the module to be communicated
				
				try {
						
					ControlSystemClient controlClient = new ControlSystemClient(port_two, null,command); // notice port_two
					Thread controlClientThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(controlClient);
										
					// start the thread which communicates through sockets
					controlClientThread.start();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	
	@Override
	public void run() {
		
		MyClassHere moduleOneClass = new MyClassHere(1);
		CommandSubSystemClass ccds_object = new CommandSubSystemClass();
		
		
		try {

			while (true) {
				
				System.out.println("Control Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Control Server: Message Received from Client - "+ message.toUpperCase());
				
	
				if (message.equalsIgnoreCase("exit"))
					break;
				
				// Command sent by TELECOM module to initiate execution
				else if(message.equalsIgnoreCase("Command list ready")) 
				{
					
					createServer(); // This method will create servers of all modules before hand.
					
					queue = ccds_object.readFile("CommandsList.txt");
					stack = new MyStack(queue.size());
					
					portsList = ccds_object.getPortNumbers();
					jsonFileList=ccds_object.getJsonFileNames();
					//closeAll();					
					
					String command = queue.dequeue();
					
					String[] temp = command.split("_");
					
					stack.push(temp[0]);					
					Integer portNo = portsList.get(temp[0]);
					//System.out.println(temp[0]+" "+portNo);
					
					communicate(portNo,command);             //communicate method is called to create connection between control server and 
															// the module to send the respective command for execution
				}
				else if(message.equalsIgnoreCase("CCDS_DONE")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Control One>");
					System.out.println("Callback message recieved is "+message);
					System.out.println("<Control One>");
					System.out.println("");
					
					String cmd = stack.pop();
					
					int fileNo = jsonFileList.get(cmd);
					JSONObject jsonContent = readJsonFile(fileNo);		
					writeFile(jsonContent,cmd);
					
					//if atleast one command is found in the queue, then it is poped out and sent to the module for execution
					if(queue.size()>0)
					{
					String command = queue.dequeue();
					String[] temp = command.split("_");
						
					stack.push(temp[0]);
					Integer portNo = portsList.get(temp[0]);
					System.out.println(temp[0]+" "+portNo);
					
					communicate(portNo,command);
					}
					
					else
						
						// if no commands are found, then it will send TEL_DONE Command to TELECOM module to read data.txt file
						communicate(9002,"TEL_DONE");
				}
				else if(message.equalsIgnoreCase("CCDS_SendResult")) {
					
					while(queue.hasItems())
					{	
						String command = queue.dequeue();				
					}
					
					emergencyCommandsQueue = ccds_object.readFile("EmergencyCommandsList.txt");
					
					while(emergencyCommandsQueue.hasItems())
					{	
						String command = queue.dequeue();
						String[] temp = command.split("_");
							
						Integer portNo = portsList.get(temp[0]);
						//System.out.println(temp[0]+" "+portNo);
						communicate(portNo,command);
						
					}
					
					moduleOneClass.addOne();
					moduleOneClass.changeBoolean();
					moduleOneClass.changeDouble();
					moduleOneClass.changeLong();
					moduleOneClass.changeString();
					
					// Using MyWriter class our server writes the changed object into a JSON file
					// MyWriter arguments are:
					// MyWriter writerName = new MyWriter(className, Constants.GroupNumber)
					@SuppressWarnings("unused")
					MyWriter JSONWriter = new MyWriter(moduleOneClass, Constants.ONE);
					System.out.println("");
					System.out.println("<Server One>");
					moduleOneClass.printObject();
					System.out.println("<Server One>");
					System.out.println("");
				}
			}
			System.out.println("Server: Shutting down Socket server 1!!");
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
