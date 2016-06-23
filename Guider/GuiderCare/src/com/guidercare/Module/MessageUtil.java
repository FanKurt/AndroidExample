package com.guidercare.Module;

public class MessageUtil {

	public static String insert(long time, String area) {
		return "behavior,"+time+","+area+",0";
	}

	public static String update(long time, int duration ) {
		return time+","+duration;
	}
	
	public static String feedback(String message){
		return message;
	}
	
	public static String updateLocation(Double latitude, Double longitude){
		return "0,0,0,"+latitude+","+longitude;
	}

}
