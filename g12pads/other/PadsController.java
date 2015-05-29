package other;

import main.Pads;

public class PadsController implements Runnable{

	
	Thread mythread ;
	Pads pads = new Pads();
	public PadsController() {
		super();
		  
//		mythread = new Thread(this, "my runnable thread");
//	    mythread.start();
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.setPosition();
			Thread.sleep(5000);
			this.startDrill();
			Thread.sleep(5000);
			this.stopDrill();
			Thread.sleep(5000);
			this.startDrt();
			Thread.sleep(5000);
			this.stopDrt();
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPosition() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("PADS_SET_POSITION: Setting up drill position..");  
	}
	public void startDrill() {
		// TODO Auto-generated method stub
		pads.setDrill_status(1); 
		System.out.println("PADS_DRILL_START: Drill has been started.."); 
		
	}
	public void getStatus() {
		// TODO Auto-generated method stub
		if(pads.getDrill_status()==1){
			System.out.println("Drill is currently functioning..");
		}else {
			System.out.println("Drill is sleeping..");
		}
		 
	}
	public void stopDrill() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRILL_STOP: Drill has been stoped.."); 
		pads.setDrill_status(0); 
		pads.setSample_exist(true); 
	}
	public void disEngageBits() {
		// TODO Auto-generated method stub
		
			System.out.println("DisEngaging drill bits.."); 
			
	}
	public void loadBits() {
		// TODO Auto-generated method stub
		if(pads.getSpare_bits()!=0){
			System.out.println("Loading drill bits.."); 
			pads.setSpare_bits(pads.getSpare_bits()-1);
		}else{
			System.out.println("No spare bits in bit box.."); 

		}
	}
	
	public void replaceBits() {
		// TODO Auto-generated method stub
		
		if(pads.getSpare_bits() != 0){
			System.out.println("Replacing bits.."); 
			
		}else{
			System.out.println("No spare bits in bit box.."); 
		}
	}
	public void setDrtMode(String objectToClean) {
		// TODO Auto-generated method stub
		System.out.println(""); 
		
		if(objectToClean.equals("surface")){
			
			System.out.println("PADS_DRT_SET_MODE: Setting up to clean mars surface.."); 
		
		}else{
			
			System.out.println("PADS_DRT_SET_MODE: Setting up to clean observation tray on the rover.."); 
		
		}
		
		
	}
	public void startDrt() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRT_START: DRT has been started.."); 
		pads.setDrt_status(1);
	}
	public void getDrtStatus() {
		// TODO Auto-generated method stub
		if(pads.getDrt_status()==1){
			System.out.println("DRT is currently functioning..");
		}else {
			System.out.println("DRT is sleeping..");
		}
	}
	public void stopDrt() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRT_STOP: DRT has been stopped.."); 
		pads.setCleaning_completed(true);  
		pads.setDrt_status(0);
	}
 
	public Pads action(String command) { 
		// TODO Auto-generated method stub
		
	 if(command.equals("PADS_DRILL_STATUS")){ //Is it currently running??

			 this.getStatus();
			
		}else if(command.equals("PADS_DRT_STATUS")){	//Is it currently running??
			
			 this.getDrtStatus();
			
		}else if(command.equals("PADS_SET_POSITION")){		//set position on target object for drilling
			
			this.setPosition();
			
		}else if(command.equals("PADS_DRILL_START")){	// Start drilling on target 
			
			 this.startDrill();
			
		}else if(command.equals("PADS_DRILL_STOP")){	//Stop drilling
			
			 this.stopDrill();
			
		}else if(command.equals("PADS_DRT_SET_MODE")){		//Decide what to clean
			
			
			//Dirt Removal Tool is used to clean two object
			//1. surface = mars surface 
			//2. tray = observation tray of contact instrument(APXS and Mahli)
			String objectToClean = "surface";
			
			this.setDrtMode(objectToClean);
			
		}else if(command.equals("PADS_DRT_START")){		//Start Dirt Removal Tool (DRT)
			
			this.startDrt();
			
		}else if(command.equals("PADS_DRT_STOP")){	// Stop Dirt Removal Tool (DRT)
			
			this.stopDrt();
			
		}else if(command.equals("PADS_REPLACE_BITS")){	// Replace bit in case of bit worn out
			
			this.replaceBits();
			
		}else if(command.equals("PADS_DIS_ENGAGE_BITS")){	// DisEngage bit in case of bit got stuck in stone
			
			this.disEngageBits();
			
		}else if(command.equals("PADS_LOAD_BITS")){	// Load new bit from bit box
			
			this.loadBits();
			
		}
	return pads; 
		
	}

}
