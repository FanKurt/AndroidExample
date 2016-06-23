package com.example.logeservicedemo;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 設備安全管理服務2.2之前的版本是沒有對外暴露的只能通過反射技術獲取
		DevicePolicyManager policyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		// 申請權限
		ComponentName componentName = new ComponentName(this,
				MyAdminReceiver.class);
		// 判斷該組件是否有系統管理員的權限
		boolean isAdminActive = policyManager.isAdminActive(componentName);
		if (!isAdminActive) {
			// 啟動設備管理(隱式Intent) - 在AndroidManifest.xml中設定相應過濾器
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			//權限列表
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
					componentName);
			startActivity(intent);
		}

		Intent intent = new Intent(this, Servicetest.class);
		startService(intent);
	}

}
