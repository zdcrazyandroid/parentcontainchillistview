package com.base.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Constants {
	
	
	public static final  String JSONDATA = "[{\"contact_name\":\"张三2\",\"contact_phone\":\"18623234234232\",\"created_at\":\"2015-07-16 15:41:36\",\"goods\":[" +
			"{\"count\":22,\"name\":\"Iphone6plus 金色 2\",\"price\":52,\"price_in\":[{\"in\":\"1-992\",\"price\":26.2},{\"in\":\"100-1992\",\"price\":25.2},{\"in\":\"2002\",\"price\":24.2}],\"sukid\":33335}," +
			"{\"count\":25,\"name\":\"Iphone6  白银\",\"price\":52,\"price_in\":[{\"in\":\"1-992\",\"price\":26.2},{\"in\":\"100-1992\",\"price\":25.2},{\"in\":\"2002\",\"price\":24.2}],\"sukid\":33336}"+
			"],\"id\":2133,\"total_finance\":125.44,\"warehouse_address\":\"北京市XXXX2\"},{\"contact_name\":\"张三4\",\"contact_phone\":\"18623234234234\",\"created_at\":\"2015-07-16 15:41:36\",\"goods\":[{\"count\":24,\"name\":\"Iphone6plus 金色 4\",\"price\":54,\"price_in\":[{\"in\":\"1-994\",\"price\":28.2},{\"in\":\"100-1994\",\"price\":27.2},{\"in\":\"2004\",\"price\":26.2}],\"sukid\":33337}],\"id\":2135,\"total_finance\":127.44,\"warehouse_address\":\"北京市XXXX4\"}]";
	
	/**
	 * 设备类型 Android或者是iphone
	 */
	public static final String DEVCE_TYPE = "android";
	/**
	 * 设备的ID（设备唯一标识码）
	 */
	public static  String DEVICE_ID(Context context){
		
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	
	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * @param listView
	 */
	public static void setListViewHeight(ListView listView) {  
		  
	    // 获取ListView对应的Adapter  
	    ListAdapter listAdapter = listView.getAdapter();  
	  
	    if (listAdapter == null) {  
	        return;  
	    }  
	    int totalHeight = 0;  
	    for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目  
	        View listItem = listAdapter.getView(i, null, listView);  
	        listItem.measure(0, 0); // 计算子项View 的宽高  
	        totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度  
	    }  
	  
	    ViewGroup.LayoutParams params = listView.getLayoutParams();  
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
	    listView.setLayoutParams(params);  
	}
	
}

