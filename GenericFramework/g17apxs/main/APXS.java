package main;

public class APXS {

private static boolean APXS_ON;
private static double APXS_TEMP;
private static boolean APXS_CON_SENSOR_ON;
private static boolean APXS_XRAY_SPEC_READ;
private static boolean APXS_RESULT_SEND;

public APXS() {
	super();
	// TODO Auto-generated constructor stub
}

public APXS(boolean APXS_ON,double APXS_TEMP,boolean APXS_CON_SENSOR_ON,
		boolean APXS_XRAY_SPEC_ON,boolean APXS_RESULT_SEND){
	this.APXS_ON = APXS_ON;
	this.APXS_TEMP = APXS_TEMP;
	this.APXS_CON_SENSOR_ON = APXS.APXS_CON_SENSOR_ON;
	this.APXS_XRAY_SPEC_READ = APXS_XRAY_SPEC_READ;
	this.APXS_RESULT_SEND = APXS.APXS_RESULT_SEND;
}

public static boolean isAPXS_ON() {
	return APXS_ON;
}

public static void setAPXS_ON(boolean aPXS_ON) {
	APXS_ON = aPXS_ON;
}

public static double getAPXS_TEMP() {
	return APXS_TEMP;
}

public static void setAPXS_TEMP(double aPXS_TEMP) {
	APXS_TEMP = aPXS_TEMP;
}

public static boolean isAPXS_CON_SENSOR_ON() {
	return APXS_CON_SENSOR_ON;
}

public static void setAPXS_CON_SENSOR_ON(boolean aPXS_CON_SENSOR_ON) {
	APXS_CON_SENSOR_ON = aPXS_CON_SENSOR_ON;
}

public static boolean getAPXS_XRAY_SPEC_READ() {
	return APXS_XRAY_SPEC_READ;
}

public static void setAPXS_XRAY_SPEC_READ(boolean aPXS_XRAY_SPEC_READ) {
	APXS_XRAY_SPEC_READ = aPXS_XRAY_SPEC_READ;
}

public static boolean getAPXS_RESULT_SEND() {
	return APXS_RESULT_SEND;
}

public static void setAPXS_RESULT_SEND(boolean aPXS_RESULT_SEND) {
	APXS_RESULT_SEND = aPXS_RESULT_SEND;
}

public void printObject(){
	System.out.println("===========================================");
	System.out.println("APXS_ON = " + this.APXS_ON);
	System.out.println("APXS_TEMP = " + this.APXS_TEMP);
	System.out.println("APXS_CON_SENSOR_ON = " + this.APXS_CON_SENSOR_ON);
	System.out.println("APXS_XRAY_SPEC_READ = " + this.APXS_XRAY_SPEC_READ);
	System.out.println("APXS_RESULT_SEND = " + this.APXS_RESULT_SEND);
	System.out.println("===========================================");
}
}
