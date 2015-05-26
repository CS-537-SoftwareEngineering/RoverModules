package power;



import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

import json.Constants;
import chemin.CHEMIN_Client;
import chemin.MyClassHere;
import chemin.Requirement;

public class Power_Server extends RoverServerRunnable {
	private static String prefix = "Power Server:";

	public Power_Server(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		Requirement moduleOneClassCSU = new Requirement(Constants.TEN);
		MyClassHere moduleOneClass = new MyClassHere(1);

		try {

			while (true) {
				
				System.out.println("CCU Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				ObjectInputStream outstream=new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				String message = outstream.readObject().toString();
				//String messageFrom = outstream.readObject().toString();

				System.out.println(prefix+ message);//+ " : From "+messageFrom);
				
				String result = null ;
				//if(messageFrom.equalsIgnoreCase(Constants.CHEMIN)){

						if(message.equalsIgnoreCase(Constants.POW_CALC)){
							//do something
							result = Constants.POW_ON;
						}else if(message.equalsIgnoreCase(Constants.POW_OFF)){
							// do something
							result = Constants.POW_OFF;
		
						}
						sendRequestToChemin(result);
					}
				//}
				
				
		}catch(Exception e){
			System.out.println(e);
		}
	}

	private void sendRequestToChemin(String result) throws UnknownHostException {
		
		CHEMIN_Client clientChemin = new CHEMIN_Client(Constants.PORT_CHEMIN, null,Constants.POWER, result); 
		Thread client_chemin = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientChemin);
		client_chemin.start();
	}
}