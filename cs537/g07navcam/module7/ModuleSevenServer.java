package module7;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import generic.RoverServerRunnable;
public class ModuleSevenServer extends RoverServerRunnable{

	public ModuleSevenServer(int port) throws IOException {
		super(port);
	}
	@Override
	public void run(){
		MyClassSeven modulesevenobj=new MyClassSeven();

		try{
			while(true){
				System.out.println("Waiting for client....");
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Module 7 Server: Message Received from Client - "+ message.toUpperCase());
				// create ObjectOutputStream object

				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				// write object to Socket
				outputToAnotherObject.writeObject("Module 7 Server response - REQUEST PROCESSED: - " + message);
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if(message.equalsIgnoreCase("turn_on")){
					modulesevenobj.power_jason(4.4);
					//if(message.equalsIgnoreCase("power_available")){
						System.out.println("NAV CAM ON");
						modulesevenobj.power_jason(4.4);
						if(message.equalsIgnoreCase("capture"));{
							System.out.println("Request to take a picture taken from client");
							modulesevenobj.convert_to_json("Desert");
							System.out.println("Picture clicked and stored in JSON ");
						}

						if (message.equalsIgnoreCase("turn_off"))
							break;

					//}else{
						//System.out.println("Power not available");
					//	break;
					//}
				}else{
					System.out.println("Cam not turned ON");
					break;
				}


			}//end of while
			System.out.println("Server: Shutting down Socket server 7!!");
			// close the ServerSocket object
			closeAll();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}

	}
}