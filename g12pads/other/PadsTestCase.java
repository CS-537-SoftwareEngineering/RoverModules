package other;

import static org.junit.Assert.*;
import main.Pads;

import org.junit.Test;

public class PadsTestCase {

	
	PadsController controller = new PadsController();
	Pads pads = new Pads();
	
	@Test
	public void Pads(){ 
		assertEquals(pads.getDrill_status(), 0);
		assertEquals(pads.getDrt_status(), 0); 
		assertEquals(pads.isPosition_set(), false); 
		assertEquals(pads.isSample_exist(), false); 
		assertEquals(pads.isCleaning_completed(), false);  
	}

	@Test
	public void testSetPosition() {
		
		boolean status = true;
		pads = controller.action("PADS_SET_POSITION");
		assertEquals(pads.isPosition_set(), status);  
		
	}

	@Test
	public void testStartDrill() {
		int status = 1;
		pads = controller.action("PADS_DRILL_START");
		assertEquals(pads.getDrill_status(), status);   
	}

	@Test
	public void testStopDrill() {
		int status = 0;
		pads = controller.action("PADS_DRILL_STOP");
		assertEquals(pads.getDrill_status(), status);   
	}

	@Test
	public void testLoadBits() {
		int spareBits = 0;
		for(int i=2; i>=-1; i--){
			pads = controller.action("PADS_LOAD_BITS"); 
		}
		assertEquals(pads.getSpare_bits(), spareBits);
	}

	@Test
	public void testReplaceBits() {
		int spareBits = 2;
		pads = controller.action("PADS_REPLACE_BITS"); 
		System.out.println("pads.getSpare_bits()"+pads.getSpare_bits()); 
		assertEquals(pads.getSpare_bits(), spareBits);
	}

	@Test
	public void testStartDrt() {
		int drt_status = 1;
		pads = controller.action("PADS_DRT_START"); 
		assertEquals(pads.getDrt_status(), drt_status);
	}


	@Test
	public void testStopDrt() {
		int drt_status = 0;
		pads = controller.action("PADS_DRT_STOP"); 
		assertEquals(pads.getDrt_status(), drt_status);
	}

}
