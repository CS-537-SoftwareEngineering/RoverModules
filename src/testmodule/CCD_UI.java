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

public class CCD_UI extends RoverClientRunnable implements ActionListener{
    
    static JFrame frame;
    static JTextArea textArea;
    ObjectOutputStream outputToAnotherObject = null;
    ObjectInputStream inputFromAnotherObject = null;
    
    public CCD_UI(int port, InetAddress host) throws UnknownHostException {
        super(port, host);
    }

    @Override
    public void run() {
        try{
            
            // Thread.sleep(5000);
            
            JLabel imageLbl = null;
            try {
                URL url = new URL("https://cdn0.iconfinder.com/data/icons/space-4/450/space-buggy-128.png");
                Icon myImgIcon = new ImageIcon(url);
                imageLbl = new JLabel(myImgIcon);
                
            } catch (IOException ex) {
                System.out.println("");
            }
            
            frame = new JFrame( "Computer Command and Data Sub Systems Client" );
            frame.setLayout( new GridLayout(3,0) );
            frame.getContentPane().setBackground( new Color(255,255,255) );
            frame.setBounds(600, 30, 0, 0);
            
            
            JPanel buttons = new JPanel(new FlowLayout());
            buttons.setBackground( new Color(255,255,255) );
            
            JButton opt1 = new JButton( "POWER ON" );
            opt1.setHorizontalTextPosition( SwingConstants.LEFT );
            opt1.addActionListener( this );

            JButton opt3 = new JButton( "PRINT INFO" );
            opt3.setHorizontalTextPosition( SwingConstants.LEFT );
            opt3.addActionListener( this );
            
            JButton opt5 = new JButton( "SEND DATA" );
            opt5.setHorizontalTextPosition( SwingConstants.LEFT );
            opt5.addActionListener( this );

            JButton opt4 = new JButton( "POWER OFF" );
            opt4.setHorizontalTextPosition( SwingConstants.LEFT );
            opt4.addActionListener( this );
            
            textArea = new JTextArea();
            textArea.setEditable( false );
            textArea.setLineWrap( true );
            textArea.setText( "Connected to Telecommunications: Click on any command to begin" );
            textArea.setSize( 500, 600 );
            JScrollPane sp = new JScrollPane(textArea);
            
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            frame.setAutoRequestFocus( true );
            //frame.add( new JLabel( "Menu Options:" ) );
            
            buttons.add( opt1 );
            buttons.add( opt3 );
            buttons.add( opt5 );
            buttons.add( opt4 );
            
            frame.add(imageLbl, BorderLayout.NORTH);
            frame.add(buttons);
            
            //frame.add( new JLabel( "Console:" ) );
            frame.add( sp );
            // frame.add( textArea );
            frame.setMinimumSize( new Dimension( 650, 340 ) );
            frame.setResizable(false);
            frame.setVisible( true );
            
            // The following line of code works, but same code gives error when it is used in actionPerformed() ??
            
            //inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            
            // outputToAnotherObject.writeObject("TEL_POWER_ON");
            //outputToAnotherObject.close();
            //inputFromAnotherObject.close();
            
            
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
            
            if( e.getActionCommand().equals( "POWER ON" ) )
            {
                System.out.println("Power On Test");
                outputToAnotherObject.writeObject("TEL_POWER_ON");
                textArea.append( "\n'Power ON' Command Sent to Telecommunications" );
            }
            else if( e.getActionCommand().equals( "POWER OFF" ) )
            {
                outputToAnotherObject.writeObject("TEL_POWER_OFF");
                textArea.append( "\n'Power OFF' Command Sent to Telecommunications" );
            }
            else if( e.getActionCommand().equals( "PRINT INFO" ) )
            {
                outputToAnotherObject.writeObject("TEL_PRINT_INFO");
                textArea.append( "\nGetting information from Telecommunications" );
            }
            else if( e.getActionCommand().equals( "SEND DATA" ) )
            {
                outputToAnotherObject.writeObject("TEL_RELAY_TO_EARTH");
                textArea.append( "\n'Relay To Earth' command sent to Telecommunications" );
            }
            
            else if( e.getActionCommand().equals( "Exit program" ) )
            {
                outputToAnotherObject.writeObject("exit");
                outputToAnotherObject.close();
                closeAll();
                frame.dispose();
                JOptionPane.showMessageDialog( frame, "Thank you for using this System.",
                    "Thank you!", 1 );
            }
        }
        catch( IOException e1 )
        {
            e1.printStackTrace();
        }
        
    }
    
    public static void main(String args[]) {
        try
        {
            new Thread(new CCD_UI( Constants.PORT_TWO, null )).start();
        }
        catch( UnknownHostException e )
        {
            e.printStackTrace();
        }
    }

}
