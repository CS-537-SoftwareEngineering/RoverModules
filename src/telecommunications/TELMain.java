package telecommunications;

public class TELMain {
    
    public static void main(String args[]) {
        
        System.out.println("==================================================");
        System.out.println("=======  Module 2 Telecommunications  ============");
        System.out.println("List of commands");
        System.out.println("1. TEL_POWER_ON");
        System.out.println("2. TEL_POWER_OFF");
        System.out.println("3. TEL_RELAY_TO_EARTH");
        System.out.println("4. TEL_RELAY_TO_ROVER");
        System.out.println("TEL_CHANNEL");
        System.out.println("TEL_FREQUENCY");
        System.out.println("TEL_BANDWIDTH");
        System.out.println("TEL_MODULATION");
        
        UHF t = new UHF();
        t.printObject();
        
        
        String data = "This is some data to be transmitted to the earth.";
        
        
        Buffer b = new Buffer(10);
        
        try {
            b.push( data );
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        

        
    }
}