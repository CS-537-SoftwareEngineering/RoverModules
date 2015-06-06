package g02tel;

// This is Buffer for storing the messages.

public class Buffer {

    private int capacity = 10000;

    private byte data[] = new byte[capacity];

    private int front = 0;

    private int top = -1;

    public Buffer()
    {
    }

    // Dynamically set the buffer size ( Only for testing ) In practice, this
    // cannot be changed.
    public Buffer( int capacity )
    {
        this.capacity = capacity;
    }

    public byte[] getData()
    {
        return data;
    }

    public String getDataString()
    {
        String str = "";
        for( int j = 0; j < data.length; j++ )
        {
            str += (char) data[j];
        }
        return str;
    }

    public void setData( byte[] data )
    {
        this.data = data;
    }

    public void push( String message ) throws Exception
    {
        byte tmp[] = message.getBytes( "UTF-8" );

        for( int i = 0; i < tmp.length; i++ )
        {

            if( top < capacity - 1 )
            {
                top++;
                data[top] = tmp[i];
            }
            else
            {
                // String str = "";
                // for(int j=0; j<data.length; j++) {
                // str+=(char) data[j];
                // }
                // System.out.println("String msg: " + str);
                throw new Exception(
                    "Message size exceeds the buffer size. Only the first "
                        + capacity + " bytes will be stored." );
            }
        }
    }

    public byte transmit()
    {
        // String temp;
        byte pop = data[front];
        for( int i = 0; i < data.length - 1; i++ )
        {
            // temp=data[i];
            data[i] = data[i + 1];
        }
        // System.out.println( data[0] );
        top--;
        return pop;
    }

    
    public int getTop()
    {
        return top;
    }
    
    
    
}
