package com.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class TimeUtils {
	/**
	 * 获取时间戳
	 * 
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static long getStrToDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date.getTime();
	}

	/**
	 * 当前时间戳
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static long getCurDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getStrToDate(sdf.format(new Date()));
	}

	/**
	 * 获取相差时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getOffestTime(String time) {
		int cutTime = NumUtils.NUM_OVER_TIME;

		String strTimeMsg = StrUtils.STR_OVERD_TIME;
		long curOff = getCurDate() / 1000 - getStrToDate(time) / 1000;
		if (curOff > 0 && curOff < cutTime) {
			curOff = cutTime - curOff;
			int ss = (int) (curOff % 60);
			int mm = (int) (curOff / 60);
			String spli = ":";
			if (ss < 10) {
				spli = spli + "0";
			}
			strTimeMsg = mm + spli + ss + StrUtils.STR_WILL_FAIL;
		}
		return strTimeMsg;
	}

	/**
	 * 是否失效
	 * @param time
	 * @return
	 */
	public static boolean isOverTime(String time) {
		int cutTime = NumUtils.NUM_OVER_TIME;
		boolean overTime = true;
		long curOff = getCurDate() / 1000 - getStrToDate(time) / 1000;
		if (curOff > 0 && curOff < cutTime) {
			overTime = false;
		}
		return overTime;
	}

	/**
	 * 返回超市时间
	 * 
	 * @param time
	 * @return
	 */
	public static long overdTime(String time) {
		int cutTime = NumUtils.NUM_OVER_TIME;
		long overTime = NumUtils.NUM_OVERD_MARK;
		long curOff = getCurDate() / 1000 - getStrToDate(time) / 1000;
		if (curOff > 0 && curOff < cutTime) {
			overTime = curOff;
		}
		return overTime;
	}
}
