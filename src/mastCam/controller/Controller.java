package mastCam.controller;

import mastCam.hardware.*;

public class Controller {
	private Camera mastCam34;
	private Camera mastCam100;
	public Camera currentMastCam;
	
	public Controller(){
		mastCam34 = new Camera(CameraTypes.MastCam34);
		mastCam100 = new Camera(CameraTypes.MastCam100);
		currentMastCam = mastCam100;
	}
	
	public void setCurrentCamera(CameraTypes type){
		if(type == CameraTypes.MastCam34) currentMastCam = mastCam34;
		else if(type == CameraTypes.MastCam100) currentMastCam = mastCam100;
	}
	
	public void switchCamera(){
		if(currentMastCam.getType() == CameraTypes.MastCam34) currentMastCam = mastCam100;
		else if(currentMastCam.getType() == CameraTypes.MastCam100) currentMastCam = mastCam34;
		
	}
	
}
