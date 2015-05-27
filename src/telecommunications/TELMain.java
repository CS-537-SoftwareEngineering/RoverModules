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
        System.out.println("5. TEL_CHANNEL");
        System.out.println("6. TEL_FREQUENCY");
        System.out.println("7. TEL_BANDWIDTH");
        System.out.println("8. TEL_MODULATION");
        System.out.println("9. TEL_SIGNAL_RECEIVED");
        System.out.println("10. Exit");
        
        
        UHF t = new UHF();
        Buffer b = new Buffer(10);
        t.printObject();
        
        String data = "This is some data to be transmitted to the earth.";
        
        
        
        
        try {
            b.push( data );
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        

        
    }
}