package main;

public class Pads {

//	status = 0 - Drill/DRT is not functioning
//	status = 1 - Drill/DRT is functioning

	private int drill_status;
	private int drt_status;
	
	private boolean sample_exist;	//true = do get the sample, false = do not get the sample
	private boolean cleaning_completed;		//true = has completed cleaning, false= hasn't completed cleaning
	private int spare_bits = 2;		// Mars rover has maximum 2 number of spare bits in bit box
	
	public Pads() {
		// TODO Auto-generated constructor stub
		this.drill_status = 0;
		this.drt_status = 0;
		this.sample_exist = false;
		this.cleaning_completed = false;
		
	}

	public int getDrill_status() {
		return drill_status;
	}

	public void setDrill_status(int drill_status) {
		this.drill_status = drill_status;
	}

	public int getDrt_status() {
		return drt_status;
	}

	public void setDrt_status(int drt_status) {
		this.drt_status = drt_status;
	}

	public boolean isSample_exist() {
		return sample_exist;
	}

	public void setSample_exist(boolean sample_exist) {
		this.sample_exist = sample_exist;
	}

	public boolean isCleaning_completed() {
		return cleaning_completed;
	}

	public void setCleaning_completed(boolean cleaning_completed) {
		this.cleaning_completed = cleaning_completed;
	}

	public int getSpare_bits() {
		return spare_bits;
	}

	public void setSpare_bits(int spare_bits) {
		this.spare_bits = spare_bits;
	}

}
