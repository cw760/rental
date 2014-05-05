package com.rp.rentalcar.util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtil {

	public static float[] getScreenDpi(Activity activity) {
		DisplayMetrics dm = getDisplayMetrics(activity);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		if ((screenWidth * screenHeight) < 480 * 800) {
			return new float[] { 2.0f, 1.0f };
		} else if ((screenWidth * screenHeight) >= 480 * 800
				&& (screenWidth * screenHeight) < 640 * 960) {
			return new float[] { 1.5f, 1.5f };
		} else if ((screenWidth * screenHeight) >= 640 * 960) {
			return new float[] { 1.0f, 2.0f };
		}
		return new float[] { 1.5f, 1.5f };
	}

	public static DisplayMetrics getDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}
}
