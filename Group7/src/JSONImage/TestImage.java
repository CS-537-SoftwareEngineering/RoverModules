package JSONImage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestImage {
	public static void main(String[] args) {

		ByteArrayOutputStream baos = null;
		FileOutputStream fileOuputStream = null;
		try {
		    BufferedImage originalImage = ImageIO.read(new File(
			    "C:/Users/kisha_000/Desktop/my work/minion 1.jpg"));
		    baos = new ByteArrayOutputStream();
		    ImageIO.write(originalImage, "jpg", baos);
		    baos.flush();
		    byte[] imageInByte = baos.toByteArray();
		    for(int i=0;i<imageInByte.length;i++)
		    {
		    	System.out.print(imageInByte[i] + " ");
		    }

		    // convert array of bytes into modified file again
		   fileOuputStream = new FileOutputStream(
			    "C:/Users/kisha_000/Desktop/my work/imagex1.jpg");
		    fileOuputStream.write(imageInByte);	    

		    System.out.println("Conversion completed");

		} catch (IOException e) {
		    e.printStackTrace();
		}finally{
		    try {
			baos.close();
			fileOuputStream.close();
		    } catch (IOException e) {
			e.printStackTrace();
		    } 

		}

	    }
}
