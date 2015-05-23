package usecase;

import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UseCaseServer extends RoverServerRunnable{
	
	List<UseCaseServerChild> childs = new ArrayList<UseCaseServerChild>();
	
	private int port;
	
	private boolean status;

	public UseCaseServer(int port) throws IOException {
		super(port);
		this.port = port;		
	}

	@Override
	public void run() {
		try {
			while(true){				
	            System.out.println("Server: Waiting for client request");	            
				//creating socket and waiting for client connection
	            Socket socket = getRoverServerSocket().openSocket();
	            UseCaseServerChild useCaseServerChild = new UseCaseServerChild();
	            useCaseServerChild.setParent(this);
	            useCaseServerChild.setSocket(socket);
	            childs.add(useCaseServerChild);
	            Thread serverChild = RoverThreadHandler.getRoverThreadHandler().getNewThread(useCaseServerChild);

	            serverChild.start();
	            
	            if(isStatus() == false) break;
	            
	        }
			System.out.println("Server: Shutting down Socket server!!");
	        //close the ServerSocket object
	        closeAll();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	    catch(Exception error){
        	System.out.println("Server: Error:" + error.getMessage());
        }
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
