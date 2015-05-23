package pads;

public class Pads {

	private int status;
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
