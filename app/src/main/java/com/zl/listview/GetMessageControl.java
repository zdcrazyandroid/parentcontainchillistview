package com.zl.listview;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.base.utils.HandlerParse;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kjy.kjylistview.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * 192.168.0.224/app/supplierorder/getAreadyReadyOrder?
 * devceid=ce98bf322e095888df41d2752eaa2179&devceType=iphone&accountid=150195
 * @author LANGUANG
 *
 */
public class GetMessageControl {
	Handler handler;
	private List<PurOrderBean> listBeans;
	private Context context;
	public GetMessageControl(Handler handler, Context context) {
		this.handler  = handler;
		this.context = context;
	}
	public void setHttpMsg() throws Exception{
		
		
		InputStream is = context.getResources().openRawResource(R.raw.json);
		byte[] buffer;
		buffer = new byte[is.available()];
		is.read(buffer);

		//将字节数组转换为以GB2312编码的字符串
		String json = new String(buffer, "utf-8");
		
		Type listType = new TypeToken<LinkedList<PurOrderBean>>(){}.getType(); 
		Gson gson = new Gson(); 
		JsonParser jsonparer = new JsonParser();
		listBeans = gson.fromJson(jsonparer.parse(json.toString()).getAsJsonObject().get("result"), listType); 
		for (PurOrderBean bean : listBeans) {
			System.out.println("bean======" + bean);
		}
		Message msg = Message.obtain();
		msg.obj = listBeans;
		msg.what = HandlerParse.HTTP_SUCCESS;
		handler.sendMessage(msg);
		
		
	}
}
