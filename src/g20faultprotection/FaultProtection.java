package g20faultprotection;

public class FaultProtection {
	
	//new code, upper and lower bounds
	private double lowerTemp;
	private double upperTemp;
	private double maxTilt;
	private double lowerPower;
	private double upperPower;
	
	FaultProtection(){
		this.setLowerTemp(-40);
		this.setUpperPower(60);
		this.setMaxTilt(30);
		this.setLowerPower(40);
		this.setUpperPower(100);
	}
	
	public double getLowerTemp() {
		return lowerTemp;
	}

	public void setLowerTemp(double lowerTemp) {
		this.lowerTemp = lowerTemp;
	}

	public double getUpperTemp() {
		return upperTemp;
	}

	public void setUpperTemp(double upperTemp) {
		this.upperTemp = upperTemp;
	}

	public double getMaxTilt() {
		return maxTilt;
	}

	public void setMaxTilt(double maxTilt) {
		this.maxTilt = maxTilt;
	}

	public double getLowerPower() {
		return lowerPower;
	}

	public void setLowerPower(double lowerPower) {
		this.lowerPower = lowerPower;
	}

	public double getUpperPower() {
		return upperPower;
	}

	public void setUpperPower(double upperPower) {
		this.upperPower = upperPower;
	}
	
	public void printObject() {
		System.out.println("===========================================");
		System.out.println("lowerTemp = " + this.lowerTemp);
		System.out.println("upperTemp = " + this.upperTemp);
		System.out.println("maxTilt = " + this.maxTilt);
		System.out.println("lowerPower = " + this.lowerPower);
		System.out.println("upperPower = " + this.upperPower);
		System.out.println("===========================================");
	}
	
	public void checkTemp() {
		// do something
	}
	
	public void checkTilt() {
		// do something
	}
	
	public void checkPower() {
		// do something
	}

}

