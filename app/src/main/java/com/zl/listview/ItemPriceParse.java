package com.zl.listview;

import org.json.JSONException;
import org.json.JSONObject;

import com.base.net.JsonParser;

public class ItemPriceParse implements JsonParser<ItemPriceInBean> {

	@Override
	public ItemPriceInBean parse(JSONObject obj) throws JSONException {
		ItemPriceInBean bean = new ItemPriceInBean();
		if (obj.has("in")) {
			bean.setBeanPriceIn(obj.getString("in"));
		}
		if (obj.has("price")) {
			bean.setBeanPrice(obj.getString("price"));
		}
		
		return bean;
	}

}
