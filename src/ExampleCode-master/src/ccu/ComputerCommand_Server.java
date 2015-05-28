package ccu;




import generic.RoverServerRunnable;

import java.io.IOException;

public class ComputerCommand_Server extends RoverServerRunnable {

	public ComputerCommand_Server(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		while(true){
		try {

				
				
				System.out.println("CCU--> trying to turn on CHEMIN");
				//creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				
				
			
		}catch(Exception e){
			System.out.println(e);
		}
		}
	}
}