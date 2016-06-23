//package com.example.iBeacan;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.RemoteException;
//import android.util.Log;
//
//import com.guidercare.Layout.GuiderCareLayout;
//import com.guidercare.Module.BeaconTag;
//import com.radiusnetworks.ibeacon.IBeacon;
//import com.radiusnetworks.ibeacon.IBeaconConsumer;
//import com.radiusnetworks.ibeacon.IBeaconManager;
//import com.radiusnetworks.ibeacon.MonitorNotifier;
//import com.radiusnetworks.ibeacon.RangeNotifier;
//import com.radiusnetworks.ibeacon.Region;
//
//
//public class IBeaconActivity extends Activity implements IBeaconConsumer{
//	private Intent intent;
//	private IBeaconManager iBeaconManager = null ;
////	private GuiderCareController mGuiderCareController;
//	private ArrayList<IBeacon> arrayL ;
//	private BluetoothManager bluetoothManager;
//	private BluetoothAdapter mBluetoothAdapter;
//	
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(new GuiderCareLayout(this));
////        mGuiderCareController = new GuiderCareController(this);
//        iBeaconManager = IBeaconManager.getInstanceForApplication(this);
//        bluetoothManager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
//	    mBluetoothAdapter = bluetoothManager.getAdapter();
////		intent =  new Intent(this , GuiderCareService.class);
//        arrayL = new ArrayList<IBeacon>();
////        ReminderDialog reminderDialog = new ReminderDialog(this);
////        reminderDialog.setDialogTitle(this.getResources().getString(R.string.remindTitle));
////        reminderDialog.setDialogMessage("吃藥的時間到了!");
////        reminderDialog.setPositiveButton(this.getResources().getString(R.string.remindConfirm));
////        reminderDialog.setNegativeButton(this.getResources().getString(R.string.remindCancel));
////        reminderDialog.saveSetting();
////        reminderDialog.setOnDialogListener(new OnDialogListener() {
////			public void onClick(int which) {
////				if(which == -1){
////					//確認吃藥
////				}
////				Toast.makeText(IBeaconActivity.this, "回傳照護者...", Toast.LENGTH_SHORT).show();
////			}
////		});
////        reminderDialog.show();
//    }
//	public void onIBeaconServiceConnect() {
//
//	
//		iBeaconManager.setRangeNotifier(new RangeNotifier() {
//			@Override
//			public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
//				arrayL.clear();
//				arrayL.addAll((ArrayList<IBeacon>) iBeacons);
//				Log.e("", ""+iBeacons.size());
//				for(IBeacon ibeacan : arrayL){
////					if(ibeacan.getMinor() == 2108 && ibeacan.getMajor() ==2){
////						Log.e("","" + ibeacan.getAccuracy());
////						if(ibeacan.getAccuracy()<=0.1){
////							Log.e("","" + BeacanTag.TIME);
////							if(BeacanTag.TIME++>=10){
////								BeacanTag.TIME = 0;
////								BeacanTag.LONGTIME = true;
////								mGuiderCareController.verifyLongTime();
////							}
////						}else{
////							BeacanTag.TIME =0;
////						}
////					}
//						
//					Log.e("","=======================================");
//					Log.e("","Major: " + ibeacan.getMajor());
//
//					Log.e("",", Minor: " + ibeacan.getMinor());
//
//					Log.e("","" +ibeacan.getAccuracy());
//				
//				}
//			}
//
//		});
//
//		iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
//			@Override
//			public void didEnterRegion(Region region) {
//				Log.e("BeaconDetactorService", "didEnterRegion");
//			}
//
//			@Override
//			public void didExitRegion(Region region) {
//				Log.e("BeaconDetactorService", "didExitRegion");
//			}
//
//			@Override
//			public void didDetermineStateForRegion(int state, Region region) {
//				Log.e("BeaconDetactorService", "didDetermineStateForRegion  "+state);
//			}
//
//		});
//
//		try {
//			iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId"
//					, null, null, null));
//			
//			iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null
//					, null, null));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//	
//	
//	}
//
//	protected void onResume() {
//		super.onResume();
//		if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, BeaconTag.REQUEST_ENABLE_BT);
//        }
//	}
//
//	protected void onStart() {
//		super.onStart();
//		if (IBeaconManager.getInstanceForApplication(this).checkAvailability()){
//			iBeaconManager.bind(this);
////			this.stopService(intent);
//		}
//	
//		
//	}
//
//	protected void onStop() {
//		super.onStop();
//		if (IBeaconManager.getInstanceForApplication(this).checkAvailability()){
//			iBeaconManager.unBind(this);
////		    this.startService(intent);
//		}
//	}
//
//
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == BeaconTag.REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
//            finish();
//            return;
//        }else{
//        	onStart();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//    
//	
//}
