package telecommunications;


import generic.RoverThreadHandler;

import java.io.IOException;

import json.Constants;
import telecommunications.TELClient;
import telecommunications.TELServer;

public class MasterMainCopy {

    public static void main(String[] args) {
        
        //Each module has its own port
        int port_two = Constants.PORT_TWO;
        
        try {
            
            // create a thread for module two
            TELServer serverTwo = new TELServer(port_two);
            Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);
            
            // each server begins listening
            server_2.start();
            
            // The following commands are examples of sending data: 
            // from module 1 client to module 2 server
            // and from module 2 client to module 1 server
            
            /*
            // client one server sending messages to server two
            ModuleOneClient clientOne = new ModuleOneClient(port_two, null); // notice port_two
            Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
            
            // client two server sending messages to server one
            TELClient clientTwo = new TELClient(port_one, null); // notice port_one
            Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientTwo);
            
            // start the thread which communicates through sockets
            client_1.start();
            client_2.start();
            */
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}

