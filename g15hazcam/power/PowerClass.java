package power;

import java.util.Random;


public class PowerClass {
	private int Power;
	
	
	
	PowerClass(int someParameter){
		Random randomGenerator = new Random();
int x=0;

while(x<40)
x=randomGenerator.nextInt(100);

		this.setMyInteger(x);
		
	}

	public int getMyInteger() {
		return Power;
	}

	public void setMyInteger(int myInteger) {
		this.Power = myInteger;
	}

	
	
	public void printObject() {
		System.out.println("===========================================");
		System.out.println("We are POWER Module & We have this Much Amount of Power avalible . ");
		System.out.println("POWER = " + this.Power+" %");
		System.out.println("So you can Start your work. Best Of Luck . Go Ahead.");
		System.out.println("===========================================");
	}


}
