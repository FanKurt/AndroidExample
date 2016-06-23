//package com.guidercare.Adapter;
//import java.util.ArrayList;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import com.radiusnetworks.ibeacon.IBeacon;
//
//public abstract class BeaconAdapter extends BaseAdapter {
//	private ArrayList<IBeacon> arrayL ;
//	public BeaconAdapter(ArrayList<IBeacon> arrayL){
//		this.arrayL = arrayL;
//	}
//
//		@Override
//		public int getCount() {
//			if (arrayL != null && arrayL.size() > 0)
//				return arrayL.size();
//			else
//				return 0;
//		}
//
//		@Override
//		public IBeacon getItem(int arg0) {
//			return arrayL.get(arg0);
//		}
//
//		public long getItemId(int arg0) {
//			return arg0;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
////				if (arrayL.get(position).getProximityUuid() != null)
////					holder.beacon_uuid.setText("UUID: " + arrayL.get(position).getProximityUuid());
////
////				holder.beacon_major.setText("Major: " + arrayL.get(position).getMajor());
////
////				holder.beacon_minor.setText(", Minor: " + arrayL.get(position).getMinor());
////
////				holder.beacon_proximity.setText("Proximity: " + arrayL.get(position).getProximity());
////
////				holder.beacon_rssi.setText(", Rssi: " + arrayL.get(position).getRssi());
////
////				holder.beacon_txpower.setText(", TxPower: " + arrayL.get(position).getTxPower());
////
////				holder.beacon_range.setText("" + arrayL.get(position).getAccuracy());
////				
////				if(arrayL.get(position).getAccuracy()<1){
////					generateNotification(getBaseContext() ,  "2108 I find it ");
////				}
//
//			return getAbstractView(position ,convertView ,parent);
//		}
//	   public abstract View getAbstractView(int position, View convertView, ViewGroup parent);
//}