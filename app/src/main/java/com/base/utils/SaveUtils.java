package com.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveUtils {
	private static String DATASAVE = "DATASAVE";// 存储位置
	private static String PACKNANE = "PACKNANE";// 判断前缀

	/**
	 * 	判断数据是否存储 
	 * @param context
	 * @return
	 */
	public static boolean IsHasPack(Context context, String packName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		return preferences.getBoolean(PACKNANE + packName, false);
	}

	/**
	 * 存储判断数据
	 * 
	 * @param context
	 * @param channel
	 */
	public static void saveData(Context context, boolean saveValue,
			String saveName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putBoolean(PACKNANE + saveName, saveValue);
		edit.commit();
	}
	
	/**
	 * 存储判断数据
	 * 
	 * @param context
	 * @param channel
	 */
	public static void saveData(Context context, String saveValue,
			String saveName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString(PACKNANE + saveName, saveValue);
		edit.commit();
	}
	/**
	 * 	获取数据 
	 * @param context
	 * @return
	 */
	public static String getSaveData(Context context, String packName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		return preferences.getString(PACKNANE + packName, "");
	}
	

	/**
	 * 存储当前时间
	 * 
	 * @param context
	 * @param channel
	 */
	public static void saveCurrentData(Context context, long saveValue,
			String saveName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putLong(PACKNANE + saveName, saveValue);
		edit.commit();
	}
	/**
	 * 	获取数据 
	 * @param context
	 * @return
	 */
	public static long getSavecurrentData(Context context, String packName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		return preferences.getLong(PACKNANE + packName, 0);
	}
	
	/**
	 * 存储验证码
	 * 
	 * @param context
	 * @param channel
	 */
	public static void saveCodeData(Context context, String saveValue,
			String saveName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString(PACKNANE + saveName, saveValue);
		edit.commit();
	}
	/**
	 * 	获取验证码
	 * @param context
	 * @return
	 */
	public static String getSaveCodeData(Context context, String packName) {
		SharedPreferences preferences = context.getSharedPreferences(DATASAVE,
				Context.MODE_PRIVATE);
		return preferences.getString(PACKNANE + packName, " ");
	}

}
