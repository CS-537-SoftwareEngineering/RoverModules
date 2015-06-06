package other;

import static org.junit.Assert.*;
import main.Pads;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PadsConstraintsTest {

	PadsController controller;
	Pads pads;
	
	@Before
    public void setUp() {
		controller = new PadsController();
		pads = new Pads();
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

	@After
	public void tearDown() {
	    controller = null;
	    pads = null;		
	}
}
