package pads;

public class Drt {
	
		//In progress
		//status = 0 - DRT(Dust removal tool) is not functioning
		//status = 1 - DRT(Dust removal tool) is functioning
		private int status;
		
		//true - DRT successfully completed cleaning
		//false - PADS is not able to complete cleaning.
		private boolean isCleaned;
		
		
		public Drt(String objectToClean) {
			// TODO Auto-generated constructor stub
			this.setStatus(1);
			this.setCleaned(true); 
		}
		
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		public boolean isCleaned() {
			return isCleaned;
		}
		public void setCleaned(boolean isCleaned) {
			this.isCleaned = isCleaned;
		}
		
		public void printObject() {
			System.out.println("===========================================");
			System.out.println("status = " + this.status);
			System.out.println("sample = " + this.isCleaned);
			System.out.println("===========================================");
		} 
}
