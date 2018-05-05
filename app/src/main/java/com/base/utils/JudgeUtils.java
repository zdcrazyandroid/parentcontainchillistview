package com.base.utils;

import android.R.bool;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 常用判断类
 * @author xieyw
 *
 */
public class JudgeUtils {

	/**
	 * 判读字符串是否为空
	 * 
	 * @param judgetStr
	 * @return
	 */
	public static boolean isEmpty(String judgetStr) {

		if (judgetStr == null || judgetStr.length() == 0) {
			return true;
		}
		return false;

	}
	/**
	 * 是否联网
	 * @param ctx
	 * @return
	 */
	public static boolean isConnectNet(Context ctx) {
		ConnectivityManager connectManager = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWorkInfo = connectManager.getActiveNetworkInfo();
		return netWorkInfo == null ? false : netWorkInfo.isAvailable();
	}


}
