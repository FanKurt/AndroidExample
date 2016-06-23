package com.guidercare.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.guidercare.Controller.GuiderCareController;
import com.guidercare.Layout.GuiderCareLayout;
import com.guidercare.Module.BeaconTag;
import com.guidercare.Module.XMPPClient;


public class GuiderCareActivity extends Activity {
	

	public static GuiderCareController mGuiderCareController;
	
	private Intent intent;
	private GuiderCareLayout mGuiderCareLayout;
	private XMPPClient mXMPPClient;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mGuiderCareLayout =new GuiderCareLayout(GuiderCareActivity.this));
        if (BeaconTag.queue == null) {
        	BeaconTag.queue = Volley.newRequestQueue(GuiderCareActivity.this);
		}
        if(!readSDFile(Environment.getExternalStorageDirectory()+"/Guider_Config.txt")){
        	Toast.makeText(this, "Config error", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        // always keep screen on
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        mGuiderCareController = new GuiderCareController(GuiderCareActivity.this ,mGuiderCareLayout);
        mXMPPClient = new XMPPClient(this);
        intent =  new Intent(GuiderCareActivity.this , GuiderCareService.class);
        
      
//        ReminderDialog reminderDialog = new ReminderDialog(GuiderCareActivity.this);
//        reminderDialog.setDialogTitle(GuiderCareActivity.this.getResources().getString(R.string.warnTitle));
//        reminderDialog.setDialogMessage(GuiderCareActivity.this.getResources().getString(R.string.warnContent));
//        reminderDialog.setPositiveButton(GuiderCareActivity.this.getResources().getString(R.string.dialogConfirm));
//        reminderDialog.setNegativeButton(GuiderCareActivity.this.getResources().getString(R.string.dialogCancel));
//        reminderDialog.saveSetting();
//        reminderDialog.setOnDialogListener(new OnDialogListener() {
//			public void onClick(int which) {
//				if(which == -1){
//					//確認吃藥
//				}
//				Toast.makeText(GuiderCareActivity.GuiderCareActivity.this, getResources().getString(R.string.dialogCallBack), Toast.LENGTH_SHORT).show();
//			}
//		}); 
//        reminderDialog.show();
    }

	protected void onResume() {
		super.onResume();
		if (!mGuiderCareController.getBluetoothAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, BeaconTag.REQUEST_ENABLE_BT);
        }
	}

	protected void onStart() {
		super.onStart();
		if (mGuiderCareController.checkAvailability()){
			mGuiderCareController.startScan();
//			GuiderCareActivity.this.stopService(intent);
		}
	
		
	}

	protected void onStop() {
		super.onStop();
		if (mGuiderCareController.checkAvailability()){
			mGuiderCareController.stopScan();
//		    GuiderCareActivity.this.startService(intent);
		}
		mGuiderCareController.getApiClient().disconnect();
		mXMPPClient.close();
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BeaconTag.REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }else{
        	onStart();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
	
	private boolean readSDFile(String fileName)
    {
        String content;
        FileInputStream is=null;
        File file=new File(fileName);
        if(!file.exists())
        {
            Log.e("",file.getAbsolutePath()+" is not exists");
            return false;
        }
        if(!file.canRead())
        {
            Log.e("",file.getAbsolutePath()+" can not be read");
            return false;
        }        
        try{
            is=new FileInputStream(file);
            byte buffer[]=new byte[is.available()];
            is.read(buffer);
            content=new String(buffer);
            Log.e("", "read SD file:"+content);
            JSONObject mJSONObject = new JSONObject(content);
            BeaconTag.SERVICE = mJSONObject.get("IP").toString();
            BeaconTag.HOST = mJSONObject.get("IP").toString();
            BeaconTag.TO = mJSONObject.get("TO").toString();
            BeaconTag.USER = mJSONObject.get("USER").toString();
            BeaconTag.PASSWORD = mJSONObject.get("PASSWORD").toString();
            BeaconTag.TOILET = mJSONObject.get("TOILET_MAC").toString();
            BeaconTag.BEDROOM = mJSONObject.get("BEDROOM_MAC").toString();
            BeaconTag.BALCONY = mJSONObject.get("OUTDOOR_MAC").toString();
            BeaconTag.LIVINGROOM = mJSONObject.get("LIVINGROOM_MAC").toString();
            BeaconTag.DISTANCE = Double.parseDouble(mJSONObject.get("DISTANCE").toString());
            Log.e("", ""+BeaconTag.SERVICE);
            Log.e("", ""+BeaconTag.HOST);
            Log.e("", ""+BeaconTag.TO);
            Log.e("", ""+BeaconTag.LIVINGROOM);
            Log.e("", ""+BeaconTag.TOILET);
            Log.e("", ""+BeaconTag.BEDROOM);
            Log.e("", ""+BeaconTag.BALCONY);
            return true;
        }
        catch(IOException e)
        {
        	 return false;
        } catch (JSONException e) {
        	 return false;
		}
        finally
        {
            if(is!=null)
            {
                try{
                is.close();
                }catch(IOException e)
                {
                	 return false;
                }
            }
        }   
    }
	
}
