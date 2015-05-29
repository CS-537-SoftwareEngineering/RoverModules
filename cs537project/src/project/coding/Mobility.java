package project.coding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mobility {
	String reportFilename="Report.txt";
	
	
	public class Point
	{
		public double x;
		public double y;
		 public Point( double x, double y) {
		      
		        this.x = x;
		        this.y = y;
		    }
		 
		  public void readPoints(String filename, ArrayList<Point> points) {
		        try {
		            String line;
		            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		            while ((line = br.readLine()) != null) {
		                points.add(new Point(line));
		            	
		            }
		            br.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		 public Point(String line) {
		        String[] cols = line.split("\\s+");  
		        this.x = Double.parseDouble(cols[0]);
		        this.y = Double.parseDouble(cols[1]);
		    }
		public Point() {
			// TODO Auto-generated constructor stub
		}
		
	}
	
	
	
	 BufferedWriter writer = null ;
	
	 public void write(String message)
	 {
      try
      {
    	  writer = new BufferedWriter( new FileWriter( reportFilename));
          
          	writer.write(message);
         

      }
      catch ( IOException e)
      {
      }
      finally
      {
          try
          {
              if ( writer != null)
              writer.close( );
          }
          catch ( IOException e)
          {
          }
      	
     
}
      
	 
	 }	
	
	public void turn_right(double angle)
	{
		 System.out.println("Turn_Right" +angle);
	}
	public void turn_left(double angle)
	{
		System.out.println("Turn_Left" +angle);
	}
	public void go_forward(double distance)
	{
		System.out.println("move_Forward " +distance+ " meters");
	}
 	
}
