package com.base.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.MeasureSpec;

public class ViewUtil {
	/**
	 * 对控件截图。
	 * 
	 * @param v
	 *            需要进行截图的控件。
	 * @param quality
	 *            图片的质量
	 * @return 该控件截图的byte数组对象。
	 */
	public static byte[] printScreen(View v, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache(true);
		Bitmap bitmap = v.getDrawingCache();
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		return baos.toByteArray();
	}

	/**
	 * 截图
	 * 
	 * @param v
	 *            需要进行截图的控件
	 * @return 该控件截图的Bitmap对象。
	 */
	public static Bitmap printScreen(View v) {
		Bitmap bitmap = null;
		v.setDrawingCacheEnabled(false);
		v.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
		v.buildDrawingCache();
		bitmap = v.getDrawingCache();
		return bitmap;
	}

	/**
	 * 屏幕位置
	 * @param v
	 * @return
	 */
	public static int[] getLocation(View v) {
		int[] loc = new int[4];
		int[] location = new int[2];
		v.getLocationOnScreen(location);
		loc[0] = location[0];
		loc[1] = location[1];
		int w = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		int h = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		v.measure(w, h);

		loc[2] = v.getMeasuredWidth();
		loc[3] = v.getMeasuredHeight();
		return loc;
	}
}