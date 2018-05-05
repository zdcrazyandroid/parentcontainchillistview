package com.zl.listview;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.base.net.JsonParser;
import com.base.net.NetUtils;

public class ItemGoodsParse implements JsonParser<ItemGoodsBean> {

	@SuppressWarnings("unchecked")
	@Override
	public ItemGoodsBean parse(JSONObject obj) throws JSONException {
		ItemGoodsBean bean = new ItemGoodsBean();
		if (obj.has("sukid")) {
			bean.setBeanId(obj.getString("sukid"));
		}
		if (obj.has("count")) {
			bean.setBeanCount(obj.getInt("count"));
		}
		if (obj.has("name")) {
			bean.setBeanName(obj.getString("name"));
		}
		if (obj.has("thumbUrl")) {
			bean.setBeanPic(obj.getString("thumbUrl"));
		}
		if (obj.has("price")) {
			bean.setBeanPrice(obj.getString("price"));
		}
		if (obj.has("price_in")) {
			bean.setBeanPriceLists((List<ItemPriceInBean>) NetUtils
					.paserArrData(obj.getString("price_in"),
							new ItemPriceParse()));
		}

		return bean;
	}

}
