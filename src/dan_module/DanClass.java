package dan_module;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

public class DanClass {
	
	static boolean DAN_PNG_ON;
	static boolean DAN_DE_ON;
	static double DAN_COLLECT_HYD;
	static int speed;
	
	public DanClass(int i) {
		this.setDAN_PNG_ON(false);
		this.setDAN_DE_ON(false);
		this.setDAN_COLLECT_HYD(0.0);
		this.setSpeed(0);
	}
	
	public static int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		DanClass.speed = speed;
	}

	public boolean isDAN_PNG_ON() {
		return DAN_PNG_ON;
	}

	public void setDAN_PNG_ON(boolean dAN_PNG_ON) {
		DanClass.DAN_PNG_ON = dAN_PNG_ON;
	}

	public boolean isDAN_DE_ON() {
		return DAN_DE_ON;
	}

	public void setDAN_DE_ON(boolean dAN_DE_ON) {
		DanClass.DAN_DE_ON = dAN_DE_ON;
	}
	
	public double getDAN_COLLECT_HYD() {
		return DAN_COLLECT_HYD;
	}

	public void setDAN_COLLECT_HYD(double d) {
		DanClass.DAN_COLLECT_HYD = d;
	}
}
