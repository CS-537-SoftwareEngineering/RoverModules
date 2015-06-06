package g02tel;

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
import json.MyWriter;

import org.json.simple.JSONObject;

import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

public class TELServer extends RoverServerRunnable implements ActionListener {
    
    JFrame frame;
    JTextArea textArea;
    
	public TELServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		TEL telObj = new TEL();
		Buffer buffer = new Buffer(100000);
		CallBack cb = new CallBack();
		// System.out.println("Buffer length: " + buffer.getData().length);
		
		try {
			
		    frame = new JFrame( "Telecommunications Server Console" );
	        frame.setLayout( new BorderLayout(2,0) );
	        frame.setBounds( 600, 630, 0, 0 );
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
		    
			while (true) {
				
				//System.out.println("Telecommunications Server: Waiting for client request");
				textArea.append( "\nTelecommunications Server: Waiting for client request" );
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				// System.out.println("Telecommunications : Message Received from Client - "+ message.toUpperCase());
				
				if(telObj.isPowerAvailable()) {
				    textArea.append( "\nTelecommunications : Message Received from Client - "+ message.toUpperCase() );
				}
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				outputToAnotherObject.writeObject("Telecommunications Server response Hi Client - " + message);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(telObj);
				
				outputToAnotherObject.writeObject(jsonString);
				
				
				
				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit")) {
				    textArea.append( "\nExit command received");
				    // System.out.println("Exit command received");
				    break;
				}
				else if(message.equalsIgnoreCase("TEL_PRINT_INFO")) {
					
				    new MyWriter(telObj, Constants.TWO);
				    // The server prints out its own object
				    textArea.append( "\n");
				    textArea.append( "\n<Telecommunications>");
				    textArea.append( "\nThis is Telecommunications parameters");
					telObj.printObject(textArea);
					textArea.append( "\n<Telecommunications Server>");
					textArea.append( "\n");
					cb.done();
				}
				else if(message.equalsIgnoreCase("TEL_POWER_ON")) {
					
				    // fetch from Power module if Power is available or not.
				    telObj.setPowerAvailable( true );
				    textArea.append( "\nAntenna power is turned ON");
				    outputToAnotherObject.writeObject("Antenna Power is turned ON");
				    // System.out.println("Antenna power is turned ON");
					cb.done();
				}
				else if(message.equalsIgnoreCase("TEL_POWER_OFF")) {
				    telObj.setPowerAvailable( false );
				    textArea.append( "\nAntenna power is turned OFF");
				    // System.out.println("Antenna power is turned OFF");
				    cb.done();
				}
				else if(message.equalsIgnoreCase("TEL_FREQUENCY")) {
				    outputToAnotherObject.writeObject("The Frequency being used for transmission is " + telObj.getFrequency());
				    textArea.append( "\nThe Frequency being used for transmission is " + telObj.getFrequency()); 
                }
				else if(message.equalsIgnoreCase("TEL_BANDWIDTH")) {
				    
				    outputToAnotherObject.writeObject("The Bandwidth being used for transmission is " + telObj.getBandwidth());
				    textArea.append( "\nThe Bandwidth being used for transmission is " + telObj.getBandwidth());
				    // System.out.println("The Bandwidth being used for transmission is " + telObj.getBandwidth());
                }
				else if(message.equalsIgnoreCase( "TEL_SIGNAL_RECEIVED" )) {
				    
				    // set the parameter for signal received here.
				    if(telObj.isSignalReceived()) {
				        textArea.append( "\nThe signal is received from Earth");
				        // System.out.println("The signal is received from Earth");
				    }
				    else {
				        textArea.append( "\nNo signal is received");
				        // System.out.println("No signal is received");
				    }
				}
				else if(message.equalsIgnoreCase("TEL_RELAY_TO_EARTH")) {
					
				    if(telObj.isPowerAvailable()) {
				    
				    // The server reads another a JSON Object in memory
					GlobalReader JSONReader = new GlobalReader(Constants.NINE);
					JSONObject ccdObject = JSONReader.getJSONObject();
					String data = (String) ccdObject.get("data"); 
					
					
					// System.out.println("");
                    // System.out.println("Telecommunications Server initializing to send message to server");
                    // System.out.println("===========================================");
                    
                    textArea.append( "\n");
                    textArea.append( "\nTelecommunications Server initializing to send message to Earth");
                    textArea.append( "\n===========================================");
                    
                    // System.out.println("data = " + data);
					
	                // If data can be sent to Earth, send it now else put it in buffer.
					if(telObj.isSendData()) {
					    
					    // System.out.println("Message in buffer is " + buffer.getData().length);
					    
					    if(buffer.getTop() >=0 ) {
					        String dataInBuffer = buffer.getDataString();
					        
					        // System.out.println("\nPrevious message present in the buffer is:" + dataInBuffer + "\n");
					        // System.out.println("\nBuffered message is being transmitted now...");
					        textArea.append( "\nMessage in the buffer is " + dataInBuffer);
					        textArea.append( "\nBuffered message is being transmitted now...");
					        
					        while(buffer.getTop()>=0) {
					            // this message has to be sent to Earth module.
					            buffer.transmit();
					        }
					        
					        // send message to Earth
					        
					        TELClient telCLient = new TELClient(Constants.PORT_THIRTY, null);
					        telCLient.setCommandToSend( "RECEIVE_DATA" );
					        Thread tThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(telCLient);
					        tThread.start();
					        
					        
					        // System.out.println("Buffered messages transmission finished..");
					        textArea.append( "\nBuffered messages transmission finished..\n\n");
					    }
					    // System.out.println("Reading the information from CCD's file");
					    textArea.append( "\nReading the information from file\n" );
					    
					    textArea.append( "\nReading Information from file finished : \n\n" + data + "\n\n");
					    textArea.append( "\nAdding the information to buffer..\n");
					    
					    for( int i=0; i<60; i++ ) {
					        textArea.append(". ");
					        Thread.sleep(100);
					    }
					    
					    buffer.push(data);
					    
					    if(buffer.getTop()>=0) {
                            String dataInBuffer = buffer.getDataString();
                            
                            // textArea.append( "\nMessage in the buffer is:" + dataInBuffer);
                            textArea.append( "\nBuffered message is being transmitted now...\n\n");
                            
                            while(buffer.getTop()>=0) {
                                // this message has to be sent to Earth module.
                                buffer.transmit();
                            }
                            
                            for( int i=0; i<95; i++ ) {
                                textArea.append(". ");
                                Thread.sleep(100);
                                
                            }
                            textArea.append( "\nBuffered messages transmission finished..\n");
                            TELClient telCLient = new TELClient(Constants.PORT_THIRTY, null);
                            telCLient.setCommandToSend( "RECEIVE_DATA" );
                            Thread tThread = RoverThreadHandler.getRoverThreadHandler().getNewThread(telCLient);
                            tThread.start();
                        }
					}
					else {
					    textArea.append( "\nCannot transmit message to Earth at this time. Message is stored in buffer.");
					    
					    buffer.push(data);
					}

					textArea.append( "\nSending the data to the Earth finished..");
					
					textArea.append( "\n===========================================");
					textArea.append( "\n<End> Telecommunications server ends communication <End>");
					textArea.append( "\n");
					cb.done();
					
				    }
				    else {
				        textArea.append( "\nCannot transmit message to Earth at this time. No Power is available.");
				        outputToAnotherObject.writeObject("\nCannot transmit message to Earth at this time. No Power is available.");
				    }
				
				}
				else if(message.equalsIgnoreCase("TEL_RELAY_TO_ROVER")) {
                    // The server reads another JSON Object in memory
                    GlobalReader JSONReader = new GlobalReader(Constants.PORT_THIRTY);
                    JSONObject ccdObject = JSONReader.getJSONObject();

                    String order = (String) ccdObject.get("order");
                    
                    if(telObj.isPowerAvailable()) {
                    textArea.append( "\n");
                    textArea.append( "\nTelecommunications Server Received Order from Earth");
                    textArea.append( "\n===========================================");
                    textArea.append( "\nThis is order received from Earth ");
                    textArea.append( "\nOrder = " + order);
                    textArea.append( "\nWriting the information to file..");
                    telObj.setOrder( order );
                    new MyWriter(telObj, Constants.TWO);
                    textArea.append( "\n===========================================");
                    textArea.append( "\n<End> Telecommunications Server Receiving <End>");
                    textArea.append( "\n");
                    cb.done();
                    }
                }
				
				// close resources
                inputFromAnotherObject.close();
                outputToAnotherObject.close();

			}
			
			
			textArea.append( "\nServer: Shutting down Telecommunications Server!!");
			// System.out.println("Server: Shutting down Socket server 2!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception error) {
		    textArea.append( "\nServer: Error: " + error.getMessage());
		}
	}

    @Override
    public void actionPerformed( ActionEvent e )
    {
        
    }
}
