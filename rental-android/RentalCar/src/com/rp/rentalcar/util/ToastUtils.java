package com.rp.rentalcar.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {

	private static Toast toast;

	public static void show(Context context, int resId) {
		show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
	}

	public static void show(Context context, int resId, int duration) {
		show(context, context.getResources().getText(resId), duration);
	}

	public static void show(Context context, CharSequence text) {
		show(context, text, Toast.LENGTH_SHORT);
	}

	private static void show(Context context, CharSequence text, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, text, duration);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

	public static void show(Context context, int resId, Object... args) {
		show(context,
				String.format(context.getResources().getString(resId), args),
				Toast.LENGTH_SHORT);
	}

	public static void show(Context context, String format, Object... args) {
		show(context, String.format(format, args), Toast.LENGTH_SHORT);
	}

	public static void show(Context context, int resId, int duration,
			Object... args) {
		show(context,
				String.format(context.getResources().getString(resId), args),
				duration);
	}

	public static void show(Context context, String format, int duration,
			Object... args) {
		show(context, String.format(format, args), duration);
	}
}
