package pads;

public class PadsController implements Runnable{

	
	boolean status;
	boolean drt_status;
	Thread mythread ;
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
		System.out.println("PADS_DRILL_START: Drill has been started.."); 
		status = true;
	}
	public void getStatus() {
		// TODO Auto-generated method stub
		if(status=true){
			System.out.println("Drill is currently functioning...");
		}else {
			System.out.println("Drill is sleeping..");
		}
		 
	}
	public void stopDrill() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRILL_STOP: Drill has been stoped.."); 
		status = false;
	}
	public void disEngageBits() {
		// TODO Auto-generated method stub
		System.out.println("Disengaging drill bits...."); 
	}
	public void loadBits() {
		// TODO Auto-generated method stub
		System.out.println("Loading bits...."); 
	}
	
	public void replaceBits() {
		// TODO Auto-generated method stub
		System.out.println("Replacing bits.."); 
	}
	public void setDrtMode() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("PADS_DRT_SET_MODE: Setting up DTR mode.."); 
		
	}
	public void startDrt() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRT_START: DRT has been started.."); 
		drt_status = true;
	}
	public void getDrtStatus() {
		// TODO Auto-generated method stub
		if(drt_status=true){
			System.out.println("DRT is currently functioning..");
		}else {
			System.out.println("DRT is sleeping..");
		}
	}
	public void stopDrt() {
		// TODO Auto-generated method stub
		System.out.println("PADS_DRT_STOP: DRT has been stopped.."); 
		 
		drt_status = false;
	}

	public void action(String command) { 
		// TODO Auto-generated method stub
		if(command.equals("PADS_SET_POSITION")){
			
			this.setPosition();
			
		}else if(command.equals("PADS_DRILL_START")){
			
			 this.startDrill();
			
		}else if(command.equals("PADS_DRILL_STOP")){
			
			 this.stopDrill();
			
		}else if(command.equals("PADS_DRT_SET_MODE")){
			
			this.setDrtMode();
			
		}else if(command.equals("PADS_DRT_START")){
			
			this.startDrt();
			
		}else if(command.equals("PADS_DRT_STOP")){
			
			this.stopDrt();
			
		}
		
	}

}
