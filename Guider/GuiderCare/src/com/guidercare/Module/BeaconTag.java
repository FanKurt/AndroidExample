package com.guidercare.Module;

import org.jivesoftware.smack.XMPPConnection;

import com.android.volley.RequestQueue;

public class BeaconTag {

	public static final int MINOR_ID = 2;
	public static final int MAJOR_ID = 2108;
	
//	public static boolean LONGTIME = false;
	
	public static double DISTANCE =0.3;
	
	public static int TOILET_DURATION_TIME = 0;
	public static int BEDROOM_DURATION_TIME = 0;
	public static int LIVINGROOM_DURATION_TIME = 0;
	public static int BALCONY_DURATION_TIME = 0;
	
	public static boolean TOILET_FIRSTIN = false;                
	public static boolean BEDROOM_FIRSTIN = false;
	public static boolean LIVINGROOM_FIRSTIN = false;
	public static boolean BALCONY_FIRSTIN = false;
	
	public static  String USER = "test1";
	public static  String PASSWORD = "test1";

	public static final String PORT = "5222";
 
	public static String SERVICE = "";

	public static String HOST = "";

	public static String TO = "";
	
	public static XMPPConnection connection = null;
	
	
	public static final int REQUEST_ENABLE_BT = 1;
	
	
	public static String TOILET = "D0:39:72:C9:FB:6C";
	public static String BEDROOM = "D0:39:72:CA:18:60";
	public static String LIVINGROOM ="D0:39:72:C9:FF:FA";
	public static String BALCONY =  "D0:39:72:CA:06:DE";
	
		
	
//	public static String TOILET = "B4:99:4C:4C:0D:3C";
//	public static String BEDROOM = "B4:99:4C:4C:0C:5B";
//	public static String LIVINGROOM = "B4:99:4C:4C:09:B9";
//	public static String BALCONY = "B4:99:4C:4C:0B:2C";
	
	
	public static double TOILET_DISTANCE =0;
	public static double BEDROOM_DISTANCE =0;
	public static double LIVINGROOM_DISTANCE =0;
	public static double BALCONY_DISTANCE =0;
	
	
	public static RequestQueue queue;
	
	public static String BEHAVIOR_ADD="http://211.23.17.100/smartcarebeacon/public/behavior/create";
	public static String BEHAVIOR_UPDATE="http://211.23.17.100/smartcarebeacon/public/behavior/update";
	public static String BEHAVIOR_READ="http://211.23.17.100/smartcarebeacon/public/behavior/show";
	
	public static String USER_ADD="http://211.23.17.100/smartcarebeacon/public/user/create";
	public static String USER_CHECK="http://211.23.17.100/smartcarebeacon/public/user/check";
	
	public static String SERVICE_ADD="http://211.23.17.100/smartcarebeacon/public/service/create";
	public static String SERVICE_DELETE="http://211.23.17.100/smartcarebeacon/public/service/delete";
	public static String SERVICE_LIST="http://211.23.17.100/smartcarebeacon/public/service/show";
	
	public static String MESSAGE_ADD ="http://211.23.17.100/smartcarebeacon/public/message/create";
	public static String MESSAGE_LIST ="http://211.23.17.100/smartcarebeacon/public/message/show";
	
	public static String LOCATION_ADD="http://211.23.17.100/smartcarebeacon/public/location/create";
	public static String LOCATION_LIST="http://211.23.17.100/smartcarebeacon/public/location/list";
	
}
