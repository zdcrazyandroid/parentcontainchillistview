package com.zl.listview;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.base.net.JsonParser;
import com.base.net.NetUtils;

public class PurOrderParse implements JsonParser<PurOrderBean> {

	@SuppressWarnings("unchecked")
	@Override
	public PurOrderBean parse(JSONObject obj) throws JSONException {
		PurOrderBean bean = new PurOrderBean();
		if (obj.has("id")) {
			bean.setBeanOrderId(obj.getString("id"));
		}
		if (obj.has("created_at")) {
			bean.setBeanCreate_Time(obj.getString("created_at"));
		}
		if (obj.has("contact_name")) {
			bean.setBeanContactName(obj.getString("contact_name"));
		}
		if (obj.has("contact_phone")) {
			bean.setBeanContactPhone(obj.getString("contact_phone"));
		}
		if (obj.has("warehouse_address")) {
			bean.setBeanWareHoseAddress(obj.getString("warehouse_address"));
		}
		if (obj.has("total_finance")) {
			bean.setBeanTotalFinance(obj.getString("total_finance"));
		}
		if (obj.has("goods")) {
			bean.setBeanGoodsList((List<ItemGoodsBean>) NetUtils.paserArrData(
					obj.getString("goods"), new ItemGoodsParse()));
		}
		return bean;
	}

}
