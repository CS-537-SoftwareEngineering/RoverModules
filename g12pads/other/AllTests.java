package other;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	PadsTestCase.class, PadsConstraintsTest.class 
	
	})
public class AllTests {
}
