package com.base.image;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 
 * @author xieyw
 * 
 */
public class ImageUtil {

	private static String tag = "ImageUtil";
	
	public static float scareWidth = 1.0f;
	public static float scareHeight = 1.0f;
	public static HashMap<String, Bitmap> cache=new HashMap<String, Bitmap>();
//	public static HashMap<String, SoftReference<Bitmap>> cache=new HashMap<String, SoftReference<Bitmap>>();
	public static int[] BitmapWidthAndHeight(Drawable drawable) {
		int[] widthAndHeight = new int[2];
		if(drawable != null)
		{
			Bitmap bitmap = drawableToBitmap(drawable);
			widthAndHeight[0] =(int) ( bitmap.getWidth() * scareWidth);
			widthAndHeight[1] = (int) (bitmap.getHeight() * scareWidth);
		}
		return widthAndHeight;
	}
	
	public static Bitmap zoomWidth(Drawable drawable)
	{
		if(drawable != null)
		{
			Bitmap bitmap = drawableToBitmap(drawable);
			int width =(int) ( bitmap.getWidth() * scareWidth);
			int height = (int) (bitmap.getHeight() * scareWidth);
			return ThumbnailUtils.extractThumbnail(bitmap, width, height);	
		}
		
		return null;
	}
	public static Bitmap zoomHeight(Drawable drawable)
	{
		if(drawable != null)
		{
			Bitmap bitmap = drawableToBitmap(drawable);
			int width =(int) ( bitmap.getWidth() * scareHeight);
			int height = (int) (bitmap.getHeight() * scareHeight);
			return ThumbnailUtils.extractThumbnail(bitmap, width, height);	
		}
		
		return null;
	}
	public static Bitmap zoomWidthAndHeight(Drawable drawable)
	{
		if(drawable != null)
		{
			Bitmap bitmap = drawableToBitmap(drawable);
			int width =(int) ( bitmap.getWidth() * scareWidth);
			int height = (int) (bitmap.getHeight() * scareHeight);
			return ThumbnailUtils.extractThumbnail(bitmap, width, height);	
		}
		
		return null;
	}
	
	

	/**
	 * 放大缩小图片
	 * 
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * 将Drawable转化为Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		if(drawable != null)
		{
			int width = drawable.getIntrinsicWidth();
			int height = drawable.getIntrinsicHeight();
			Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
					.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
					: Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, width, height);
			drawable.draw(canvas);
			return bitmap;
		}
		return null;

	}

	/**
	 * 获得圆角图片的方法(内有抗锯齿)
	 * 
	 * @param bitmap
	 * @param roundPx
	 * @param isRoundCor
	 *            :是否改成圆角角图片
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx,
			boolean isRoundCor) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		// 抗锯齿
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		if (isRoundCor) {
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		}
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 获得带倒影的图片方法
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * Bitmap转换成Drawable
	 * 
	 * @return
	 */
	public static Drawable BitmapToDrawable(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}

	/**
	 * 图片压缩
	 * 
	 * @param bitmap
	 * @param BitWidth
	 * @param BitHeight
	 *            :如果为零者是默认值
	 * @return
	 */
	public static Bitmap BitmapCompre(Bitmap bitmap, int BitWidth, int BitHeight) {

		BitmapFactory.Options BFO = new BitmapFactory.Options();
		BFO.inJustDecodeBounds = true;
		Bitmap bit = BitmapFactory.decodeStream(BitmapToInputStream(bitmap),
				null, BFO);

		if (BitHeight == 0) {
			BitHeight = BFO.outHeight;
			BitWidth = BFO.outWidth;
		}
		int yRatio = (int) Math.ceil(BFO.outHeight / BitHeight);
		int xRatio = (int) Math.ceil(BFO.outWidth / BitWidth);
		if (yRatio > 1 || xRatio > 1) {
			if (yRatio > xRatio) {
				BFO.inSampleSize = yRatio;
			} else {
				BFO.inSampleSize = xRatio;
			}
		}
		BFO.inJustDecodeBounds = false;

		Bitmap bitPic = BitmapFactory.decodeStream(BitmapToInputStream(bit),
				null, BFO);
		return bitPic;
	}

	public static InputStream BitmapToInputStream(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 得到输出流
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		// 转输入流
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	
	  public static final DefaultHttpClient createHttpClient() {
	      
	        HttpParams params = new BasicHttpParams();

	        HttpConnectionParams.setStaleCheckingEnabled(params, false);       
	        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
	        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
	        HttpConnectionParams.setSocketBufferSize(params, 8192);      
	        final SchemeRegistry supportedSchemes = new SchemeRegistry();      
	        final SocketFactory sf = PlainSocketFactory.getSocketFactory();
	        supportedSchemes.register(new Scheme("http", sf, 80));

	        final ClientConnectionManager ccm = new ThreadSafeClientConnManager(params,
	                supportedSchemes);
	        return new DefaultHttpClient(ccm, params);
	    }
	  /**
	    * 
	    * @param entity
	    * @return
	    * @throws IOException
	    */
	    public static InputStream getUngzippedContent(HttpEntity entity) throws IOException {
	        InputStream responseStream = entity.getContent();
	        if (responseStream == null) {
	            return responseStream;
	        }
	        Header header = entity.getContentEncoding();
	        if (header == null) {
	            return responseStream;
	        }
	        String contentEncoding = header.getValue();
	        if (contentEncoding == null) {
	            return responseStream;
	        }
	        if (contentEncoding.contains("gzip")) {
	            responseStream = new GZIPInputStream(responseStream);
	        }
	        return responseStream;
	    }

	    public static Bitmap drawableToScalBitmap(Drawable drawable, int w, int h) // bitmap
		{
			int width = drawable.getIntrinsicWidth();
			int height = drawable.getIntrinsicHeight();
			if (width <= w && height <= h) {
				return ((BitmapDrawable) drawable).getBitmap();
			} else {
				Bitmap oldbmp = drawableToBitmap(drawable); // drawable
															// 转换成
				Matrix matrix = new Matrix(); // 创建操作图片用的 Matrix 对象
				float scaleWidth = ((float) w / width); // 计算缩放比例
				float scaleHeight = ((float) h / height);
				matrix.postScale(scaleWidth, scaleHeight); // 设置缩放比例
				Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
						matrix, true); // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
				oldbmp.recycle();
				return newbmp;// 把 bitmap 转换成 drawable 并返回
			}
		}
}