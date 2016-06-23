package com.guidercare.MainActivity;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.guidercare.Module.BeaconTag;
import com.guidercare.Module.BluetoothLeScanner;

public class GuiderCareService extends Service {
	private Handler handler = new Handler();
	private BluetoothLeScanner mScanner;
	private BluetoothManager bluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;
	public IBinder onBind(Intent intent) {
		return null;
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}
	public void onCreate() {
		bluetoothManager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
		mScanner = new BluetoothLeScanner(mLeScanCallback ,mBluetoothAdapter);
		mScanner.scanLeDevice(true);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("stop", "stop");
		mScanner.scanLeDevice(false);
		
		if(handler!=null){
			handler.removeCallbacks(null);
			handler = null;
		}
		
	}

	private Runnable runnable = new Runnable() {
		public void run() {
			if(handler!=null)
			handler.postDelayed(runnable , 1000); 
			Log.e("","" + BeaconTag.TOILET_DURATION_TIME);
			if(BeaconTag.TOILET_DURATION_TIME++>=10){
				GuiderCareService.this.stopSelf();
			}
		}
	};
	
	private void launchAcitvity(){
		Intent intent = new Intent (GuiderCareService.this , GuiderCareActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		GuiderCareService.this.startActivity(intent);
	}

	private  BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
		@Override
		public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
			double distance = Double.parseDouble(String.format("%.02f", (calcDistance(rssi)/100)));
			if(device!=null && device.getAddress().equals(BeaconTag.TOILET)){
				Log.e("", "----------------");
				Log.e("BluetoothDevice", ""+device.getName());
				Log.e("BluetoothDevice", ""+device.getAddress());
				Log.e("BluetoothDevice", ""+device.getBondState());
				Log.e("BluetoothDevice", ""+device.getUuids());
				Log.e("rssi",distance +" m");
				Log.e("", "**********");
				Log.e("","" + BeaconTag.TOILET_DURATION_TIME);
				if(distance<BeaconTag.DISTANCE){
					BeaconTag.TOILET_DURATION_TIME++;
					if(BeaconTag.TOILET_DURATION_TIME>=10){
						BeaconTag.TOILET_DURATION_TIME = 0;
						launchAcitvity();
					}
				}else{
					BeaconTag.TOILET_DURATION_TIME = 0;
				}
			}
	
		}
	};
	
	private double calcDistance (int rssi){
		double distance = 1278.89666284 + 98.19763231 * rssi +2.69949458 *Math.pow(rssi, 2)+0.03184348*Math.pow(rssi , 3)+0.00013895*Math.pow(rssi , 4);
		return distance;
	}
	

}
