package g02tel;

import earth.EarthServer;
import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import testmodule.CCD_UI;
import testmodule.Earth_UI;

public class TELMain {

    public static void main(String[] args) {
        
        int port_two = Constants.PORT_TWO;
        int port_thirty = Constants.PORT_THIRTY;
        
        try {
            
            // create a thread for module two
            EarthServer earthServer = new EarthServer(port_thirty);
            TELServer serverTwo = new TELServer(port_two);
            
            
            Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(earthServer);
            Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);
            
            // each server begins listening
            server_1.start();
            server_2.start();
            
            // client two server sending messages to server one
            CCD_UI telClient = new CCD_UI(port_two, null);
            Earth_UI earthClient = new Earth_UI(port_two, null);
            // TestClient telClient = new TestClient(port_two, null); // notice port_earth
            Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(telClient);
            Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(earthClient);
            
            // start the thread which communicates through sockets
            client_2.start();
            client_3.start();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
