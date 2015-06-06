package webcamcapture;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.util.*;
import sun.audio.*;

public class test {

public void CaputureImg() {
try {
// Create capture device
CaptureDeviceInfo deviceInfo = CaptureDeviceManager.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
Player player = Manager.createRealizedPlayer(deviceInfo.getLocator());
player.start();

// Wait a few seconds for camera to initialise (otherwise img==null)
Thread.sleep(2500);

// Grab a frame from the capture device
FrameGrabbingControl frameGrabber = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
Buffer buf = frameGrabber.grabFrame();

// Convert frame to an buffered image so it can be processed and saved
Image img = (new BufferToImage((VideoFormat) buf.getFormat()).createImage(buf));
BufferedImage buffImg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
Graphics2D g = buffImg.createGraphics();
g.drawImage(img, null, null);

// Overlay curent time on image
g.setColor(Color.RED);
g.setFont(new Font("Verdana", Font.BOLD, 16));
g.drawString((new Date()).toString(), 10, 25);
System.out.println("Woww.. Captured Image");
// Save image to disk as PNG
ImageIO.write(buffImg, "png", new File("E:/Demo.jpg"));

System.out.println("Saved Image Sucessfuly.");
System.out.println("Goto E disk and See Image. ");

// Stop using webcam
player.close();
player.deallocate();
player.start();
} catch (Exception ex) {
ex.printStackTrace();
}

}

public static void main(String[] args) {
test cam = new test();
cam.CaputureImg();
}
}