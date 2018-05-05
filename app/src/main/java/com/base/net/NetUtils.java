package com.base.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.base.utils.StringUtils;

public class NetUtils {
	private static HttpClient mHttpClient = null;

	/**
	 * 解析数组
	 * 
	 * @param strData
	 * @param parser
	 * @return
	 */
	public static <T> Object paserArrData(String strData, JsonParser<T> parser) {
		List<T> lists = new ArrayList<T>();
		try {
			JSONArray jsons = null;
			try {
				jsons = StringUtils.getJsonArray(strData);
			} catch (Exception e) {
				return null;
			}
			if (jsons != null && jsons.length() > 0) {
				for (int i = 0; i < jsons.length(); i++) {
					JSONObject object = jsons.getJSONObject(i);
					lists.add(parser.parse(object));
				}
			} else {
				return lists;
			}
		} catch (Exception e) {

		}
		return lists;
	}

	/**
	 * 解析数组
	 * 
	 * @param strData
	 * @param parser
	 * @return
	 */
	public static <T> Object paserStrData(String strData, JsonParser<T> parser) {
		try {
			JSONObject jsonObject = new JSONObject((String) strData);
			return parser.parse(jsonObject);
		} catch (Exception e) {

		}
		return null;
	}

	public static HttpClient getmHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = createHttpClient();
		}
		return mHttpClient;
	}

	private static final DefaultHttpClient createHttpClient() {

		HttpParams params = new BasicHttpParams();

		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);
		final SchemeRegistry supportedSchemes = new SchemeRegistry();
		final SocketFactory sf = PlainSocketFactory.getSocketFactory();
		supportedSchemes.register(new Scheme("http", sf, 80));

		final ClientConnectionManager ccm = new ThreadSafeClientConnManager(
				params, supportedSchemes);
		return new DefaultHttpClient(ccm, params);
	}


	// public static <T> Object paserData(String strData, JsonParser<T> parser)
	// {
	// QueryResult<T> queryResult = new QueryResult<T>();
	// queryResult.setStrCurJson(strData);
	// try {
	// JSONObject jsonObject = new JSONObject((String) strData);
	// List<T> lists = new ArrayList<T>();
	// if (jsonObject.has("week")) {
	// queryResult.setWeek(jsonObject.getString("week"));
	// }
	// if (jsonObject.has("status")) {
	// queryResult.setState(jsonObject.getString("status"));
	// }
	// if (jsonObject.has("is_route")) {
	// try {
	// if (jsonObject.getInt("is_route") == 1) {
	// queryResult.setRoute(true);
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	// if (jsonObject.has("data")) {
	// JSONArray jsons = null;
	// try {
	// jsons = StringUtils.getJsonArray(jsonObject
	// .getString("data"));
	// } catch (Exception e) {
	// return queryResult;
	// // TODO: handle exception
	// }
	// if (jsons != null && jsons.length() > 0) {
	// for (int i = 0; i < jsons.length(); i++) {
	// JSONObject object = jsons.getJSONObject(i);
	// lists.add(parser.parse(object));
	// }
	// } else {
	// return null;
	// }
	// }
	//
	// if (jsonObject.has("keyword")) {
	// queryResult.setKeyword(jsonObject.getString("keyword"));
	// }
	// if (jsonObject.has("code")) {
	// try {
	// queryResult.setCode(jsonObject.getInt("code"));
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	// queryResult.setResult(lists);
	//
	// } catch (Exception e) {
	// return null;
	// }
	// return queryResult;
	// }
}
