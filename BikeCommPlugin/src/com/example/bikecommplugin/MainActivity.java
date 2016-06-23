package com.example.bikecommplugin;

import java.util.HashMap;

import molosports.api.SimpleComm;
import molosports.bike.IBikeDevice;
import molosports.bike.OutputSensorEvent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {
	private Handler mHandler ;
	private OutputSensorEvent kevent;
	private IBikeDevice m_kBikeDevice = null;
	private HashMap<String, String> iBikeInformation;
	private String m_eSensorID ,m_iValue,BikeSpeed ,BikeTurnDirection;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SimpleComm.StartBTConnActivity(12345, this);
		iBikeInformation= new HashMap<String, String>();
		mHandler = new Handler();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (12345 == requestCode) {
			SimpleComm.EConnectResult eResult = SimpleComm
					.ProcessBTConnActivityResult(requestCode, resultCode, data);
			if (SimpleComm.EConnectResult.CONNECT_SUCCEED == eResult) {
				m_kBikeDevice = SimpleComm.GetBikeDevice();
				if (null != m_kBikeDevice) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int iTurnValue = m_kBikeDevice.GetTurnValue();
					Toast toast = Toast.makeText(
							this,
							m_kBikeDevice.GetBikeID()
									+ " -> Get Bike Turn Value: "
									+ Integer.toString(iTurnValue),
							Toast.LENGTH_LONG);
					toast.show();
				}
				
				iBikeInformation.put("DeviceTypeNo", String.valueOf(m_kBikeDevice.GetDeviceTypeNo()));
				iBikeInformation.put("DeviceStatus", String.valueOf(m_kBikeDevice.GetDeviceStatus()));
				iBikeInformation.put("ButtonLayout", String.valueOf(m_kBikeDevice.GetButtonLayout())); //握把樣式(0直把 ,1衡把
				kevent = m_kBikeDevice.GetEvent(0);
				if(mHandler!=null){
					mHandler.postDelayed(Call_BikeAPI, 0);
				}
		
			}
		}
	}

	private Runnable Call_BikeAPI = new Runnable() {
		public void run() {
			m_eSensorID = String.valueOf(kevent.m_eSensorID);
			m_iValue = String.valueOf( m_kBikeDevice.GetEvent(0).m_iValue);
			BikeSpeed = ""+ m_kBikeDevice.GetSpeedValue();
			BikeTurnDirection =""+ m_kBikeDevice.GetTurnValue();
			if(m_iValue.trim().equals("1")){
				UnityPlayer.UnitySendMessage("Panel", "GetButtonTouchEvent",m_iValue);
			}
			if(mHandler!=null){
				mHandler.postDelayed(Call_BikeAPI, 0);
			}
		}
	};
	
	protected void onDestroy() {
		super.onDestroy();
		if(mHandler!=null){
			mHandler.removeCallbacks(Call_BikeAPI);
			mHandler=null;
		}
	}
	
	
	//DeviceTypeNo
	public void getDeviceTypeNo(){
		UnityPlayer.UnitySendMessage("Panel", "GetDeviceTypeNo", iBikeInformation.get("DeviceTypeNo"));
	}
	//DeviceStatus
	public void getDeviceStatus(){
		UnityPlayer.UnitySendMessage("Panel", "GetDeviceStatus", iBikeInformation.get("DeviceStatus"));
	}
	//握把樣式(0直把 ,1衡把
	public void getButtonLayout(){
		UnityPlayer.UnitySendMessage("Panel", "GetButtonLayout", iBikeInformation.get("ButtonLayout"));
	}
	//按鈕按下事件(0放開,1按下)
	public void getButtonTouchEvent(){
		UnityPlayer.UnitySendMessage("Panel", "GetButtonTouchEvent",m_iValue);
	}
	//8顆按鈕事件(9~16)
	public void getButtonEventm_eSensorID(){
		UnityPlayer.UnitySendMessage("Panel", "GetButtonEventm_eSensorID",m_eSensorID);
	}
	//按鈕事件(4按鈕事件)
	public void getButtonEventm_eEventID(){
		UnityPlayer.UnitySendMessage("Panel", "GetButtonEventm_eEventID", iBikeInformation.get("ButtonEventm_eEventID"));
	}
	//腳踏車轉速(RPM 0~1100+)
	public void getBikeSpeed(){
		UnityPlayer.UnitySendMessage("Panel", "GetBikeSpeed", BikeSpeed);
	}
	//腳踏車旋轉方向(0~150)
	public void getBikeTurnDirection(){
		UnityPlayer.UnitySendMessage("Panel", "GetBikeTurnDirection", BikeTurnDirection);
	}
	
}
