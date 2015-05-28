package mastCam.message;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourceModule;
	private String command;
	private boolean turnedOn;
	private String data;
	
	/**
	 * @constructor
	 */
	public Message(String _Command){
		this.command = _Command;
		this.sourceModule = "MAST_CAM";
	}
	
	
	/**
	 * @return the sourceModule
	 */
	public String getSourceModule() {
		return sourceModule;
	}

	/**
	 * @param sourceModule the sourceModule to set
	 */
	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}


	/**
	 * @return the turnedOn
	 */
	public boolean isTurnedOn() {
		return turnedOn;
	}


	/**
	 * @param turnedOn the turnedOn to set
	 */
	public void setTurnedOn(boolean turnedOn) {
		this.turnedOn = turnedOn;
	}


	/**
	 * @return this Object(this->json->string)
	 */
	public String toJsonString(byte[] binaryData){
		data = "";
		if(binaryData != null) data = DatatypeConverter.printBase64Binary(binaryData);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	public void displayImage(byte[] binaryData){
		JFrame frame = new JFrame();
		InputStream inputStream = new ByteArrayInputStream(binaryData);
		BufferedImage img;
		try {
			img = ImageIO.read(inputStream);
			JLabel label = new JLabel(new ImageIcon(img));
			frame.add(label, BorderLayout.CENTER);
			frame.setTitle(command);
			frame.pack();
			frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
