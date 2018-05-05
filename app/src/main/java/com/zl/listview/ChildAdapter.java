package com.zl.listview;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eegets.peter.enclosure.network.bitmap.abitmap.AWonderBitmap;
import com.kjy.kjylistview.R;

public class ChildAdapter extends BaseAdapter {
	private List<ItemGoodsBean> list;
	private Context context;
	private LayoutInflater inflater;
	private AWonderBitmap aWonderBitmap;

	public ChildAdapter(Context context) {
		super();

		this.context = context;
		aWonderBitmap = AWonderBitmap.create(context);
		
	}
	public void addAll(List<ItemGoodsBean> list) {
		this.list=list;
		notifyDataSetChanged();
	}

	public void clearAll() {
		this.list.clear();
		notifyDataSetChanged();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ParentListItem parentListItem = null;
		if (convertView == null) {
			parentListItem = new ParentListItem();
		    inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.sssssss, null, false);
			parentListItem.exhibit_item_icon = (ImageView) convertView .findViewById(R.id.exhibit_item_icon);
			parentListItem.text_name = (TextView) convertView .findViewById(R.id.text_name);
			parentListItem.text_count = (TextView) convertView .findViewById(R.id.text_count);
			parentListItem.text_price = (TextView) convertView .findViewById(R.id.text_price);
			convertView.setTag(parentListItem);
		} else {
			parentListItem = (ParentListItem) convertView.getTag();
		}
		parentListItem.text_name.setText(list.get(position).getGoodsname());
		parentListItem.text_count.setText(list.get(position).getGoodssum()+"");
		parentListItem.text_price.setText(list.get(position).getPrice()+"");
		parentListItem.text_price.getPaint() .setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		aWonderBitmap.display(parentListItem.exhibit_item_icon, list.get(position).getGoodspicurl());
		return convertView;
	}
	
		public class ParentListItem {
			ImageView exhibit_item_icon;
		TextView text_name, text_count, text_price;
	}

}
