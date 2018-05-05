package com.zl.listview;


import java.util.List;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.kjy.kjylistview.R;

public class ParentAdapter extends BaseAdapter implements ListAdapter {
	private List<PurOrderBean> list;
	private Context context;
	private LayoutInflater inflater;
	private ChildAdapter daAdapter;

	public ParentAdapter(List<PurOrderBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		daAdapter = new ChildAdapter(context);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ChildListViewItem childListViewItem = null;
		if (convertView == null) {
			childListViewItem = new ChildListViewItem();
			convertView = inflater.inflate(R.layout.parentitem, null, false);
			childListViewItem.text_caigou = (TextView) convertView.findViewById(R.id.text_caigou);
			childListViewItem.child_item_img = (TextView) convertView.findViewById(R.id.text_caigou);
			childListViewItem.child_item_title = (TextView) convertView.findViewById(R.id.shouhuoren_text);
			childListViewItem.parent_lv = (ChildLiistView) convertView.findViewById(R.id.parent_lv);
			childListViewItem.callphone_text = (TextView) convertView.findViewById(R.id.callphone_text);
			childListViewItem.shouhuodizhi_text = (TextView) convertView.findViewById(R.id.shouhuodizhi_text);
			convertView.setTag(childListViewItem);
		} else {
			childListViewItem = (ChildListViewItem) convertView.getTag();
		}
		childListViewItem.text_caigou.setText(list.get(position).getId()+"");
		childListViewItem.child_item_title.setText(list.get(position).getDdoperator());
		childListViewItem.callphone_text.setText(list.get(position).getDdoperatorphone());
		
		String address = list.get(position).getDdprovince() + list.get(position).getDdcity() + list.get(position).getDdarea() + list.get(position).getDdaddress();
		childListViewItem.shouhuodizhi_text.setText(address);
		final Button bt_jz = (Button) convertView.findViewById(R.id.button_add);
		int z = ((List<ItemGoodsBean>)list.get(position).getBeanGoodsList()).size();
//		if (z <= 2) {
////			bt_jz.setVisibility(View.GONE);
			daAdapter.addAll(((List<ItemGoodsBean>)list.get(position).getBeanGoodsList()));
			childListViewItem.parent_lv.setAdapter(daAdapter);
		bt_jz.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				bt_jz.setVisibility(View.GONE);
				daAdapter.addAll(((List<ItemGoodsBean>) list.get(position).getBeanGoodsList()));

			}
		});

		childListViewItem.parent_lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent intent = new Intent(context, Activity_2.class);
						context.startActivity(intent);
					}
				});
		return convertView;
	}

	public class ChildListViewItem {
		TextView text_caigou;
		TextView child_item_title, shouhuodizhi_text,
		callphone_text;
		TextView child_item_img;
		ChildLiistView parent_lv;
	}

}
