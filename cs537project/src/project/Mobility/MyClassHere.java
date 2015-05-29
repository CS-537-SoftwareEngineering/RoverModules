package project.Mobility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import project.coding.Mobility;
import project.coding.Mobility.Point;

public class MyClassHere implements Runnable {
	
	int step=0;
	private double distance;
	private double slope;
	private double slope_degree;
 	private double turn_angle;
	private double previous_slope=0;
	private double power=2.07;
	private boolean state=false;
	private double speed=30;
	private double running_time;
	private double total_distance=0;
	private double total_running_time;
	
	Mobility mobility= new Mobility();
	Point currentLocation = mobility.new Point();

	public MyClassHere( ) {
		super();
		this.distance = distance;
		this.slope = slope;
		this.slope_degree = slope_degree;
		this.turn_angle = turn_angle;
		this.power=power;
		this.running_time=running_time;
		this.state=state;
		this.speed=speed;
		this.total_distance=total_distance;
		this.total_running_time=total_running_time;
	}
	
	public double getPrevious_slope() {
		return previous_slope;
	}


	public void setPrevious_slope(double previous_slope) {
		this.previous_slope = previous_slope;
	}


	public Mobility getMobility() {
		return mobility;
	}


	public void setMobility(Mobility mobility) {
		this.mobility = mobility;
	}


	public Point getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(Point p) {
		this.currentLocation = p;
	}
	
 	public double getTotal_distance() {
		return total_distance;
	}


	public void setTotal_distance(double total_distance) {
		this.total_distance = total_distance;
	}


	public double getPower() {
		return power;
	}


	public void setPower(double power) {
		if(power==0)
		{
			System.out.println("POWER IS OFF!!!PLEASE TURN ON POWER.");
		}
		else
		{
			System.out.println("POWER IS ON!!!");
		}
	}


	public boolean isState() {
		return state;
	}


	public void setState(boolean state) 
	{
		if(state==false)
		{
			System.out.println("ROVER IS NOT RUNNING.");
		}
		else
		{
			System.out.println("ROVER IS RUNNING");
		}
	}

	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public double getTotal_running_time() {
		return total_running_time;
	}


	public void setTotal_running_time(double total_running_time) {
		this.total_running_time = total_running_time;
	}


	public double getRunning_time() {
		return running_time;
	}


