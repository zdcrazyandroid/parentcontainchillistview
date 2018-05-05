package com.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Http����
 * 
 * @author caiwh
 * 
 */
public class HttpUtils {

	public static final String BASEURL = "http://desktop.gsie.cn/";

	public static String feedback(String feedbackUrl, String telephone,
			String content) {
		String serverURL = "http://desktop.gsie.cn/update";
		String result = null;
		HttpGet httpRequest = new HttpGet(serverURL);// ����http get����
		HttpResponse httpResponse = null;
		try {
			httpResponse = new DefaultHttpClient().execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200)
				result = EntityUtils.toString(httpResponse.getEntity());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ����http����

		//System.out.println("http://desktop.gsie.cn/update �ķ���ֵ=========="
		//		+ result);

		return result;
	}

	public static String upgrade(Context context) {
		String result = "";
		PackageManager manager;

		PackageInfo info = null;

		manager = context.getPackageManager();
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		try {

			info = manager.getPackageInfo(context.getPackageName(), 0);

		} catch (NameNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
		StringBuffer buffer = new StringBuffer();
		
		
		HttpGet httpRequest;
		HttpResponse httpResponse = null;
		String operatorNameString = null;
		try {
			buffer.append("http://desktop.gsie.cn/update?");
			buffer.append("version="+info.versionCode);
			buffer.append("&os=Android"+android.os.Build.VERSION.RELEASE);
			buffer.append("&imei="+tm.getSimSerialNumber());
			buffer.append("&brand="+android.os.Build.MANUFACTURER);
			buffer.append("&model="+android.os.Build.MODEL);
			if(tm.getSimOperatorName() == null){
				operatorNameString = "";
			}else{
				operatorNameString = tm.getSimOperatorName();
			}
			buffer.append("&oper="+URLEncoder.encode(operatorNameString, "UTF-8"));
			buffer.append("&net="+getCurrentNetType(context));
			buffer.append("&isdefault="+(isDefaultLauncher(context)?"1":"0"));
			//System.out.println("�����ַ�=======" + buffer.toString().replaceAll(" ", "%20"));
			httpRequest = new HttpGet(buffer.toString().replaceAll(" ", "%20"));// ����http get����
			if (!getCurrentNetType(context).equals("null")) {
				httpResponse = new DefaultHttpClient().execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toString(httpResponse.getEntity());
				}
			} else {
				//System.out.println("upgrade �����������磡");
				//Toast.makeText(context, "�����������磡", Toast.LENGTH_LONG).show();
				result = "";
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ����http����

		//System.out.println("http://desktop.gsie.cn/update �ķ���ֵ=========="
		//		+ result);

		return result;
	}

	public static String feedback(Context context, String contact,
			String content) {
		String feedbackUrl = "http://desktop.gsie.cn/feedback"; // ������ַ�ַ�

		HttpPost httpRequest = new HttpPost(feedbackUrl); // ����HTTP POST����
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // Post�������ͱ���������NameValuePair[]���鴢��
		PackageManager manager;

		PackageInfo info = null;

		manager = context.getPackageManager();
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		try {

			info = manager.getPackageInfo(context.getPackageName(), 0);

		} catch (NameNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		params.add(new BasicNameValuePair("contact", contact));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("ver", info.versionCode + ""));
		params.add(new BasicNameValuePair("os", "Android"
				+ android.os.Build.VERSION.RELEASE));
		params.add(new BasicNameValuePair("imei", tm.getSimSerialNumber()));
		params.add(new BasicNameValuePair("brand",
				android.os.Build.MANUFACTURER));
		params.add(new BasicNameValuePair("model", android.os.Build.MODEL));
		params.add(new BasicNameValuePair("oper", tm.getSimOperatorName()));
		String networkType = getCurrentNetType(context);
		params.add(new BasicNameValuePair("net", networkType));
		params.add(new BasicNameValuePair("isdefault", isDefaultLauncher(context)?"1":"0"));

		HttpResponse httpResponse;
		String resultString = null;
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			if (!networkType.equals("null")) {
				// ����http����
				httpResponse = new DefaultHttpClient().execute(httpRequest); // ȡ��http��Ӧ
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					resultString = EntityUtils.toString(httpResponse
							.getEntity()); // ��ȡ�ַ�
					//System.out.println("result=========" + resultString);
					return resultString;
				}

			} else {
				//System.out.println("feedback �����������磡");
				//Toast.makeText(context, "�����������磡", Toast.LENGTH_LONG).show();
				
				resultString = "";
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;

	}
	
	
	public static boolean isDefaultLauncher(Context context) {
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		final ResolveInfo res = context.getPackageManager().resolveActivity(
				intent, 0);
		if (res.activityInfo == null) {
			return false;
		}
		if (res.activityInfo.packageName.equals("android")) {
			return false;
		} else {
			return  "com.iboluo.launcher".equals(res.activityInfo.packageName);
		}
	}
	
	public static JSONObject getJsonObjectFromString(String result){
		JSONObject object = null;
		try {
			object = new JSONObject(result);
			/*object.getString("versionName");
			object.getString("versionCode");
			object.getString("appName");
			object.getString("downloadUrl");
			object.getString("remake");*/
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * �õ���ǰ���ֻ���������
	 * 
	 * @param context
	 * @return
	 */
	public static String getCurrentNetType(Context context) {
		String type = "";
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			type = "null";
		} else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
			type = "wifi";
		} else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
			int subType = info.getSubtype();
			if (subType == TelephonyManager.NETWORK_TYPE_CDMA
					|| subType == TelephonyManager.NETWORK_TYPE_GPRS
					|| subType == TelephonyManager.NETWORK_TYPE_EDGE) {
				type = "2g";
			} else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
					|| subType == TelephonyManager.NETWORK_TYPE_HSDPA
					|| subType == TelephonyManager.NETWORK_TYPE_EVDO_A
					|| subType == TelephonyManager.NETWORK_TYPE_EVDO_0
					|| subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
				type = "3g";
			} else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE��3g��4g�Ĺ�ɣ���3.9G��ȫ���׼
				type = "4g";
			}
		}
		//System.out.println("type=========" + type);
		return type;
	}
}
