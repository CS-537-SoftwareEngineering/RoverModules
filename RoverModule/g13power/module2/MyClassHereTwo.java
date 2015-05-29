package module2;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

public class MyClassHereTwo {
	private int myInteger;
	private String myString;
	private int myTime;

	public int getMyTime() {
		return myTime;
	}

	public void setMyTime(int myTime) {
		this.myTime = myTime;
	}

	public int getMyPower() {
		return myPower;
	}

	public void setMyPower(int myPower) {
		this.myPower = myPower;
	}

	private int myPower;
	
	MyClassHereTwo(int someParameter){
		this.setMyInteger(someParameter);
		this.setMyString("This is the second class!!!");
	}

	public int getMyInteger() {
		return myInteger;
	}

	public void setMyInteger(int myInteger) {
		this.myInteger = myInteger;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public void printObject() {
		System.out.println("===========================================");
		System.out.println("myPower = " + this.myPower);
		//System.out.println("myTime = " + this.myTime);
		System.out.println("===========================================");
	}

	public void addOne() {
		this.myInteger += 1;
	}
	
	public void changeString() {
		this.myString = this.myString.concat("After Calculation.");
	}
}