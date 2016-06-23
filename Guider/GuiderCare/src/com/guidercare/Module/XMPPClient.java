package com.guidercare.Module;

import java.util.ArrayList;
import java.util.HashMap;

import org.jivesoftware.smack.packet.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.guidercare.R;
import com.google.android.gms.playlog.internal.LogEvent;
import com.guidercare.MainActivity.GuiderCareActivity;
import com.guidercare.volley.VolleyPOST;
import com.guidercare.volley.VolleyPOST.OnResponse;

public class XMPPClient {
	private Context context;
	private Handler handle;
	private boolean boo = false;
	public static boolean showPic = true;
	public static ArrayList<String> region;
	private HashMap<String, String> map;
	private VolleyPOST volleyPOST;
	public XMPPClient(Context context) {
		this.context = context;
		init();
	}

	private void init() {
//		ConnectionConfiguration connConfig = new ConnectionConfiguration(
//				BeaconTag.SERVICE, Integer.parseInt(BeaconTag.PORT),
//				BeaconTag.SERVICE);
//		BeaconTag.connection = new XMPPConnection(connConfig);
		region = new ArrayList<String>();
		Log.e("XMPPClient", "XMPPClient");
//		try {
//			BeaconTag.connection.connect();
//			BeaconTag.connection.login(BeaconTag.USER, BeaconTag.PASSWORD);
//			Presence presence = new Presence(Presence.Type.available);
//			BeaconTag.connection.sendPacket(presence);
//			setConnection(BeaconTag.connection);
//			Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
//		} catch (XMPPException ex) {
//			Toast.makeText(context, "disConnected", Toast.LENGTH_SHORT).show();
//			Log.e("XMPPClient", ex.toString());
//			setConnection(null);
//		}

		handle = new Handler();
		handle.post(runnable);
	}

	private Runnable runnable = new Runnable() {
		public void run() {
			if(handle!=null){
				handle.postDelayed(runnable, 1000);
			}
		
			if (boo) {
				boo = false;
				GuiderCareActivity.mGuiderCareController.getTextToSpeech()
						.speak(context.getResources().getString(
								R.string.medicineContent),
								TextToSpeech.QUEUE_FLUSH, null);

				GuiderCareActivity.mGuiderCareController.getGuiderCareLayout()
						.setBackgroundResource(R.drawable.medicine_bg);

				GuiderCareActivity.mGuiderCareController.getGuiderCareLayout()
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								showPic = true;
								if (GuiderCareActivity.mGuiderCareController.default_image) {
									GuiderCareActivity.mGuiderCareController.showDialog(
											context.getString(R.string.medicineTitle),
											context.getString(R.string.medicineContent));
								}

							}
						});
			}
			getMessage();
		}
	};
	
	private void getMessage(){
		if (map != null) {
			map.clear();
			map = null;
		}
		map = new HashMap<String, String>();
		map.put("user_id", "2");
		volleyPOST = new VolleyPOST(context, map, BeaconTag.SERVICE_LIST);
		volleyPOST.Queue(new OnResponse() {
			public void response(VolleyPOST POSt, int state, String Data) {
				POSt.close();
				if (state == VolleyPOST.REPONSE_STATE) {
					try {
						JSONObject jsonObject = new JSONObject(Data);
						String start_time =jsonObject.optString("start_time");
						String end_time =jsonObject.optString("end_time");
						String place =jsonObject.optString("place");
						Log.e("start_time", ""+start_time);
						Log.e("end_time", ""+end_time);
						Log.e("place", ""+place);
						Time mTime = new Time();
						mTime.setToNow();
						if (Integer.parseInt(start_time.split(":")[0]) <= mTime.hour
								&& Integer.parseInt(end_time.split(":")[0]) >= mTime.hour) {
							region.clear();
							region.add(place);
						}
					} catch (JSONException e) {
						Log.e("JSONException....", ""+e);
						e.printStackTrace();
					}
				} else {
					Log.e("sendBehavior", "ERROR " + Data);
				}
			}
		});
	}
//	private void setConnection(XMPPConnection connection) {
//		if (connection != null) {
//			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
//			connection.addPacketListener(new PacketListener() {
//				public void processPacket(Packet packet) {
//					Message message = (Message) packet;
//					if (message.getBody() != null) {
//						String fromName = StringUtils.parseBareAddress(message
//								.getFrom());
//						Log.e("XMPPClient", "Got text [" + message.getBody()+ "] from [" + fromName + "]");
//						String[] time = message.getBody().split(",");
//						Time mTime = new Time();
//						mTime.setToNow();
//						if (Integer.parseInt(time[0]) <= mTime.hour
//								&& Integer.parseInt(time[1]) >= mTime.hour) {
//							Log.e("~~~~~~~~~~~~~", "hour  "+time[2]);
//						
//							region.clear();
//							region.add(time[2]);
//							
//						}
//					}
//				}
//			}, filter);
//		}
//	}

	public void sendMessage(String text) {
		String to = BeaconTag.TO;

		Log.e("XMPPClient", "Sending text [" + text + "] to [" + to + "]");
		Message msg = new Message(to, Message.Type.chat);
		msg.setSubject("chat");
		msg.setBody(text);
		BeaconTag.connection.sendPacket(msg);
	}

	private String getCurrentPlace(){
		if(BeaconTag.LIVINGROOM_FIRSTIN){
			return "livingroom";
		}else if(BeaconTag.BEDROOM_FIRSTIN){
			return "bedroom";
		}else if(BeaconTag.BALCONY_FIRSTIN){
			return "balcony";
		}else if(BeaconTag.TOILET_FIRSTIN){
			return "bathroom";
		}
		return "livingroom";
	}
	
	public void callReminder(){
		region.clear();
		boo = true;
		showPic = false;
		GuiderCareActivity.mGuiderCareController.default_image = true;
	}
	
	public void close(){
		if(handle!=null){
			handle.removeCallbacks(null);
			handle = null;
		}
	}
}
