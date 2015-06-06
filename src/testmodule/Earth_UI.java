package testmodule;

import generic.RoverClientRunnable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import json.Constants;

public class Earth_UI extends RoverClientRunnable implements ActionListener{
    
    static JFrame frame;
    static JTextArea textArea;
    ObjectOutputStream outputToAnotherObject = null;
    ObjectInputStream inputFromAnotherObject = null;
    
    public Earth_UI(int port, InetAddress host) throws UnknownHostException {
        super(port, host);
    }

    @Override
    public void run() {
        try{
            
            // Thread.sleep(5000);
            
            JLabel imageLbl = null;
            try {
                URL url = new URL("http://www.animatedgif.net/earthglobe/sfworldani_e0.gif");
                Icon myImgIcon = new ImageIcon(url);
                imageLbl = new JLabel(myImgIcon);
                
            } catch (IOException ex) {
                System.out.println("");
            }
            
            frame = new JFrame( "Earth Client" );
            // frame.setContentPane( new JLabel(new ImageIcon(ImageIO.read(new File("D:/test.jpg")))) );
            frame.setLayout( new GridLayout( 3,0 ) );
            frame.setBounds(10, 30, 0, 0);
            frame.getContentPane().setBackground( new Color(255,255,255) );
            
            
            
            JPanel buttons = new JPanel(new FlowLayout());
            buttons.setBackground( new Color(255,255,255) );
            JButton opt1 = new JButton( "GET FREQUENCY" );
            opt1.setHorizontalTextPosition( SwingConstants.LEFT );
            opt1.addActionListener( this );

            JButton opt2 = new JButton( "GET BANDWIDTH" );
            opt2.setHorizontalTextPosition( SwingConstants.LEFT );
            opt2.addActionListener( this );
            
            JButton opt5 = new JButton( "SEND DATA" );
            opt5.setHorizontalTextPosition( SwingConstants.LEFT );
            opt5.addActionListener( this );
            
            JButton opt3 = new JButton( "PRINT INFO" );
            opt3.setHorizontalTextPosition( SwingConstants.LEFT );
            opt3.addActionListener( this );
            
            JButton opt4 = new JButton( "EXIT" );
            opt4.setHorizontalTextPosition( SwingConstants.LEFT );
            opt4.addActionListener( this );
            
            textArea = new JTextArea();
            textArea.append( "Conencted to Telecommunications: Click on any command to begin." );
            textArea.setEditable( false );
            textArea.setLineWrap( true );
            JScrollPane sp = new JScrollPane(textArea);
            
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            frame.setAutoRequestFocus( true );
            
            frame.add(imageLbl, BorderLayout.CENTER);
            //frame.add( new JLabel( "Menu Options:" ) );
            
            buttons.add( opt1 );
            buttons.add( opt2 );
            buttons.add( opt5 );
            buttons.add( opt3 );
            buttons.add( opt4 );
            
            
            frame.add(buttons);
            
            //frame.add( new JLabel( "Console:" ) );
            frame.add( sp );
            // frame.add( textArea );
            frame.setMinimumSize( new Dimension( 650, 340 ) );
            frame.setResizable(false);
            frame.setVisible( true );
        }           
        catch (Exception error) {
            // System.out.println("TEST client: Error:" + error.getMessage());
        }
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        try
        {
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
            
            if( e.getActionCommand().equals( "GET FREQUENCY" ) )
            {
                outputToAnotherObject.writeObject("TEL_GET_FREQUENCY");
                textArea.append( "\nFrequency is : 2M Hz" );
            }
            else if( e.getActionCommand().equals( "GET BANDWIDTH" ) )
            {
                outputToAnotherObject.writeObject("TEL_GET_BANDWIDTH");
                textArea.append( "\nBandwidth is : 10M Hz" );
            }
            else if( e.getActionCommand().equals( "PRINT INFO" ) )
            {
                outputToAnotherObject.writeObject("TEL_PRINT_INFO");
                textArea.append( "\nPrinting Antenna Info.." );
            }
            else if( e.getActionCommand().equals( "SEND DATA" ) )
            {
                outputToAnotherObject.writeObject("TEL_RELAY_TO_ROVER");
                textArea.append( "\nSending Command To Rover" );
            }
            else if( e.getActionCommand().equals( "EXIT" ) )
            {
                outputToAnotherObject.writeObject("exit");
                outputToAnotherObject.close();
                closeAll();
                frame.dispose();
                JOptionPane.showMessageDialog( frame, "Thank you for using this System.",
                    "Thank you!", 1 );
            }
            
            inputFromAnotherObject = new ObjectInputStream(
                getRoverSocket().getSocket().getInputStream() );
            String message = (String) inputFromAnotherObject.readObject();
            textArea.append( "\nMessage from Telecommuncations Server - "
                + message.toUpperCase() );
        }
        catch( IOException e1 )
        {
            e1.printStackTrace();
        }
        catch( ClassNotFoundException e1 )
        {
            e1.printStackTrace();
        }
        
    }
    
    public static void main(String args[]) {
        try
        {
            new Thread(new Earth_UI( Constants.PORT_TWO, null )).start();
        }
        catch( UnknownHostException e )
        {
            e.printStackTrace();
        }
    }

}
