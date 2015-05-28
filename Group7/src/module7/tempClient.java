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

public class tempClient extends RoverClientRunnable{
	public tempClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}
	@Override
	public void run() {
		try
		{
		    ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    //Jsonwrite code
		    ByteArrayOutputStream baos = null;
			FileOutputStream fileOuputStream = null;
			try {
				BufferedImage originalImage = ImageIO.read(new File(
						"C:/Users/kisha_000/Desktop/my work/minion 1.jpg"));
				baos = new ByteArrayOutputStream();
				ImageIO.write(originalImage, "jpg", baos);
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
				FileWriter writer = null;
				try {
					JSONObject obj=new JSONObject();
					obj.put("temperature", 212);
					writer = new FileWriter("E:/MS/cs537/JSONFiles/main/Image.json");
					writer.write(obj.toString());
				} catch (IOException e) {
					outputToAnotherObject.writeObject("exit");
					e.printStackTrace();
				}
				

				// Object is converted to a JSON String
				String jsonString = gson.toJson(base64Encoded);
				
				
				// Write the file
				try {
					writer.write(jsonString);
				} catch (IOException e) {
					e.printStackTrace();
					outputToAnotherObject.writeObject("exit");
				}
				
				// Close the Writer
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					outputToAnotherObject.writeObject("exit");
				}
				outputToAnotherObject.writeObject("Convert File to JSON");
				

			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					baos.close();
					//fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 

			}
		    //Json code end
			inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            String message = (String) inputFromAnotherObject.readObject();
            System.out.println("Module 7 Client: Message from Server - " + message.toUpperCase());
            
            //close resources
            inputFromAnotherObject.close();
            outputToAnotherObject.close();
            Thread.sleep(5000);
            //outputToAnotherObject.writeObject("exit");
            closeAll();
		    
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
	}
}
