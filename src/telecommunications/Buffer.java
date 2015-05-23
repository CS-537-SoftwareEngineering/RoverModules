package telecommunications;

// This is Buffer for storing the messages.

public class Buffer {

    private final int capacity = 100;
    private String data[] = new String[capacity];
    private int front = 0;
    private int top = -1;

    public void add( String pushedElement ) throws Exception
    {
        if( top < capacity - 1 )
        {
            top++;
            data[top] = pushedElement;
        }
        else
        {
            throw new Exception( "Buffer is full" );
        }
    }

    public String transmit()
    {
        // String temp;
        String pop = data[front];
        for( int i = 0; i < data.length - 1; i++ )
        {
            // temp=data[i];
            data[i] = data[i + 1];
        }
        //System.out.println( data[0] );
        return pop;
    }
}