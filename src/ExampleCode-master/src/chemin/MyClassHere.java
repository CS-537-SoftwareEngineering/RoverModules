package chemin;



import json.Constants;
import json.GlobalReader;



public class MyClassHere {
	private Long Power;

	public MyClassHere(int someParameter){
		GlobalReader reader = new GlobalReader(Constants.ROOT_PATH+Constants.EIGHT );
		
		
		this.setPower((Long) reader.getJSONObject().get("Power"))  ;

		
		//System.out.println("power: " +Power);
	}

	


	public Long getPower() {
		return Power;
	}




	public void setPower(Long power) {
		Power = power;
	}




	public void printObject() {
		System.out.println("===========================================");
		System.out.println("power = "  +this.Power);
		//System.out.println("time = " +this.Time);
		
		System.out.println("===========================================");
	}


}