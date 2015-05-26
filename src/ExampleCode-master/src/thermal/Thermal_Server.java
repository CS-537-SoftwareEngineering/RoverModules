package thermal;




import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

import json.Constants;
import chemin.CHEMIN_Client;
import chemin.MyClassHere;
import chemin.Requirement;

public class Thermal_Server extends RoverServerRunnable {
	private static String prefix = "Thermal Server:";

	public Thermal_Server(int port) throws IOException {
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

						if(message.equalsIgnoreCase(Constants.THRM_HEATER_STATUS)){
							//do something
							result = Constants.THRM_HEATER_STATUS;
						}
						sendRequestToChemin(result);
					}
				//}
				
				
		}catch(Exception e){
			System.out.println(e);
		}
	}

	private void sendRequestToChemin(String result) throws UnknownHostException {
		
		CHEMIN_Client clientChemin = new CHEMIN_Client(Constants.PORT_CHEMIN, null,Constants.THERMAL, result); 
		Thread client_chemin = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientChemin);
		client_chemin.start();
	}
}