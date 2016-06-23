package com.guidercare.Controller;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import com.example.guidercare.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.guidercare.Dialog.ReminderDialog;
import com.guidercare.Dialog.ReminderDialog.OnDialogListener;
import com.guidercare.Layout.GuiderCareLayout;
import com.guidercare.MainActivity.GuiderCareActivity;
import com.guidercare.Module.BeaconTag;
import com.guidercare.Module.BluetoothLeScanner;
import com.guidercare.Module.MessageUtil;
import com.guidercare.Module.XMPPClient;
import com.guidercare.volley.VolleyParser;

public class GuiderCareController implements ConnectionCallbacks,
		OnConnectionFailedListener,LocationListener {
	private GuiderCareActivity Acty;
	private GoogleApiClient googleApiClient;
	private LocationRequest locationRequest;
	private Location location;
	private int result;
	private BluetoothLeScanner mScanner;
	private BluetoothManager bluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;
	private XMPPClient mXMPPClient;
	private Handler handler;
	private GuiderCareLayout mGuiderCareLayout;
	private ReminderDialog reminderDialog;
	private AlertDialog mAlertDialog;
	private long insertTime;
	private int toilet_time = 0;
	private int bedroom_time = 3000;
	private boolean dialog_boolean = false;
	public boolean default_image = false;
	private TextToSpeech mTextToSpeech;
	private VolleyParser volleyParser;
	public GuiderCareController(Context context,
			GuiderCareLayout mGuiderCareLayout) {
		this.Acty = ((GuiderCareActivity) context);
		this.mGuiderCareLayout = mGuiderCareLayout;
		init();
		initLocation();
	}

	private void initLocation() {
		googleApiClient = new GoogleApiClient.Builder(Acty)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
		result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(Acty);
		if (ConnectionResult.SUCCESS == result) {
			googleApiClient.connect();
		} else {
			// Show appropriate dialog
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(result, Acty,0);
			dialog.show();
		}
	}

	private void init() {
		if (verifySupportBLE()) {
			mXMPPClient = new XMPPClient(Acty);
			volleyParser = new VolleyParser(Acty);
			bluetoothManager = (BluetoothManager) Acty
					.getSystemService(Context.BLUETOOTH_SERVICE);
			mBluetoothAdapter = bluetoothManager.getAdapter();
			mScanner = new BluetoothLeScanner(mLeScanCallback,
					mBluetoothAdapter);
		}
		mTextToSpeech = new TextToSpeech(Acty,
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							mTextToSpeech.setLanguage(Locale.UK);
						}
					}
				});

	}

	private Runnable mRunnable = new Runnable() {
		public void run() {
			mScanner.scanLeDevice(false);

			mScanner.scanLeDevice(true);
			
			if(XMPPClient.region.size()>0 && checkRegion(XMPPClient.region.get(0))){
				mXMPPClient.callReminder();
			}
			if (handler != null) {
				handler.postDelayed(mRunnable, 2000);
			}
			TOILET();
			BEDROOM();
			LIVINGROOM();
			BALCONY();
			Log.e("LIVINGROOM_FIRSTIN", ""+BeaconTag.LIVINGROOM_FIRSTIN);
			Log.e("BEDROOM_FIRSTIN", ""+BeaconTag.BEDROOM_FIRSTIN);
			Log.e("BALCONY_FIRSTIN", ""+BeaconTag.BALCONY_FIRSTIN);
			Log.e("TOILET_FIRSTIN", ""+BeaconTag.TOILET_FIRSTIN);
			if (!BeaconTag.TOILET_FIRSTIN && !BeaconTag.BEDROOM_FIRSTIN
					&& !BeaconTag.LIVINGROOM_FIRSTIN
					&& !BeaconTag.BALCONY_FIRSTIN && XMPPClient.showPic) {
				mGuiderCareLayout.setBackgroundResource(R.drawable.background);
				default_image = false;
			}

		}

		private void BALCONY() {
			if (BeaconTag.BALCONY_DISTANCE != 0
					&& BeaconTag.BALCONY_DISTANCE < BeaconTag.DISTANCE) {
				if (!BeaconTag.BALCONY_FIRSTIN) {
					BeaconTag.BALCONY_DURATION_TIME = 0;
					BeaconTag.BALCONY_FIRSTIN = true;
					// mGuiderCareLayout.getTextView4().setText("BALCONY  ENTER  "
					// + BeaconTag.BALCONY_DISTANCE );
					mTextToSpeech.speak(
							Acty.getResources()
									.getString(R.string.sportContent),
							TextToSpeech.QUEUE_FLUSH, null);
					showDialog(
							Acty.getResources().getString(R.string.sportTitle),
							Acty.getResources()
									.getString(R.string.sportContent));
					mGuiderCareLayout
							.setBackgroundResource(R.drawable.sport_bg);
					String INSERT = MessageUtil.insert(
							System.currentTimeMillis() / 1000, "balcony");
					insertTime = System.currentTimeMillis() / 1000;
					Log.e("", "" + INSERT);
//					mXMPPClient.sendMessage(INSERT);
					volleyParser.sendBehavior("2",System.currentTimeMillis() / 1000,"balcony",""+BeaconTag.BALCONY_DURATION_TIME);
				}
				String UPDATE = MessageUtil.update(insertTime,
						BeaconTag.BALCONY_DURATION_TIME++);
				Log.e("UPDATE", "" + UPDATE);
//				mXMPPClient.sendMessage(UPDATE);
				volleyParser.updateBehavior("2",insertTime,"balcony",""+BeaconTag.BALCONY_DURATION_TIME);
			} else {
				// mGuiderCareLayout.getTextView4().setText("BALCONY  EXIT  " +
				// BeaconTag.BALCONY_DISTANCE );
				BeaconTag.BALCONY_FIRSTIN = false;
				BeaconTag.BALCONY_DURATION_TIME = 0;
			}
		}

		private void LIVINGROOM() {
			if (BeaconTag.LIVINGROOM_DISTANCE != 0
					&& BeaconTag.LIVINGROOM_DISTANCE < BeaconTag.DISTANCE) {
				if (!BeaconTag.LIVINGROOM_FIRSTIN) {
					BeaconTag.LIVINGROOM_DURATION_TIME = 0;
					BeaconTag.LIVINGROOM_FIRSTIN = true;
					// mGuiderCareLayout.getTextView3().setText("LIVINGROOM  ENTER  "
					// + BeaconTag.LIVINGROOM_DISTANCE);
					String INSERT = MessageUtil.insert(
							System.currentTimeMillis() / 1000, "livingroom");
					insertTime = System.currentTimeMillis() / 1000;
					Log.e("", "" + INSERT);
//					mXMPPClient.sendMessage(INSERT);
					volleyParser.sendBehavior("2",System.currentTimeMillis() / 1000,"livingroom",""+BeaconTag.LIVINGROOM_DURATION_TIME);
				}
				String UPDATE = MessageUtil.update(insertTime,BeaconTag.LIVINGROOM_DURATION_TIME++);
				Log.e("UPDATE", "" + UPDATE);
//				mXMPPClient.sendMessage(UPDATE);
				volleyParser.updateBehavior("2",insertTime,"livingroom",""+BeaconTag.LIVINGROOM_DURATION_TIME);
			} else {
				// mGuiderCareLayout.getTextView3().setText("LIVINGROOM  EXIT  "
				// + BeaconTag.LIVINGROOM_DISTANCE);
				BeaconTag.LIVINGROOM_FIRSTIN = false;
				BeaconTag.LIVINGROOM_DURATION_TIME = 0;
			}
		}

		private void BEDROOM() {
			if (BeaconTag.BEDROOM_DISTANCE != 0
					&& BeaconTag.BEDROOM_DISTANCE < BeaconTag.DISTANCE) {
				if (!BeaconTag.BEDROOM_FIRSTIN) {
					BeaconTag.BEDROOM_DURATION_TIME = 0;
					BeaconTag.BEDROOM_FIRSTIN = true;
					// mGuiderCareLayout.getTextView2().setText("BEDROOM  ENTER  "
					// + BeaconTag.BEDROOM_DISTANCE );
					String INSERT = MessageUtil.insert(
							System.currentTimeMillis() / 1000, "bedroom");
					insertTime = System.currentTimeMillis() / 1000;
					Log.e("", "" + INSERT);
//					mXMPPClient.sendMessage(INSERT);
					volleyParser.sendBehavior("2",System.currentTimeMillis() / 1000,"bedroom",""+bedroom_time);
				}
				String UPDATE = MessageUtil.update(insertTime, bedroom_time++);
				Log.e("UPDATE", "" + UPDATE);
				volleyParser.updateBehavior("2",insertTime,"bedroom",""+bedroom_time++);
//				mXMPPClient.sendMessage(UPDATE);
			} else {
				bedroom_time = 1600;
				// mGuiderCareLayout.getTextView2().setText("BEDROOM  EXIT  " +
				// BeaconTag.BEDROOM_DISTANCE );
				BeaconTag.BEDROOM_FIRSTIN = false;
			}
		}

		private void TOILET() {
			if (BeaconTag.TOILET_DISTANCE != 0
					&& BeaconTag.TOILET_DISTANCE < BeaconTag.DISTANCE) {
				if (!BeaconTag.TOILET_FIRSTIN) {
					toilet_time = 0;
					BeaconTag.TOILET_DURATION_TIME = 0;
					BeaconTag.TOILET_FIRSTIN = true;
					// mGuiderCareLayout.getTextView1().setText("TOILET  ENTER  "
					// + BeaconTag.TOILET_DISTANCE);
					String INSERT = MessageUtil.insert(
							System.currentTimeMillis() / 1000, "bathroom");
					insertTime = System.currentTimeMillis() / 1000;
					Log.e("", "" + INSERT);
//					mXMPPClient.sendMessage(INSERT);
					volleyParser.sendBehavior("2",System.currentTimeMillis() / 1000,"bathroom",""+BeaconTag.TOILET_DURATION_TIME++);
				}
				String UPDATE = MessageUtil.update(insertTime,
						BeaconTag.TOILET_DURATION_TIME++);
			
				if (toilet_time++ >= 5 && !dialog_boolean) {
					mGuiderCareLayout
							.setBackgroundResource(R.drawable.toilet_bg);
					dialog_boolean = true;
					toilet_time = 0;
					volleyParser.sendMeassage("2", "bath,Stayed too long.");
//					mXMPPClient.sendMessage(MessageUtil.feedback(
//							"bath,Stayed too long.",
//							System.currentTimeMillis() / 1000));
					mTextToSpeech
							.speak(Acty.getResources().getString(
									R.string.warnContent),
									TextToSpeech.QUEUE_FLUSH, null);
					showDialog(Acty.getResources()
							.getString(R.string.warnTitle), Acty.getResources()
							.getString(R.string.warnContent));

				}
				Log.e("UPDATE", "" + UPDATE);
				volleyParser.updateBehavior("2",insertTime,"bathroom",""+BeaconTag.TOILET_DURATION_TIME++);
//				mXMPPClient.sendMessage(UPDATE);
			} else {
				// mGuiderCareLayout.getTextView1().setText("TOILET  EXIT  " +
				// BeaconTag.TOILET_DISTANCE);
				BeaconTag.TOILET_DURATION_TIME = 0;
				dialog_boolean = false;
				BeaconTag.TOILET_FIRSTIN = false;
			}
		}

	};

	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
		@Override
		public void onLeScan(final BluetoothDevice device, int rssi,
				byte[] scanRecord) {
			//
//			if (device != null && device.getName() != null
//					&& !device.getName().equals("GD-50")) {
//				return;
//			}

			double distance = Double.parseDouble(String.format("%.02f",
					(calcDistance(rssi) / 100)));

			/*
			 * 廁所
			 */
			if (device != null && device.getAddress().equals(BeaconTag.TOILET)) {
				Log.e("TOILET", "" + distance);
				BeaconTag.TOILET_DISTANCE = distance;
				return;
			}
			/*
			 * 臥房
			 */
			if (device != null && device.getAddress().equals(BeaconTag.BEDROOM)) {
				Log.e("BEDROOM", "" + distance);
				BeaconTag.BEDROOM_DISTANCE = distance;
				return;
			}

			/*
			 * 客廳
			 */
			if (device != null
					&& device.getAddress().equals(BeaconTag.LIVINGROOM)) {
				Log.e("LIVINGROOM", "" + distance);
				BeaconTag.LIVINGROOM_DISTANCE = distance;
				return;
			}
			/*
			 * 陽台
			 */
			if (device != null && device.getAddress().equals(BeaconTag.BALCONY)) {
				Log.e("BALCONY", "" + distance);
				BeaconTag.BALCONY_DISTANCE = distance;
				return;
			}
		}
	};

	public void startScan() {
		if (handler == null) {
			handler = new Handler();
		}
		handler.post(mRunnable);
	}

	public void stopScan() {
		mScanner.scanLeDevice(false);
		if (handler != null) {
			handler.removeCallbacks(null);
			handler = null;
		}
	}

	private boolean verifySupportBLE() {
		if (!Acty.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH_LE)) {
			Toast.makeText(Acty, R.string.exceptionMessage, Toast.LENGTH_SHORT)
					.show();
			Acty.finish();
			return false;
		}
		return true;
	}

	private double calcDistance(int rssi) {
		double distance = 1278.89666284 + 98.19763231 * rssi + 2.69949458
				* Math.pow(rssi, 2) + 0.03184348 * Math.pow(rssi, 3)
				+ 0.00013895 * Math.pow(rssi, 4);

		return distance;
	}

	public boolean checkAvailability() {
		if (!Acty.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH_LE)) {
		} else {
			if (((BluetoothManager) Acty
					.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter()
					.isEnabled()) {
				return true;
			}
		}
		return false;
	}

	public BluetoothAdapter getBluetoothAdapter() {
		return mBluetoothAdapter;
	}

	public void showDialog(final String Title, String Content) {
		if (mAlertDialog != null) {
			Log.e("mAlertDialog", "mAlertDialog");
			mAlertDialog.dismiss();
			mAlertDialog = null;
			reminderDialog = null;
		}
		reminderDialog = new ReminderDialog(Acty);
		reminderDialog.setDialogTitle(Title);
		reminderDialog.setDialogMessage(Content);
		reminderDialog.setPositiveButton(Acty.getResources().getString(
				R.string.dialogConfirm));
		reminderDialog.setNegativeButton(Acty.getResources().getString(
				R.string.dialogCancel));
		reminderDialog.saveSetting();
		reminderDialog.setOnDialogListener(new OnDialogListener() {
			public void onClick(int which) {
				String region = selectRegion(Title);
				String message = "";
				if (which == -1) {
					message = MessageUtil.feedback(region + ",I got message.");
					
				} else {
					message = MessageUtil.feedback(region+ ",I ignore message.");
				}
				volleyParser.sendMeassage("2", message);
//				mXMPPClient.sendMessage(message);
				Toast.makeText(Acty,
						Acty.getResources().getString(R.string.dialogFeedBack),
						Toast.LENGTH_SHORT).show();
				reminderDialog = null;
				toilet_time = 0;
			}
		});
		mAlertDialog = reminderDialog.show();
	}

	private String selectRegion(String value) {
		if (value.equals(Acty.getString(R.string.warnTitle))) {
			return "bath";
		} else if (value.equals(Acty.getString(R.string.sportTitle))) {
			return "exc";
		} else {
			return "med";
		}
	}

	public TextToSpeech getTextToSpeech() {
		return mTextToSpeech;
	}

	public GuiderCareLayout getGuiderCareLayout() {
		return mGuiderCareLayout;
	}
	
	public GoogleApiClient getApiClient(){
		return googleApiClient;
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {}
	public void onProviderEnabled(String provider) {}
	public void onProviderDisabled(String provider) {}
	public void onConnectionFailed(ConnectionResult arg0) {}
	public void onConnectionSuspended(int arg0) {}
	
	public void onLocationChanged(Location location) {
		getLocation(location);
	}
	
	public void onConnected(Bundle arg0) {
		location = LocationServices.FusedLocationApi
				.getLastLocation(googleApiClient);
		getLocation(location);
		locationRequest = new LocationRequest();
		locationRequest.setInterval(10000);
		locationRequest.setFastestInterval(5000);
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		LocationServices.FusedLocationApi.requestLocationUpdates(
				googleApiClient, locationRequest, GuiderCareController.this);
	}
	
	private void getLocation(Location location) {
		if (location != null) {
			Double longitude = location.getLongitude();	//取得經度
			Double latitude = location.getLatitude();	//取得緯度
			String curLocation = MessageUtil.updateLocation(latitude, longitude);
//			mXMPPClient.sendMessage(curLocation);
			volleyParser.sendLocation("2",latitude,longitude);
		}else {
			Toast.makeText(Acty, "請開啟定位並重新進入", Toast.LENGTH_LONG).show();
		}
	}
	
	
	private boolean checkRegion(String region ){
		if(region.trim().equals(getCurrentPlace()+"")){
			return true;
		}
		return false;
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
		return "null";
	}
}
