package server;

import generic.RoverClientRunnable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ThermalCommandClient extends RoverClientRunnable{
	
	private String data;

	public ThermalCommandClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ObjectOutputStream oos = null;
		    ObjectInputStream ois = null;
		    Thread.sleep(2000);

            oos = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());

        	oos.writeObject(data);
        	System.out.println("COMMAND TO MODULES: " +data);
            //read the server response message
            ois = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            String message = (String) ois.readObject();

            ois.close();
            oos.close();
            Thread.sleep(1000);

	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	} 
}
