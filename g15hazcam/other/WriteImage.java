package other;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteImage {
	public static void main(String[] args) {
//WriteImage(){
		ByteArrayOutputStream baos = null;
		
		{
		try {
			BufferedImage originalImage = ImageIO.read(new File(
					"C:/Users/TEJENDRA/Desktop/HAZCAM/1.jpg"));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

		
			String base64Encoded = DatatypeConverter.printBase64Binary(imageInByte);
			FileWriter writer = null;
			try {
				// Write the file
				writer = new FileWriter("C:/Users/TEJENDRA/Desktop/CONTROL JSON/Image5.json");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String jsonString = gson.toJson(base64Encoded);
			
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

	}}
}
