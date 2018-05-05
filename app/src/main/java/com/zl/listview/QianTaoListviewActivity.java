package com.zl.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.kjy.kjylistview.R;
/** 
 * @author zl
 * @time 2014��12��24��15:37:18
 */
public class QianTaoListviewActivity extends Activity {
	private ListView listView;
	private ArrayList<HashMap<String, Object>> parentList, childList;
	private ParentAdapter parentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiantao);
		init();
	}

	private void init() {
		listView = (ListView) findViewById(R.id.qiantao_lv);

		getHttpMessage();
		

	}
	public void getHttpMessage(){
		GetMessageControl getMessageControl = new GetMessageControl(handler, getApplicationContext());
		try {
			getMessageControl.setHttpMsg();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			List<PurOrderBean> listBeans = (List<PurOrderBean>)msg.obj;
			parentAdapter = new ParentAdapter(listBeans, QianTaoListviewActivity.this);
			listView.setAdapter(parentAdapter);
		};
	};
}
