package g02tel;

import javax.swing.JTextArea;

public class TEL {

    private boolean sendData = true; // Flag to check if data can be sent to
                                      // Earth or not.

    private boolean powerAvailable = false;
    private float signalPower = 7;
    private float signalMaxPower = 30;
    private String frequency = "2MHz";
    private String bandwidth = "10 MHz";
    private int modulation = 4;
    private int noise = 3;
    private boolean signalReceived = true;
    private String order = "";

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

    public String getOrder()
    {
        return order;
    }

    public void setOrder( String message )
    {
        this.order = message;
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

    public void printObject(JTextArea textArea)
    {
        textArea.append( "\n===========================================" );
        textArea.append( "\nFrequency Used = " + this.frequency );
        textArea.append( "\nBandwidth Used = " + this.bandwidth );
        textArea.append( "\nModulation Index = " + this.modulation );
        textArea.append( "\nNoise = " + this.noise );
        textArea.append( "\nSignal Strength = " + this.signalPower );
        textArea.append( "\nSignal Max Power = " + this.signalMaxPower );
        textArea.append( "\nPower Available = " + this.powerAvailable );
        textArea.append( "\n===========================================" );
    }
}
