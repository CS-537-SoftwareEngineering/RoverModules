package telecommunications;


public class Transmitter {
    
    private boolean powerAvailable;
    private float signalPower;
    private float signalMaxPower;
    private String message;
   
    
    public Transmitter() {
        
    }
    
    public boolean isPowerAvailable()
    {
        return powerAvailable;
    }
    
    public void setPowerAvailable( boolean powerAvailable )
    {
        this.powerAvailable = powerAvailable;
    }
    
    public float getSignalPower()
    {
        return signalPower;
    }
    
    public void setSignalPower( float signalPower )
    {
        this.signalPower = signalPower;
    }
    
    public float getSignalMaxPower()
    {
        return signalMaxPower;
    }
    
    public void setSignalMaxPower( float signalMaxPower )
    {
        this.signalMaxPower = signalMaxPower;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage( String message )
    {
        this.message = message;
    }

    public void printObject() {
        System.out.println("===========================================");
        System.out.println("Signal Strength = " + this.signalPower);
        System.out.println("Signal Max Power = " + this.signalMaxPower);
        System.out.println("Message = " + this.message);
        System.out.println("===========================================");
    }
}
