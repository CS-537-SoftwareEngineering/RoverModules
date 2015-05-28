package g02tel;

public class TEL {

    private boolean sendData = false; // Flag to check if data can be sent to
                                      // Earth or not.

    private boolean powerAvailable = true;

    private float signalPower = 7;

    private float signalMaxPower = 30;

    private String message = "Message here";

    private String frequency = "2MHz";

    private String bandwidth = "10 MHz";

    private int modulation = 4;

    private int noise = 3;

    private boolean signalReceived = true;

    public TEL()
    {

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

    public String getFrequency()
    {
        return frequency;
    }

    public void setFrequency( String frequency )
    {
        this.frequency = frequency;
    }

    public String getBandwidth()
    {
        return bandwidth;
    }

    public void setBandwidth( String bandwidth )
    {
        this.bandwidth = bandwidth;
    }

    public int getModulation()
    {
        return modulation;
    }

    public void setModulation( int modulation )
    {
        this.modulation = modulation;
    }

    public int getNoise()
    {
        return noise;
    }

    public void setNoise( int noise )
    {
        this.noise = noise;
    }

    public boolean isSignalReceived()
    {
        return signalReceived;
    }

    public void setSignalReceived( boolean signalReceived )
    {
        this.signalReceived = signalReceived;
    }

    public boolean isSendData()
    {
        return sendData;
    }

    public void setSendData( boolean sendData )
    {
        this.sendData = sendData;
    }

    public void printObject()
    {
        System.out.println( "===========================================" );
        System.out.println( "Frequency Used = " + this.frequency );
        System.out.println( "Bandwidth Used = " + this.bandwidth );
        System.out.println( "Modulation Index = " + this.modulation );
        System.out.println( "Noise = " + this.noise );
        System.out.println( "Signal Strength = " + this.signalPower );
        System.out.println( "Signal Max Power = " + this.signalMaxPower );
        System.out.println( "Power Available = " + this.powerAvailable );
        System.out.println( "Receiving Signal? " + this.signalReceived );
        System.out.println( "Signal Message = " + this.message );
        System.out.println( "===========================================" );
    }
}
