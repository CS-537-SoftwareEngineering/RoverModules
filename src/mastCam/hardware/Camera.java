package mastCam.hardware;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Camera {
	private CameraTypes type;
	private String focalLength;
	private String lens;
	private String fov;
	private String resolution;
	private String currentFilter;
	private int numberOfFilters;
	private int photoCount;
	private String capturedImageName;
	private String currentPhotoPath;
	private boolean on;
	
	public Camera(CameraTypes _Type){
		this.type = _Type;
		this.on = false;
		if(_Type == CameraTypes.MastCam100){
			focalLength = "100 mm";
			lens = "f/10";
			fov = "5.1 degrees";
			resolution = "1200 x 1200";
			//currentFilter = 1;
		}
		else if(_Type == CameraTypes.MastCam34){
			focalLength = "34 mm";
			lens = "f/8";
			fov = "15 degrees";
			resolution = "1200 x 1200";
		}
		numberOfFilters = 8;
		photoCount = 0;
	}

	public CameraTypes getType() {
		return type;
	}

	public void setType(CameraTypes type) {
		this.type = type;
	}

	/**
	 * @return the focalLength
	 */
	public String getFocalLength() {
		return focalLength;
	}

	/**
	 * @param focalLength the focalLength to set
	 */
	public void setFocalLength(String focalLength) {
		this.focalLength = focalLength;
	}

	/**
	 * @return the lens
	 */
	public String getLens() {
		return lens;
	}

	/**
	 * @param lens the lens to set
	 */
	public void setLens(String lens) {
		this.lens = lens;
	}

	/**
	 * @return the fov
	 */
	public String getFov() {
		return fov;
	}

	/**
	 * @param fov the fov to set
	 */
	public void setFov(String fov) {
		this.fov = fov;
	}

	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the currentFilter
	 */
	public String getCurrentFilter() {
		return currentFilter;
	}

	/**
	 * @param currentFilter the currentFilter to set
	 */
	public void setCurrentFilter(String currentFilter) {
		this.currentFilter = currentFilter;
	}

	/**
	 * @return the numberOfFilters
	 */
	public int getNumberOfFilters() {
		return numberOfFilters;
	}

	/**
	 * @param numberOfFilters the numberOfFilters to set
	 */
	public void setNumberOfFilters(int numberOfFilters) {
		this.numberOfFilters = numberOfFilters;
	}

	
	/**
	 * @return the photoCount
	 */
	public int getPhotoCount() {
		return photoCount;
	}

	/**
	 * @param photoCount the photoCount to set
	 */
	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	/**
	 * @return the currentPhotoPath
	 */
	public String getCurrentPhotoPath() {
		return currentPhotoPath;
	}

	/**
	 * @param currentPhotoPath the currentPhotoPath to set
	 */
	public void setCurrentPhotoPath(String currentPhotoPath) {
		this.currentPhotoPath = currentPhotoPath;
	}

	/**
	 * @return the on
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * @param on the on to set
	 */
	public void setOn(boolean on) {
		this.on = on;
	}

	public byte[] capturePhoto(String type){
		String filePath = "images/mastcam/still.jpg";
		capturedImageName = "still";
		if(type.equalsIgnoreCase("panorama")){
			capturedImageName = "panorama";
			filePath = "images/mastcam/panorama.jpg";
		}
		File imgFile = new File(filePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imgFile);
			WritableRaster raster = bufferedImage .getRaster();
			DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
			photoCount++;
			return data.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] returnPhoto(String type){
		String filePath = "";
		if(type.equalsIgnoreCase("thumbnail")) filePath = "images/mastcam/"+capturedImageName+"-thumbnail.jpg";
		if(type.equalsIgnoreCase("compressed")) filePath = "images/mastcam/"+capturedImageName+"-compressed.jpg";
		if(type.equalsIgnoreCase("original")) filePath = "images/mastcam/"+capturedImageName+"-original.jpg";
		this.currentPhotoPath = filePath;
		File imgFile = new File(filePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imgFile);
			WritableRaster raster = bufferedImage .getRaster();
			DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
			return data.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void clearMemory(){
		photoCount = 0;
	}
}
