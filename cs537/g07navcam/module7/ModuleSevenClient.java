package module7;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverClientRunnable;
public class ModuleSevenClient extends RoverClientRunnable{
	public ModuleSevenClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(1000);		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    System.out.println("Sending request to Server");
		    outputToAnotherObject.writeObject("Turn_On");
		    outputToAnotherObject.writeObject("Power_available");
		    outputToAnotherObject.writeObject("capture");
		    outputToAnotherObject.writeObject("Turn_Off");
		   
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
	}
}
