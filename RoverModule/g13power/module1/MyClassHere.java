package module1;

import java.io.File;

import json.GlobalReader;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

public class MyClassHere {
	private int myPower;
	private int myTime;
	
	private int myInteger;
	private boolean myBoolean;
	private double myDouble;
	private long myLong;
	private String myString;
	
	 public MyClassHere(int someParameter){
		GlobalReader gr = new GlobalReader(someParameter);
		//System.out.println(">>>>>>>>>>>>>>>"+);
		//gr.getJSONObject().get("Time");
		Long myLong = (Long)(gr.getJSONObject().get("Power"));
		//System.out.println(">>>>>>>>>>>>>>>"+myLong);
		this.setMyPower(myLong.intValue());
	}
	
	public int getMyPower() {
		return myPower;
	}

	public void setMyPower(int myPower) {
		this.myPower = myPower;
	}
	
	public int getMyInteger() {
		return myInteger;
	}

	public void setMyInteger(int myInteger) {
		this.myInteger = myInteger;
	}

	public boolean isMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}
	
	public void addOne() {
		this.myInteger += 1;
	}

	public void changeBoolean() {
		if(this.myBoolean == true){
			this.myBoolean = false;
		}
		else {
			this.myBoolean = true;
		}
	}
	
	public void changeDouble() {
		this.myDouble += 50.0;
	}
	
	public void changeLong() {
		this.myLong += 50l;
	}
	
	public void changeString() {
		this.myString = this.myString.concat("After Calculation.");
	}
	
	public void printObject() {
		System.out.println("===========================================");
		System.out.println("myPower = " + this.myPower);
		//System.out.println("myBoolean = " + this.myTime);
		System.out.println("===========================================");
	}

}