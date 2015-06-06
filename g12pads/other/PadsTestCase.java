package other;

import static org.junit.Assert.*;
import main.Pads;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PadsTestCase {

	PadsController controller;
	Pads pads;
	
	@Before
    public void setUp() {
		controller = new PadsController();
		pads = new Pads();
        System.out.println("@Before - setUp");
    }
	
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

	@After
	public void tearDown() {
	    controller = null;
	    pads = null;		
	    System.out.println("@After - tearDown");
	}
}
