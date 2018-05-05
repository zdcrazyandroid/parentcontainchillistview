package com.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 字符处理
 * 
 * @author xieyw
 * 
 */
public class StringUtils {
	/**
	 * 组合链接
	 * 
	 * @param sb
	 * @param map
	 * @return
	 */
	public static String allUrl(StringBuffer sb, Map<String, String> map) {
		try {
			if (map != null && !map.isEmpty()) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getValue() !=null) {						
						sb.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 获取流信息,返回btye数组
	 * 
	 * @param input
	 * @return
	 */
	public static byte[] getInputData(InputStream input) {
		byte[] buffer = new byte[10240];
		int length = 0;
		ByteArrayOutputStream byteoutput = new ByteArrayOutputStream();
		try {
			while ((length = input.read(buffer)) != -1) {
				byteoutput.write(buffer, 0, length);
			}
			byteoutput.flush();
			byteoutput.close();

			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteoutput.toByteArray();
	}

	/**
	 * 获取流信息,返回 string
	 * 
	 * @param input
	 * @return
	 */
	public static String getStringData(InputStream input) {
		if (input != null) {
			try {
				byte[] bytes = getInputData(input);
				return new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	/**
	 * 获取流信息,返回jsonArray
	 * 
	 * @param input
	 * @return
	 */
	public static JSONArray getJsonData(InputStream input) {
		if (input != null) {
			try {
				byte[] bytes = getInputData(input);
				return new JSONArray(new String(bytes, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else
			return null;
	}

	/**
	 * 解析字符串,返回jsonArray
	 * 
	 * @param source
	 * @return
	 */
	public static JSONArray getJsonArray(String source) {
		if (StringUtils.isNotEmpty(source)) {
			try {
				return new JSONArray(source);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else
			return null;
	}

	

	/**
	 * 判断string是否存在
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		if (string != null && string.length() > 0) {
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @param currentpage
	 * @param maxResult
	 * @return
	 */
	public static int getFirstIndex(int currentpage, int maxResult) {
		int firstIndex = 0;
		if (currentpage >= 0) {
			firstIndex = (currentpage - 1) * maxResult <= 0 ? 0
					: (currentpage - 1) * maxResult;
		}
		return firstIndex;
	}

	public static int getendIndex(int currentpage, int maxResult, int totalSize) {
		int endIndex = totalSize;
		int firstIndex = getFirstIndex(currentpage, maxResult);
		if (currentpage >= 0) {
			endIndex = (firstIndex + 10) <= totalSize ? (firstIndex + 10)
					: totalSize;
		}
		return endIndex;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	public static boolean isNullOrEmpty(String str) {
		boolean flag = true;
		if (str != null && !"".equals(str.trim())) {
			flag = false;
		}
		return flag;
	}

	public static boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}

}
