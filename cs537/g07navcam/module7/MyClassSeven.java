package module7;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyClassSeven {
	private String image;
	private String power;
	
	public MyClassSeven(){
		//super();
		
	}
	
	public MyClassSeven(double x){
		//super();
		this.power=Double.toString(x);
	}
	
	
	public MyClassSeven(String image) {
		//super();
		this.image = image;
	}
	
	public void power_jason(double x){
		String myFilePath = "C:/Jugal/Courses/CS537/JSON output/Power.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// Instantiate the writer since we're writing to a JSON file.
		FileWriter writer = null;
		try {
			writer = new FileWriter(myFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MyClassSeven s=new MyClassSeven(x);
		// Object is converted to a JSON String
		String jsonString = gson.toJson(s);
		
		// Write the file
		try {
			writer.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Close the Writer
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void convert_to_json(String image)
	{
		ByteArrayOutputStream baos = null;
		FileOutputStream fileOuputStream = null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(
					"C:/Users/Public/Pictures/Sample Pictures/"+image+".jpg"));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
			FileWriter writer = null;
			try {
				writer = new FileWriter("C:/Jugal/Courses/CS537/JSON output/Image.json");
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			MyClassSeven s=new MyClassSeven(base64Encoded);
			// Object is converted to a JSON String
			String jsonString = gson.toJson(s);
			// Write the file
			try {
				writer.write(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Close the Writer
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				baos.close();
				//fileOuputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
