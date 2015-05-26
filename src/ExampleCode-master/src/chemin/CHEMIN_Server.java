package chemin;




import generic.RoverServerRunnable;
import generic.RoverServerSocket;
import generic.RoverThreadHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.MyWriter;

import java.io.File;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ccu.CallBack;

import com.sun.webkit.ThemeClient;

import power.Power_Client;
import telecommunication.Telecommunication_Client;
import thermal.Thermal_Client;

public class CHEMIN_Server extends RoverServerRunnable {

	private static String prefix = "CHEMIN Server:";
	
	public CHEMIN_Server(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				
				System.out.println(prefix+" Waiting for client request");
				
				// creating socket and waiting for client connection or for CCU
				getRoverServerSocket().openSocket();
				
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("-->"+message.toUpperCase());
				
	/*			// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("CHEMIN SERVER - OKIE");*/
				
				//Reading JSON file from ccu Client and writing it to my disk
				/*String jsonread=inputFromAnotherObject.readObject().toString();
				MyWriter jsonwrite=new MyWriter(jsonread,Constants.ROOT_PATH+"savin");
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(jsonread);
				JSONObject ob=(JSONObject) obj;
				System.out.println(ob.containsKey("CHEMIN_TURN_ON"));
				System.out.println("I got yes from CCU and turning on the instrument now");
				*/
				
				//Sending a json file to power unit
		/*		RoverServerSocket outputToPower=new RoverServerSocket(9010);
				System.out.println(outputToPower.getPort());
				ObjectOutputStream otp=(ObjectOutputStream) outputToPower.getSocket().getOutputStream();
				otp.writeObject("I need Power");
				
				*/
				// close resources
			/*	inputFromAnotherObject.close();
				outputToAnotherObject.close();
				*/
				//Trying to print Json file in sequence
				
				boolean bothPowerTermalOn = false;
				
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase(Constants.CMIN_TURN_ON)) {
					System.out.println(prefix+ Constants.CMIN_TURN_ON +" : From CCU");
						sendToPower(Constants.POW_CALC);
						sendToThermal(Constants.THRM_HEATER_STATUS);
				}else if(message.equalsIgnoreCase(Constants.POW_ON)) {
					bothPowerTermalOn = true;
					System.out.println(prefix+ Constants.POW_ON +" : From Power");
				}else if(message.equalsIgnoreCase(Constants.POW_OFF)) {
					//System.out.println(prefix+ Constants.POW_ON +" : From Power");
					// misha  what to do?
				}else if(message.equalsIgnoreCase(Constants.THRM_HEATER_STATUS)) {
					bothPowerTermalOn = true;
					System.out.println(prefix+ Constants.THRM_HEATER_STATUS +" : From Thermal");
					// misha  what to do?
				}
				
				
				finallyClose();
			}
			System.out.println("Server: Shutting down Socket server 1!!");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}


	private void sendToPower(String message) throws UnknownHostException {
		Power_Client powerClient = new Power_Client(Constants.PORT_POWER, null,Constants.CHEMIN , message); 
		Thread client_power = RoverThreadHandler.getRoverThreadHandler().getNewThread(powerClient);
		client_power.start();
	}

	private void sendToThermal(String message) throws UnknownHostException {
		Thermal_Client thermalClient = new Thermal_Client(Constants.PORT_THERMAL, null, Constants.CHEMIN, message); 
		Thread client_thermal = RoverThreadHandler.getRoverThreadHandler().getNewThread(thermalClient);
		client_thermal.start();
	}
	
	private void finallyClose() throws UnknownHostException {
	
		Telecommunication_Client telecommunication_Client = new Telecommunication_Client(Constants.PORT_TELECOMMUNICAION, null, Constants.CHEMIN, Constants.TEL_CHANNEL);
		Thread client_telecom = RoverThreadHandler.getRoverThreadHandler().getNewThread(telecommunication_Client);
		client_telecom.start();
		
		sendToThermal(Constants.THRM_READ_SENSOR_TEMP);
		
		CallBack cb = new CallBack();
		cb.done();
		sendToPower(Constants.POW_CALC);
		
	}
}