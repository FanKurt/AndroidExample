package com.example.testphoto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {
	private final static int PHOTO = 99;
	private String Data;
	private final String PackageName="com.imac.Nantou5";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void GetVersion(){
		PackageManager m = getPackageManager();
		String app_ver = "";
		try {
		    app_ver = m.getPackageInfo(this.getPackageName(), 0).versionName;
		} 
		catch (NameNotFoundException e) {
		    throw new AssertionError();
		}
		UnityPlayer.UnitySendMessage("Panel", "GetVersion", app_ver);
	}

	public void OpenPhoto() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, PHOTO);
	}

	public void ReadText() {
		BufferedReader reader = null;
		String mLine = "";
		String readData = "";
		try {
			FileReader fr = new FileReader(
					"/mnt/sdcard/Android/data/"+PackageName+"/files/Text");
			reader = new BufferedReader(fr);
			while ((mLine = reader.readLine()) != null) {
				readData += mLine + "\n";
			}
		} catch (IOException e) {
			Log.e("IOException", "" + e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		UnityPlayer.UnitySendMessage("Panel", "GetReadText", readData);
	}

	public void CreateText() {

		BufferedReader reader = null;
		String mLine = "";
		String readData = "";
		try {
			reader = new BufferedReader(new InputStreamReader(getAssets().open(
					"Text.txt"), "UTF-8"));
			while ((mLine = reader.readLine()) != null) {
				readData += mLine + "\n";
			}

		} catch (IOException e) {
			Log.e("IOException", "" + e);
		} finally {
			if (reader != null) {
				try {

					reader.close();
				} catch (IOException e) {
				}
			}
		}
		generateNoteOnSD("Text", readData);
	}

	public void ToastText(final String data) {
		Data= data;
		Message msg = mHandler.obtainMessage( 0 );
	    mHandler.sendMessage( msg );
		if (mHandler != null) {
			mHandler.removeCallbacks(null);
			mHandler = null;
		}

	}

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(MainActivity.this, Data, Toast.LENGTH_SHORT)
						.show();
				break;

			default:
				break;
			}
		}

	};

	public void generateNoteOnSD(String sFileName, String sBody) {
		String path = "/mnt/sdcard/Android/data/"+PackageName+"/files";
		try {
			File root = new File(path);
			if (!root.exists()) {
				root.mkdirs();
			}
			File gpxfile = new File(root, sFileName);
			FileWriter writer = new FileWriter(gpxfile);
			writer.append(sBody);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UnityPlayer.UnitySendMessage("Panel", "GetContent", sBody);
	}

	public String getPath(Uri uri) {
//		String[] projection = { MediaStore.Images.Media.DATA };
//		Cursor cursor = getContentResolver().query(uri, projection, null, null,
//				MediaStore.Images.ImageColumns.DATE_TAKEN);
//		if (cursor == null)
//			return null;
//		int column_index = cursor
//				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//		cursor.moveToFirst();
//		String path = "";
//		try {
//			if (cursor.getString(column_index) != null) {
//				path = cursor.getString(column_index);
//				String[] imageNames = path.split("/");
//				Bitmap org = BitmapFactory.decodeFile(path);
//				SaveBitmap(CutPicture(org, 720, 1280),
//						imageNames[imageNames.length - 1]);
//			}
//		} catch (Exception e) {
//			Log.e("Exception", "" + e);
//		}
		String path = "";
		try {
			path =ImageFilePath.getPath(this, uri);
			String[] imageNames = path.split("/");
			Bitmap org = BitmapFactory.decodeFile(path);
			SaveBitmap(CutPicture(org, 720, 1280),
					imageNames[imageNames.length - 1]);
		} catch (Exception e) {
			Log.e("Exception", "" + e);
		}

		return path;
	}

	public static Bitmap CutPicture(Bitmap bitmap, int width, int height) {
		if (bitmap != null) {
			float scaleWidth = ((float) width) / bitmap.getWidth();
			float scaleHeight = ((float) height) / bitmap.getHeight();
			Matrix matrix = new Matrix();
			matrix.postRotate(0);
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
					bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			return resizedBitmap;
		} else {
			return null;
		}
	}

	public void SaveBitmap(Bitmap bitmap, String FILE_NAME) throws IOException {

		FileOutputStream fOut = null;
		String path = "/mnt/sdcard/Android/data/"+PackageName+"/files";
		try {
			File destDir = new File(path);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			fOut = new FileOutputStream(path + "/" + FILE_NAME);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
			bitmap = null;
		} catch (Exception e) {
			return;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == PHOTO) && data != null) {
			Uri uri = data.getData();
			UnityPlayer.UnitySendMessage("Panel", "GetPath",
					(uri == null) ? "null" : getPath(uri));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}