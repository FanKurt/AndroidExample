package com.guidercare.volley;

import java.util.HashMap;

import android.content.Context;
import android.util.Log;

import com.guidercare.Module.BeaconTag;
import com.guidercare.volley.VolleyPOST.OnResponse;

public class VolleyParser {
	private Context context;
	private VolleyPOST volleyPOST;
	private HashMap<String, String> map;

	public VolleyParser(Context context) {
		this.context = context;
	}

	public void sendBehavior(String use_id, long at_time, String place,String in_time) {
		if (map != null) {
			map.clear();
			map = null;
		}
		Log.e("place", ""+place);
		map = new HashMap<String, String>();
		map.put("user_id", use_id);
		map.put("at_time", at_time + "");
		map.put("place", place);
		map.put("in_time", in_time);
		volleyPOST = new VolleyPOST(context, map, BeaconTag.BEHAVIOR_ADD);
		volleyPOST.Queue(new OnResponse() {
			public void response(VolleyPOST POSt, int state, String Data) {
				POSt.close();
				if (state == VolleyPOST.REPONSE_STATE) {
					Log.e("sendBehavior", "" + Data);
				} else {
					Log.e("sendBehavior", "ERROR " + Data);
				}
			}
		});
	}

	public void updateBehavior(String use_id, long at_time, String place,String in_time) {
		if (map != null) {
			map.clear();
			map = null;
		}
		map = new HashMap<String, String>();
		map.put("user_id", use_id);
		map.put("at_time", at_time + "");
		map.put("place", place);
		map.put("in_time", in_time);
		volleyPOST = new VolleyPOST(context, map, BeaconTag.BEHAVIOR_UPDATE);
		volleyPOST.Queue(new OnResponse() {
			public void response(VolleyPOST POSt, int state, String Data) {
				POSt.close();
				if (state == VolleyPOST.REPONSE_STATE) {
					Log.e("updateBehavior", "" + Data);
				} else {
					Log.e("updateBehavior", "ERROR " + Data);
				}
			}
		});
	}
	
	public void sendMeassage(String use_id , String text){
		if (map != null) {
			map.clear();
			map = null;
		}
		map = new HashMap<String, String>();
		map.put("user_id", use_id);
		map.put("text", text);
		volleyPOST = new VolleyPOST(context, map, BeaconTag.MESSAGE_ADD);
		volleyPOST.Queue(new OnResponse() {
			public void response(VolleyPOST POSt, int state, String Data) {
				POSt.close();
				if (state == VolleyPOST.REPONSE_STATE) {
					Log.e("sendMeassage", "" + Data);
				} else {
					Log.e("sendMeassage", "ERROR " + Data);
				}
			}
		});
	}
	
	public void sendLocation(String use_id, Double latitude, Double longitude ){
		if (map != null) {
			map.clear();
			map = null;
		}
		map = new HashMap<String, String>();
		map.put("user_id", use_id);
		map.put("lat", latitude+"");
		map.put("lon", longitude+"");
		volleyPOST = new VolleyPOST(context, map, BeaconTag.LOCATION_ADD);
		volleyPOST.Queue(new OnResponse() {
			public void response(VolleyPOST POSt, int state, String Data) {
				POSt.close();
				if (state == VolleyPOST.REPONSE_STATE) {
					Log.e("sendLocation", "" + Data);
				} else {
					Log.e("sendLocation", "ERROR " + Data);
				}
			}
		});
	}
}
