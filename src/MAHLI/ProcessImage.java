package MAHLI;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class ProcessImage {
	@SuppressWarnings("unchecked")
	public Color getImageColor(File imagePath) {
        int color=0;
        int count=0;
		try {
			@SuppressWarnings("rawtypes")
			HashSet hs=new HashSet();
			BufferedImage image = ImageIO.read(imagePath);
				color = image.getRGB(0, 0);
				for (int r = 0; r < image.getHeight(); r += 1) {
				    for (int c = 0; c < image.getWidth(); c += 1) {
				    	hs.add(new Color(image.getRGB(c, r)));
				    	count++;
				       
				        }
				    }
			    System.out.println(count);
				System.out.println(hs.size());
				System.out.println(hs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		

        return new Color(color);
    }

}
