package com.example.socketserver;

import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private boolean OutServer = false;
	private ServerSocket server;
	private final int ServerPort = 8765;// 要監控的port
	private Handler handler;
	TextView tx;
	Button btn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		tx = (TextView) findViewById(R.id.message);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			    tx.setText("伺服器已啟動 !");
				SocketServer();
				handler = new Handler();
				handler.post(runnable);
			}
		});
	
	}

	private void SocketServer() {
		try {
			server = new ServerSocket(ServerPort);

		} catch (java.io.IOException e) {
			Log.e("Socket啟動有問題 !","Socket啟動有問題 !");
			Log.e("IOException :" , e.toString());
		}
	}
	
	private Runnable runnable = new Runnable() {
		public void run() {
	        Socket socket;
	        java.io.BufferedInputStream in;
	    
	        Log.e("伺服器已啟動 !","伺服器已啟動 !");
	        while (!OutServer) {
	            socket = null;
	            try {
	                synchronized (server) {
	                    socket = server.accept();
	                }
	                Log.e("取得連線 : InetAddress = ", ""+socket.getInetAddress());
	                // TimeOut時間
	                socket.setSoTimeout(15000);
	 
	                in = new java.io.BufferedInputStream(socket.getInputStream());
	                byte[] b = new byte[1024];
	                String data = "";
	                int length;
	                while ((length = in.read(b)) > 0)// <=0的話就是結束了
	                {
	                    data += new String(b, 0, length);
	                }
	         
	                Log.e("我取得的值:" ,"" +data);
	                in.close();
	                in = null;
	                socket.close();
	                OutServer = true;
	                
	                tx.setText("我取得的值:"+data);
	            } catch (java.io.IOException e) {
	                Log.e("Socket連線有問題 !","Socket連線有問題 !");
	                Log.e("IOException :", e.toString());
	            }
	 
	        }
	        
	        
	    }
	};
	protected void onDestroy() {
		if(handler!=null){
			handler.removeCallbacks(null);
			handler = null;
		}
	
		super.onDestroy();
	}
	
	
	
}
