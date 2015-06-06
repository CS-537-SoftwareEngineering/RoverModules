package earth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import json.Constants;
import json.GlobalReader;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;

public class EarthServer extends RoverServerRunnable implements ActionListener  {
    
    JFrame frame;
    JTextArea textArea;
    
	public EarthServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		Earth moduleOneClass = new Earth();
		
		frame = new JFrame( "Earth Server Console" );
        frame.setLayout( new BorderLayout(2,0) );
        frame.setBounds(10, 370, 0, 0);
        
        JButton opt4 = new JButton( "Exit program" );
        opt4.setHorizontalTextPosition( SwingConstants.LEFT );
        opt4.addActionListener( this );
        
        textArea = new JTextArea();
        textArea.setEditable( false );
        textArea.setLineWrap( true );
        JScrollPane sp = new JScrollPane(textArea);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setAutoRequestFocus( true );
        
        frame.add( new JLabel( "Console:" ) );
        frame.add( sp );
        // frame.add( textArea );
        frame.setMinimumSize( new Dimension( 650, 350 ) );
        frame.setResizable(false);
        frame.setVisible( true );
		
		try {

			while (true) {
				
			    textArea.append( "\nEarth: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				textArea.append( "\nEarth Server: Message Received from Client - "+ message.toUpperCase());
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Earth Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(moduleOneClass);
				
				outputToAnotherObject.writeObject(jsonString);
				
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				else if(message.equalsIgnoreCase("MODULE_PRINT")) {
					// The server prints out its own object
					System.out.println("");
					System.out.println("<Server One>");
					System.out.println("This is Earth Module");
					System.out.println("<Earth>");
					System.out.println("");
				}
				else if(message.equalsIgnoreCase("RECEIVE_DATA")) {
                    // The server prints out its own object
				    textArea.append( "\n");
				    textArea.append( "\n<Earth Server>");
				    textArea.append( "\nMessage is received from Telecommunications");
                    
				    GlobalReader JSONReader = new GlobalReader(Constants.PORT_NINE);
                    JSONObject ccdObject = JSONReader.getJSONObject();

                    String order = (String) ccdObject.get("data");
				    
				    textArea.append( "\n\n\nReceived message is : \n" + order);
                    
				    textArea.append( "\n<Earth Server End>");
				    textArea.append( "\n");
                }
			}
			textArea.append( "\nServer: Shutting down Socket server for Earth!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error: " + error.getMessage());
		}

	}

    @Override
    public void actionPerformed( ActionEvent e )
    {
        // TODO Auto-generated method stub
        
    }

}
