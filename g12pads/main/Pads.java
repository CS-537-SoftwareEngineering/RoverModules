package main;

public class Pads {

	//	status = 0 - Drill/DRT is not functioning
	//	status = 1 - Drill/DRT is functioning
	private int drill_status;
	private int drt_status;
	
	//true = position is already set
	//false = position is not set
	private boolean position_set;	
	
	//true = do get the sample
	//false = do not get the sample
	private boolean sample_exist;
	
	//true = has completed cleaning 
	//false= hasn't completed cleaning
	private boolean cleaning_completed;	
	
	// Mars rover has maximum 2 number of spare bits in bit box
	private int spare_bits = 2;		

	public Pads() {
		// TODO Auto-generated constructor stub
		this.drill_status = 0;
		this.drt_status = 0;
		this.position_set = false;
		this.sample_exist = false;
		this.cleaning_completed = false;
		
	}

	public boolean isPosition_set() {
		return position_set;
	}

	public void setPosition_set(boolean position_set) {
		this.position_set = position_set;
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
