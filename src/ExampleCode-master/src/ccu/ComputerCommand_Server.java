package ccu;



import generic.RoverServerRunnable;

import java.io.IOException;
import java.io.ObjectInputStream;

import json.Constants;
import chemin.MyClassHere;
import chemin.Requirement;

public class ComputerCommand_Server extends RoverServerRunnable {
	private static String prefix = "CCU Server:";

	public ComputerCommand_Server(int port) throws IOException {
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
				//ObjectInputStream outstream=new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				//System.out.println(outstream.readObject().toString());
				

				
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}