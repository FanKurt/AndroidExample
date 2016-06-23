package com.guidercare.Module;

import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.os.Handler;
import android.util.Log;

public class BluetoothLeScanner {
	private Handler mHandler;
	private BluetoothAdapter.LeScanCallback mLeScanCallback;
	private boolean mScanning;
	private BluetoothAdapter mBluetoothAdapter;

	public BluetoothLeScanner(LeScanCallback leScanCallback,BluetoothAdapter mBluetoothAdapter) {
		this.mLeScanCallback = leScanCallback;
		this.mBluetoothAdapter = mBluetoothAdapter;
		mHandler = new Handler();
	}

	public void scanLeDevice(final boolean enable) {
		if (enable) {
			if (mScanning) {
				return;
			}
			Log.e("TAG", "~ Starting Scan");
			mScanning = true;
			mBluetoothAdapter.startLeScan(mLeScanCallback);
		} else {
			mScanning = false;
			mBluetoothAdapter.stopLeScan(mLeScanCallback);
		}
	}
}
