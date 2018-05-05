package com.base.net;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonParser<T> {
	public abstract T parse(JSONObject obj) throws JSONException;

}
