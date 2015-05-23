package telecommunications;


public class Antenna {
    
    private boolean powerAvailable;
    private String useChannel; // Uplink | Downlink
    private float frequency; // in hertz
    private float bandwidth; // in hertz
    private int noise; // factor
    
    public String getUseChannel()
    {
        return useChannel;
    }
    
    public void setUseChannel( String useChannel )
    {
        this.useChannel = useChannel;
    }
    
    public float getFrequency()
    {
        return frequency;
    }
    
    public void setFrequency( float frequency )
    {
        this.frequency = frequency;
    }
    
    public float getBandwidth()
    {
        return bandwidth;
    }
    
    public void setBandwidth( float bandwidth )
    {
        this.bandwidth = bandwidth;
    }
    
    public int getNoise()
    {
        return noise;
    }
    
    public void setNoise( int noise )
    {
        this.noise = noise;
    }
    
    public boolean isPowerAvailable()
    {
        return powerAvailable;
    }

    public void setPowerAvailable( boolean powerAvailable )
    {
        this.powerAvailable = powerAvailable;
    }
    
}
