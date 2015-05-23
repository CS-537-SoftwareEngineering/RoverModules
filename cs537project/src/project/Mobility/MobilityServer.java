package project.Mobility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import project.coding.Mobility;
import project.coding.Mobility.Point;
import project.generic.RoverServerRunnable;

public class MobilityServer extends RoverServerRunnable {

	public MobilityServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		try {
			while (true) {
				
				//System.out.println("Mobility Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				String[] parts = message.split("-");
				String part1 = parts[0];
				String part2 = parts[1];
				
				System.out.println("MOBILITY Server: Message Received from CONTROL CLIENT - "+ message.toUpperCase());
				
			
				// Our computation is suppose to be done here ..
				String content = null ;
				content = part2;
				
				File file = new File("C:/Users/Saloni/workspace/cs537Project/reading.txt");
				
				if (!file.exists()) {
					file.createNewFile();
				}
				
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(part2);
					bw.close();
				
				//System.out.println("Done");
				
				
				String coordinates = "C:/Users/Saloni/workspace/cs537Project/reading.txt";
		 		ArrayList<Point> points = new ArrayList<Point>();

		 		Mobility mobility= new Mobility();
		 		Point p = mobility.new Point();
		 		
		 		p.readPoints(coordinates, points);
		 		
		 		Point prepoint = mobility.new Point();
		 		int i=0;
		 	
		 		for(Point point : points)
		 		{
		 			if (prepoint!=null)
		 			{
		 				i++;
		 				double distance= Math.sqrt(Math.pow((point.x-prepoint.x),2)+Math.pow((point.y-prepoint.y),2));
		 		 		double previous_slope=0;
		 		 		double slope= (point.y-prepoint.y)/(point.x-prepoint.x);
		 		 		double slope_degree = Math.toDegrees(slope);
		 		 		double turn_angle= (slope_degree-previous_slope);	
		 		 		
		 		 		if(turn_angle>0&&turn_angle<=180)
		 		 		{
		 		 			mobility.turn_right(turn_angle);
		 		 			mobility.go_forward(distance);
		 		 			previous_slope=slope_degree;
		 		 		}
		 		 		else
		 		 		{
		 		 			mobility.turn_left(turn_angle);
		 		 			mobility.go_forward(distance);
		 		 			previous_slope=slope_degree;
		 		 		}
		 		 		
		 		 		
		 		 		File file1 = new File("C:/Users/Saloni/workspace/cs537Project/reading1.txt");
						
		 		 		if (!file1.exists()) {
							file1.createNewFile();
						}
						
							FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
							BufferedWriter bw1 = new BufferedWriter(fw1);
							bw1.write("Distance = " +distance+ "Slope=" +slope+ "Turn_angle" +turn_angle);
							bw1.close();
		 		 		
		 			}
		 			prepoint=point;
		 		}
		 		
			
		 		ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				
				if (message.equalsIgnoreCase("exit"))
					break;
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
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
