package com.example.testphoto;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class SaveBitmap extends AsyncTask<Object, Object, String> {
	protected String doInBackground(Object... params) {
		return Save(((Bitmap) params[0]), ((String) params[1]));
	}

	private String Save(Bitmap bitmap, String FILE_NAME) {

		FileOutputStream fOut = null;
		String path = "/mnt/sdcard/Android/data/com.example.testphoto/files";
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
			return null;
		}
		return path + "/" + FILE_NAME;
	}
}
