package pads;

public class Pads {

	//status = 0 - Drill is not functioning
	//status = 1 - Drill is functioning
	private int status;
	
	//true - PADS successfully able to get sample from the mars.
	//false - PADS is not able to get sample from surface of the mars.
	private boolean sample;
	
	public Pads() {
		super(); 
		this.setStatus(1);
		this.setSample(true); 
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isSample() {
		return sample;
	}
	public void setSample(boolean sample) {
		this.sample = sample;
	}
	
	public void printObject() {
		System.out.println("===========================================");
		System.out.println("status = " + this.status);
		System.out.println("sample = " + this.sample);
		System.out.println("===========================================");
	} 
}
