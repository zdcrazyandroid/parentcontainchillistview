package com.base.utils;

import android.content.Context;
import android.util.Log;

/**
 * log输出类
 * 
 * @author xieyw
 * 
 */
public class LogUtils {

	/**
	 * 输出相应类的log信息
	 * @param context
	 * @param msg
	 */
	public static void d(Context context, String msg) {
		if (ControlUtils.IS_SHOW_LOG) {
			try {
				Log.d(context.getClass().getName(), msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void d(String tag, String msg) {
		if (ControlUtils.IS_SHOW_LOG) {
			Log.d(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (ControlUtils.IS_SHOW_LOG) {
			Log.e(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (ControlUtils.IS_SHOW_LOG) {
			Log.w(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (ControlUtils.IS_SHOW_LOG) {
			Log.i(tag, msg);
		}
	}

}
