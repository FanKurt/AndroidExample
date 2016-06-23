package com.example.iBeacan;
//package com.example.IBeacan;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.util.Log;
//
//import com.guidercare.Module.BeaconTag;
//import com.radiusnetworks.ibeacon.IBeacon;
//import com.radiusnetworks.ibeacon.IBeaconConsumer;
//import com.radiusnetworks.ibeacon.IBeaconManager;
//import com.radiusnetworks.ibeacon.MonitorNotifier;
//import com.radiusnetworks.ibeacon.RangeNotifier;
//import com.radiusnetworks.ibeacon.Region;
//
//public class IBeaconService extends Service implements IBeaconConsumer  {
//	private Handler handler = new Handler();
//	private ArrayList<IBeacon> arrayL = new ArrayList<IBeacon>();
//	private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
//	public IBinder onBind(Intent intent) {
//		return null;
//	}
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		return START_STICKY;
//	}
//	public void onCreate() {
//		iBeaconManager.bind(this);
//	
//	
//	}
//	public void onDestroy() {
//		if(handler!=null){
//			handler.removeCallbacks(null);
//			handler = null;
//		}
//		iBeaconManager.unBind(this);
//	}
//
//
//	private Runnable runnable = new Runnable() {
//		public void run() {
//			handler.postDelayed(runnable , 1000);
//			Log.e("","" + BeaconTag.TIME);
//			if(BeaconTag.TIME++>=10){
//			}
//		}
//	};
//	
//	private void launchAcitvity(){
//		Intent intent = new Intent (IBeaconService.this , IBeaconActivity.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		IBeaconService.this.startActivity(intent);
//	}
//
//	public void onIBeaconServiceConnect() {
//		iBeaconManager.setRangeNotifier(new RangeNotifier() {
//			@Override
//			public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
//				arrayL.clear();
//				arrayL.addAll((ArrayList<IBeacon>) iBeacons);
//				
////				Log.e("","******************************");
////				if (arrayL.get(0).getProximityUuid() != null)
////					Log.e("","UUID: " + arrayL.get(0).getProximityUuid());
////
////				Log.e("","Major: " + arrayL.get(0).getMajor());
////
////				Log.e("",", Minor: " + arrayL.get(0).getMinor());
////
////				Log.e("","Proximity: " + arrayL.get(0).getProximity());
////
////				Log.e("",", Rssi: " + arrayL.get(0).getRssi());
////
////				Log.e("",", TxPower: " + arrayL.get(0).getTxPower());
////
////				Log.e("","" + arrayL.get(0).getAccuracy());
////				Log.e("","******************************");
//				
//				for(IBeacon ibeacan : arrayL){
//					if(ibeacan.getMinor() == 2108 && ibeacan.getMajor() ==2){
//						Log.e("","" + ibeacan.getAccuracy());
//						if(ibeacan.getAccuracy()<=0.1){
//							Log.e("","" + BeaconTag.TIME);
//							if(BeaconTag.TIME++>=10){
//								BeaconTag.TIME = 0;
//								BeaconTag.LONGTIME = true;
//								launchAcitvity();
//							}
//						}else{
//							BeaconTag.TIME =0;
//						}
//						
//					}
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
//				Log.e("BeaconDetactorService", "didDetermineStateForRegion:" + state);
//			}
//
//		});
//
//		try {
//			iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId"
//					, null, null, null));
//			
//			iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	
//	}
//	
//
//}
