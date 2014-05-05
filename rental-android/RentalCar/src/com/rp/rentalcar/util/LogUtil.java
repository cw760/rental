package com.rp.rentalcar.util;

import android.util.Log;

public class LogUtil {

	public static void addLog(String msg) {

		if (msg == null || msg.trim().length() == 0)
			return;

		Log.e("ErrorLog", msg);

	}

	public static void addLog(Exception e) {

		Log.e("ErrorLog", "androidError", e);

	}

	public static void addLogTag(String msg) {
		if (msg == null || msg.trim().length() == 0)
			return;
		Log.i("TAG", msg);

	}

}
