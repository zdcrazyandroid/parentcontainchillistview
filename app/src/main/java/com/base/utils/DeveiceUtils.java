package com.base.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 设备信息类
 * 
 * @author xieyw
 * 
 */
public class DeveiceUtils {

	public static String DeveiceId = null;

	/**
	 * DeviceId
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		String result = DeveiceId;
		if (DeveiceId == null) {
			result = "000000";
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (imei != null && imei.length() > 0) {
				result = imei;
			}
		}
		return result;
	}

}
