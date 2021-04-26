package com.ref.openmbee;

import org.json.JSONObject;

import com.ref.openmbee.adapter.ActivityElement;

public interface ActivityElementBuilder {

	public ActivityElement BuildElement(JSONObject jsonObject);
}
