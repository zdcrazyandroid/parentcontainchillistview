package com.base.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.widget.ImageView;

public class ImageLoad {
	public static String DOWN_PATH = android.os.Environment
			.getExternalStorageDirectory().getAbsolutePath();
	private static final int FILEMAX = 200;// 最多存储个数
	public static String imgDir = "";

	// private static HashMap<String, SoftReference<Bitmap>> ImageUtil.cache=new
	// HashMap<String, SoftReference<Bitmap>>();//图片缓存

	private File cacheDir;// 文件夹

	private String fileDir = "";// 文件夹路径
	private HttpClient mHttpClient;
	private boolean onlay = true;

	/**
	 * 清除以前文件
	 */
	public void cleanImageFile() {
		try {

			String[] children = cacheDir.list();

			if (children != null) {
				if (children.length > FILEMAX) {
					File[] files = fileSort();
					for (int index = FILEMAX - 100; index < files.length; index++) {
						files[index].delete();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改文件的最后修改时间
	 * 
	 * @param dir
	 * @param fileName
	 */
	private void updateFileTime(File file) {
		long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
		// Utils.showLog("imageTime", " time" +newModifiedTime);
	}

	/**
	 * 文件排序
	 * 
	 * @return
	 */

	public File[] fileSort() {
		File[] files = cacheDir.listFiles();
		File file;
		for (int i = 0; i < files.length - 1; i++) {
			for (int j = 0; j < files.length; j++) {
				if (files[i].lastModified() > files[j].lastModified()) {
					file = files[j];
					files[j] = files[i];
					files[i] = file;
				}
			}
		}
		return files;
	}

	/**
	 * 初始化文件夹
	 * 
	 * @param context
	 * @param fileDir
	 */
	public ImageLoad(HttpClient mHttpClient) {
		File file;
		ImageUtil.cache.clear();
		photoLoaderThread.setPriority(Thread.NORM_PRIORITY - 1);
		// if (android.os.Environment.getExternalStorageState().equals(
		// android.os.Environment.MEDIA_MOUNTED))
		System.gc();
		file = new File(DOWN_PATH, "LanGuangMarket");
		if (!file.exists())
			file.mkdirs();
		if (file.exists()) {
			cacheDir = new File(file.getAbsolutePath(), "images");
			// cacheDir = context.getCacheDir();
			if (!cacheDir.exists())
				cacheDir.mkdirs();
			this.fileDir = cacheDir.getAbsolutePath();
			imgDir = fileDir;
		}
		if (this.mHttpClient == null) {

			this.mHttpClient = mHttpClient;
		}
	}

	/**
	 * 取得文件
	 * 
	 * @param imageUrl
	 * @return
	 */
	public File getFile(String imageUrl) {
		return new File(fileDir, Uri.encode(imageUrl));
	}

	private int showIndex = 0;

	/**
	 * 定义大小显示
	 * 
	 * @param imageUrl
	 *            地址
	 * @param context
	 * @param imageView
	 *            控件
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public void DisplayImage(String imageUrl, Context context,
			ImageView imageView, int width, int height) {
		// Utils.showLog("imageTime",imageUrl + " 1: " +
		// System.currentTimeMillis());

		Bitmap bitmap = null;
		if (imageUrl != null) {
			if (ImageUtil.cache.containsKey(imageUrl)) {

				bitmap = ImageUtil.cache.get(imageUrl);
				if (bitmap != null) {
					// imageView.setBackground(null);
					// if (width == 0)
					// imageView.setImageBitmap(bitmap);
					// else
					// imageView.setImageBitmap(ThumbnailUtils
					// .extractThumbnail(bitmap, width, height));
					imageView.setBackgroundDrawable(ImageUtil
							.BitmapToDrawable(bitmap));
					bitmap = null;
					return;
				} else {
					ImageUtil.cache.get(imageUrl).recycle();
				}
			}
			bitmap = null;
			if (imageUrl.length() < 10)
				return;
			showIndex++;
			if (isScroll) {
				return;
			}
			if (getFile(imageUrl).exists()) {
				bitmap = getLocalBitmap(getFile(imageUrl), width, height);
				if (bitmap != null) {
					// if(bitmap.getWidth() < 80 || width != 0)
					ImageUtil.cache.put(imageUrl, bitmap);
					// imageView.setBackground(null);
					// imageView.setImageBitmap(bitmap);
					imageView.setBackgroundDrawable(ImageUtil
							.BitmapToDrawable(bitmap));
					bitmap = null;
					return;
				}
			}
			bitmap = null;

			queuePhoto(imageUrl, context, imageView, width, height);
			bitmap = null;
		}
		// Utils.showLog("imageTime",imageUrl + " 2: " +
		// System.currentTimeMillis());
	}

	/**
	 * 定义大小显示
	 * 
	 * @param imageUrl
	 *            地址
	 * @param context
	 * @param imageView
	 *            控件
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public void DisplayImage(String imageUrl, Context context,
			ImageView imageView, int width, int height, boolean loadFile) {
		// Utils.showLog("imageTime",imageUrl + " 1: " +
		// System.currentTimeMillis());

		Bitmap bitmap = null;
		if (imageUrl != null) {
			if (ImageUtil.cache.containsKey(imageUrl)) {
				bitmap = ImageUtil.cache.get(imageUrl);
				if (bitmap != null) {
					imageView.setBackgroundDrawable(ImageUtil
							.BitmapToDrawable(bitmap));
					bitmap = null;
					return;
				} else {
					ImageUtil.cache.get(imageUrl).recycle();
				}
			}
			if (!loadFile) {
				return;
			}
			bitmap = null;
			if (imageUrl.length() < 10)
				return;

			showIndex++;
			if (isScroll) {
				return;
			}
			if (getFile(imageUrl).exists()) {
				bitmap = getLocalBitmap(getFile(imageUrl), width, height);
				if (bitmap != null) {
					// if(bitmap.getWidth() < 80 || width != 0)
					ImageUtil.cache.put(imageUrl, bitmap);
					// imageView.setBackground(null);
					// imageView.setImageBitmap(bitmap);
					imageView.setBackgroundDrawable(ImageUtil
							.BitmapToDrawable(bitmap));
					bitmap = null;
					return;
				}
			}
			bitmap = null;

			queuePhoto(imageUrl, context, imageView, width, height);
			bitmap = null;
		}
		// Utils.showLog("imageTime",imageUrl + " 2: " +
		// System.currentTimeMillis());
	}

	/**
	 * 默认大小显示
	 * 
	 * @param imageUrl
	 *            地址
	 * @param context
	 * @param imageView
	 *            控件
	 */
	public void DisplayImage(String imageUrl, Context context,
			ImageView imageView) {
		if (imageUrl != null) {
			Bitmap bitmap = null;
			if (ImageUtil.cache.containsKey(imageUrl)) {

				bitmap = ImageUtil.cache.get(imageUrl);
				if (bitmap != null) {
					// imageView.setBackground(null);
					// imageView.setImageBitmap(bitmap);
					imageView.setBackgroundDrawable(ImageUtil
							.BitmapToDrawable(bitmap));
					bitmap = null;
					return;
				}
				return;
			}
			bitmap = null;
			int width = 0;
			int height = 0;

			if (imageUrl.length() < 10)
				return;
			if (getFile(imageUrl).exists()) {
				bitmap = getLocalBitmap(getFile(imageUrl), width, height);
				if (bitmap != null) {
					// if(bitmap.getWidth()< 80 || width != 0)
					ImageUtil.cache.put(imageUrl, bitmap);
				}
			}
			if (bitmap != null) {
				// imageView.setBackground(null);
				// imageView.setImageBitmap(bitmap);
				imageView.setBackgroundDrawable(ImageUtil
						.BitmapToDrawable(bitmap));
				bitmap = null;
				return;
			}
			bitmap = null;
			queuePhoto(imageUrl, context, imageView, width, height);
			bitmap = null;
		}
	}

	/**
	 * 图片导入
	 * 
	 * @param imageUrl
	 * @param context
	 * @param imageView
	 */
	private void queuePhoto(String imageUrl, Context context,
			ImageView imageView, int width, int height) {
		// Utils.showLog("imageTime",imageUrl + " 5: " +
		// System.currentTimeMillis());

		photosQueue.Clean(imageView);
		PhotoToLoad p = new PhotoToLoad(imageUrl, imageView, width, height);
		synchronized (photosQueue.photosToLoad) {
			photosQueue.photosToLoad.push(p);
			photosQueue.photosToLoad.notifyAll();
		}

		if (photoLoaderThread.getState() == Thread.State.NEW)
			photoLoaderThread.start();

		if (photosRun.getState() == Thread.State.NEW && onlay)
			photosRun.start();
	}

	/**
	 * 获取网络图片
	 * 
	 * @param imageUrl
	 * @return
	 */
	private Bitmap getBitmap(String imageUrl, int width, int height) {

		File file = getFile(imageUrl);
		Bitmap bitmap = null;
		if (file.exists())
			bitmap = getLocalBitmap(file, width, height);
		if (bitmap != null)
			return bitmap;
		bitmap = null;
		if (imageUrl == null)
			return null;
		InputStream is = null;
		if (imageUrl.length() > 7
				&& imageUrl.substring(0, 7).compareToIgnoreCase("http://") == 0) {

			try {
				if (ImageUtil.cache.size() > 300)
					ImageUtil.cache.clear();
				HttpGet httpGet = new HttpGet(imageUrl);
				httpGet.addHeader("Accept-Encoding", "gzip");
				HttpResponse response = mHttpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				is = getUngzippedContent(entity);

				OutputStream os = new FileOutputStream(file);
				byte[] byt = new byte[4096];
				int count;
				int total = 0;
				while ((count = is.read(byt)) > 0) {
					os.write(byt, 0, count);
					total += count;
				}
				os.close();
				return getLocalBitmap(file, width, height);
			} catch (Exception ex) {
				ex.printStackTrace();
				if (is != null) {
					return getLocalBitmap(InputStream2Bitmap(is), width, height);
				}
				return null;
			}
		}
		return null;
	}

	public Bitmap InputStream2Bitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	public static InputStream getUngzippedContent(HttpEntity entity)
			throws IOException {
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

	public Bitmap optimizeBitmap(byte[] source, int maxWidth, int maxHeight) {
		Bitmap result = null;
		int length = source.length;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		result = BitmapFactory.decodeByteArray(source, 0, length, options);
		int widthRatio = (int) Math.ceil(options.outWidth / maxWidth);
		int heightRatio = (int) Math.ceil(options.outHeight / maxHeight);
		if (widthRatio > 1 || heightRatio > 1) {
			if (widthRatio > heightRatio) {
				options.inSampleSize = widthRatio;
			} else {
				options.inSampleSize = heightRatio;
			}
		}
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeByteArray(source, 0, length, options);
	}

	/**
	 * 本地图片流获取
	 * 
	 * @param hash
	 * @return
	 * @throws IOException
	 */
	public Bitmap getLocalBitmap(File file, int width, int height) {
		Bitmap bitmap = null;
		try {
			updateFileTime(file);
			bitmap = BitmapFactory
					.decodeStream((InputStream) new FileInputStream(file));

			if (bitmap != null) {
				if (width == 0) {
					return bitmap;
				} else
					return ThumbnailUtils.extractThumbnail(bitmap, width,
							height);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			bitmap = null;
			// System.gc();
		}
		return null;
	}

	public Bitmap getLocalBitmap(Bitmap bitmap, int width, int height) {
		if (bitmap != null) {
			if (width == 0) {
				return bitmap;
			} else
				return ThumbnailUtils.extractThumbnail(bitmap, width, height);
		}
		return null;
	}

	/**
	 * 栈内容
	 * 
	 * @author 谢云伟
	 * 
	 */
	private class PhotoToLoad {
		public String url = null;
		public int width = 0;
		public ImageView imageView = null;
		public int height = 0;

		public PhotoToLoad(String imageUrl, ImageView image, int width,
				int height) {
			url = imageUrl;
			this.width = width;
			this.height = height;
			imageView = image;
		}
	}

	PhotosQueue photosQueue = new PhotosQueue();

	/**
	 * 停止线程
	 */
	public void stopThread() {
		photoLoaderThread.interrupt();
	}

	/**
	 * 按要求适配
	 * 
	 * @param bitmap
	 * @param imageWidth
	 * @param imageHeight
	 * @return
	 */
	public Bitmap zoomBitmap(Bitmap bitmap, int imageWidth, int imageHeight) {
		if (bitmap != null)
			return ThumbnailUtils.extractThumbnail(bitmap, imageWidth,
					imageHeight);
		return null;
	}

	public void delAllLoadImg() {
		isScroll = true;
		if (photosQueue != null) {
			if (photosQueue.photosToLoad != null
					&& photosQueue.photosToLoad.size() > 0) {

				for (int index = photosQueue.photosToLoad.size() - 1; index >= 0; index--) {
					photosQueue.photosToLoad.remove(index);
				}
			}
		}
	}

	/**
	 * 栈存储图片存储地址
	 * 
	 * @author xieyunwei
	 * 
	 */
	class PhotosQueue {
		private Stack<PhotoToLoad> photosToLoad = new Stack<PhotoToLoad>();

		public void Clean(ImageView image) {
			if (photosToLoad != null && photosToLoad.size() > 0) {
				for (int index = 0; index < photosToLoad.size();) {
					int count = photosToLoad.size();
					// if (photosToLoad.size() > 15) {
					for (int i = 0; i < count - 15; i++) {
						photosToLoad.remove(0);
					}
					try {
						if (photosToLoad.get(index).imageView == image)
							photosToLoad.remove(index);
						else
							++index;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	}

	PhotosRun photosRun = new PhotosRun();

	class PhotosRun extends Thread {

		public void run() {
			if (onlay) {
				onlay = false;
				cleanImageFile();
			}
		}
	}

	private int indexCount = 0;
	private boolean isScroll = false;

	/**
	 * 线程图片加载并上传
	 * 
	 * @author xieyunwei
	 * 
	 */
	class PhotosLoader extends Thread {
		public void run() {
			try {
				while (true) {
					if (!isScroll) {
						if (photosQueue.photosToLoad.size() == 0)
							synchronized (photosQueue.photosToLoad) {
								// ImageUtil.cache.clear();
								System.gc();
								photosQueue.photosToLoad.wait();
							}
						if (photosQueue.photosToLoad.size() != 0) {
							PhotoToLoad photoToLoad;
							synchronized (photosQueue.photosToLoad) {
								photoToLoad = photosQueue.photosToLoad.get(0);
								photosQueue.photosToLoad.remove(0);

							}
							indexCount++;
							if (indexCount >= 20) {
								System.gc();
								indexCount = 0;
							}
							Bitmap bmp = null;
							bmp = getBitmap(photoToLoad.url, photoToLoad.width,
									photoToLoad.height);
							if (bmp != null) {
								// if(bmp.getWidth() < 80 || photoToLoad.width
								// != 0)
								ImageUtil.cache.put(photoToLoad.url, bmp);
							}

							Object tag = photoToLoad.imageView.getTag();
							if (tag != null
									&& ((String) tag).equals(photoToLoad.url)) {
								BitmapDisplayer bd = new BitmapDisplayer(bmp,
										photoToLoad.imageView);
								Activity a = (Activity) photoToLoad.imageView
										.getContext();
								a.runOnUiThread(bd);
								photoToLoad.imageView = null;
								bmp = null;
								bd = null;
								tag = null;
								a = null;
							}
						}

						if (Thread.interrupted()) {
							// ImageUtil.cache.clear();
							break;
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	PhotosLoader photoLoaderThread = new PhotosLoader();

	/**
	 * 图片加载线程
	 * 
	 * @author 谢云伟
	 * 
	 */
	class BitmapDisplayer implements Runnable {
		Bitmap bitmap = null;
		ImageView imageView = null;

		public BitmapDisplayer(Bitmap b, ImageView i) {
			bitmap = b;
			imageView = i;
		}

		public void run() {
			if (bitmap != null) {
				// imageView.setBackground(null);
				// imageView.setImageBitmap(bitmap);
				imageView.setBackgroundDrawable(ImageUtil
						.BitmapToDrawable(bitmap));
			}
			bitmap = null;

		}
	}

	/**
	 * 图片删除所有图片和缓存
	 */
	public void clearCache() {
		ImageUtil.cache.clear();
		File[] files = cacheDir.listFiles();
		for (File f : files)
			f.delete();
	}

	public boolean isScroll() {
		return isScroll;
	}

	public void setScroll(boolean isScroll) {
		this.isScroll = isScroll;
	}

}
