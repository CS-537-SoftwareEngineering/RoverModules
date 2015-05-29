package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadImage {
	public static void main(String[] args)  {

		String myFilePath = "C:/Users/TEJENDRA/Desktop/CONTROL JSON/Image5.json";

		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			String image = (String) parser.parse(new FileReader(myFilePath));
			byte[] imageInByte = DatatypeConverter.parseBase64Binary(image);
			@SuppressWarnings("resource")
			FileOutputStream fileOuputStream = new FileOutputStream("C:/Users/TEJENDRA/Desktop/CONTROL IMAGE/Hcam_4012.jpg");
			    fileOuputStream.write(imageInByte);	    

			    System.out.println("Conversion completed");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