	public void setRunning_time(double running_time) {
		this.running_time = running_time;
	}


	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		this.slope = slope;
	}
	public double getSlope_degree() {
		return slope_degree;
	}
	public void setSlope_degree(double slope_degree) {
		this.slope_degree = slope_degree;
	}
	public double getTurn_angle() {
		return turn_angle;
	}
	public void setTurn_angle(double turn_angle) {
		this.turn_angle = turn_angle;
	}
	
	
	/*public void getResult(){
		
	
	String coordinates = "coordinates.txt";
	ArrayList<Point> points = new ArrayList<Point>();
	
	

		
		p.readPoints(coordinates,points);
		
		Point prepoint = mobility.new Point();
		state=true;
		for(Point point : points)
		{
			if (prepoint!=null)
			{
				step++;
				 distance= Math.sqrt(Math.pow((point.x-prepoint.x),2)+Math.pow((point.y-prepoint.y),2));
				 running_time = distance/speed;
				 
		 		 slope= (point.y-prepoint.y)/(point.x-prepoint.x);
		 		slope_degree = Math.toDegrees(slope);
		 		
		 		turn_angle= (slope_degree-previous_slope);
		 		total_distance=total_distance+distance;
		 		total_running_time=total_distance/speed;
		 		
//		 		System.out.println("STEP "+step+" :" );
		 		System.out.println("distance="+distance);
		 		System.out.println("slope="+slope);
		 		System.out.println("turn_angle="+turn_angle);
		 		System.out.println("Running Time="+ running_time);
		 		System.out.println("Total distance="+total_distance);
		 		System.out.println("Total Running Time="+total_running_time);
		 		
		 		this.setState(state);
		 		this.setDistance(distance);
		 		this.setSlope(slope);
		 		this.setSlope_degree(slope_degree);
		 		this.setTurn_angle(turn_angle);
		 		this.setTotal_distance(total_distance);
		 		this.setTotal_running_time(total_running_time);
		 		this.setPower(30);
		 		state=false;
		 		this.setState(state);
		 		
		 		
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
			}
			prepoint=point;
		}

	}*/
	public void getResult(double pre_x,double pre_y,double x, double y){
		
		
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		
			
			Mobility mob=new Mobility();
			
			Point p= mobility.new Point();
			p.x=x;
			p.y=y;
			points.add(p);
			
			Point prepoint = mobility.new Point();
			prepoint.x=pre_x;
			prepoint.y=pre_y;
			state=true;
			for(Point point : points)
			{
				if (prepoint!=null)
				{
					step++;
					 distance= Math.sqrt(Math.pow((point.x-prepoint.x),2)+Math.pow((point.y-prepoint.y),2));
					 running_time = distance/speed;
					 
			 		 slope= (point.y-prepoint.y)/(point.x-prepoint.x);
			 		slope_degree = Math.toDegrees(slope);
			 		
			 		turn_angle= (slope_degree-previous_slope);
			 		total_distance=total_distance+distance;
			 		total_running_time=total_distance/speed;
			 		
			 		if(turn_angle<180)
			 			commandperforms("MBLTY_TURNRIGHT");
			 			
			 		else
			 			commandperforms("MBLTY_TURNLEFT");
			 			
//			 		System.out.println("STEP "+step+" :" );
			 		System.out.println("distance="+distance);
			 		System.out.println("slope="+slope);
			 		System.out.println("turn_angle="+turn_angle);
			 		System.out.println("Running Time="+ running_time);
			 		System.out.println("Total distance="+total_distance);
			 		System.out.println("Total Running Time="+total_running_time);
			 		
			 		this.setState(state);
			 		this.setDistance(distance);
			 		this.setSlope(slope);
			 		this.setSlope_degree(slope_degree);
			 		this.setTurn_angle(turn_angle);
			 		this.setTotal_distance(total_distance);
			 		this.setTotal_running_time(total_running_time);
			 		this.setPower(30);
			 		this.setCurrentLocation(point);
			 		String message=null;
	 		 		if (turn_angle==0)
	 		 		{
	 		 			if (message!=null)
	 	 		 			message=message+" Turn "+turn_angle+ " degree "+" Move forward "+distance+ " meters ";
	 	 		 			else
	 	 		 				message=" Turn "+turn_angle+ " degree "+" Move forward "+distance+ " meters ";
	 		 		}
	 		 		if(turn_angle>0&&turn_angle<=180)
	 		 		{
	 		 			mobility.turn_right(turn_angle);
	 		 			mobility.go_forward(distance);
	 		 			if (message!=null)
	 		 			message=message+"Turn right "+turn_angle+ " degree "+" Move forward "+distance+ " meters ";
	 		 			else
	 		 				message=" Turn right "+turn_angle+ " degree "+" Move forward "+distance+ " meters ";
	 		 		}
	 		 		else if (turn_angle>180)
	 		 		{
	 		 			mobility.turn_left(turn_angle);
	 		 			mobility.go_forward(distance);
	 		 			if (message!=null)
	 		 			message=message+" Turn left "+turn_angle + " degree "+" Move forward "+distance+ " meters ";
	 		 			else
	 		 				message=" Turn left "+turn_angle+ " degree "+" Move forward "+distance+ " meters ";
	 		 		}
	 		 		mob.write(message);
			 	/*	state=false;
			 		this.setState(state);
			 		
			 		*/
			 		/*if(turn_angle>0&&turn_angle<=180)
			 		{
			 			mobility.turn_right(turn_angle);
			 			mobility.go_forward(distance);
			 			previous_slope=slope_degree;
			 		}
			 		*/
			 		/*else
			 		{
			 			mobility.turn_left(turn_angle);
			 			mobility.go_forward(distance);
			 			previous_slope=slope_degree;
			 		}*/
			 	

				}
				prepoint=point;
			}

		}



	@Override
	public void run() {
		
		this.setPower(power);
		mobility.turn_left(turn_angle);
		mobility.turn_right(turn_angle);
		mobility.go_forward(distance);
		this.setTotal_distance(total_distance);
		// TODO Auto-generated method stub
		
		
	}
	
	public void commandperforms(String command){

			if(command.equals("MBLTY_POW_ON")){
			
			this.setPower(power);
			this.setState(true);
			
		}else{
			
			//getResult();
			 if((command.equals("MBLTY_TURNRIGHT") && turn_angle>0 && turn_angle<=180)){
			
	 			mobility.turn_right(turn_angle);
	 			mobility.go_forward(distance);
	 			//mobility.go_forward(distance);
	 			previous_slope=slope_degree;
	 			this.setPrevious_slope(previous_slope);
		}
		else if((command.equals("MBLTY_TURNLEFT") && turn_angle<0 && turn_angle>=180)){
			
			mobility.turn_left(turn_angle);
			mobility.go_forward(distance);
 			//mobility.go_forward(distance);
 			previous_slope=slope_degree;
 			this.setPrevious_slope(previous_slope);
	}
		/*else if(command.equals("MBLTY_FWRD")){
			
		//	mobility.turn_left(turn_angle);
 			mobility.go_forward(distance);
 		//	previous_slope=slope_degree;
	}*/
		else if(command.equals("MBLTY_TOTALDISTANCE"))
		{
			this.setTotal_distance(total_distance);
		}
		else if(command.equals("MBLTY_POW_OFF"))
		{
			this.setPower(0);
			this.setState(false);
		}
	}
	}
	
}

