package ccam;

import org.json.simple.JSONObject;

public class Chem_cam {

	boolean POWER_ON = false;
	boolean CWL_ON = false;
	boolean LIBS_WARM = false;
	boolean SET_FOCUS = false;
	boolean LASER_ON = false;
	boolean COOLER_ON = false;
	
	
public boolean PWR_ON(){
		
		
		try {
		    Thread.sleep(2000);                
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		setPWR_ON(true);
		return true;
	}
public boolean COOLER_ON(){
	
	
	

	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	setCOOLER_ON(true);
	return true;
}
public boolean CWL_ON(){
	
	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	
	setCWL_ON(true);
	return true;
	
}
public long SET_FOCUS(){
	
	
	

	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	long distance = 6;
	setSET_FOCUS(true);
	return distance;
}

public boolean LASER_ON(){
	
	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	setLASER_ON(true);
	return true;
}
public boolean LIBS_WARM(){
	
	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	setLIBS_WARM(true);
	return true;
	
}
public JSONObject CCAM_OFF(){
	
	try {
	    Thread.sleep(2000);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	setLIBS_WARM(false);
	setCOOLER_ON(false);
	setCWL_ON(false);
	setLASER_ON(false);
	setPWR_ON(false);
	setSET_FOCUS(false);
	
	JSONObject obj = new JSONObject();
	obj.put("calcium", 200.90);
	obj.put("magnesium", 102.45);
	obj.put("iron", 505.90);
	obj.put("silicon", 300.4);
	obj.put("silver", 12.83);
	JSON.MyWriter.MyWriter(obj, 11);
	
	
	return obj;
}

public void printObject(){
	
	System.out.println("-------------------------------------------");
	System.out.println("POWER_ON = " + this.POWER_ON);
	System.out.println("CWL_WARM = " + this.CWL_ON);
	System.out.println("LIBS_WARM = " + this.LIBS_WARM);
	System.out.println("SET_FOCUS = " + this.SET_FOCUS);
	System.out.println("COOLER_ON = " + this.COOLER_ON);
	System.out.println("LASER_ON = " + this.LASER_ON);
	
	
	System.out.println("--------------------------------------------");
}

public void setPWR_ON(boolean on) {
	POWER_ON = on;
}
public void setCWL_ON(boolean on) {
	CWL_ON = on;
}
public void setSET_FOCUS(boolean on) {
	SET_FOCUS = on;
}
public void setLASER_ON(boolean on) {
	LASER_ON = on;
}
public void setLIBS_WARM(boolean on) {
	LIBS_WARM = on;
}
public void setCOOLER_ON(boolean on) {
	COOLER_ON = on;
}
	
}
