package main;

import java.util.Random;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

public class HazcamClass {
	private int  myInteger; 
	private String IMAGE;
	
	
	public HazcamClass(int someParameter){
		Random randomGenerator = new Random();
		int randomInt=0;
		while(randomInt<10000)
		randomInt = randomGenerator.nextInt(99999);
		this.setMyInteger(randomInt);
		this.setIMAGE("/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMj");
	
	}
	
	
	public int getMyInteger() {
		return myInteger;
	}

	public void setMyInteger(int myInteger) {
		this.myInteger = myInteger;
	}

	public String getIMAGE() {
		return IMAGE;
	}


	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}


	public void printObject() {
		System.out.println("=====================START======================");
		
		System.out.println("=====================END======================");
	}


}
