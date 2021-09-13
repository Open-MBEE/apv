package com.ref.openmbee.builder;

import org.json.JSONObject;

import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityDiagram;

public class DiagramBuilder{

	public ActivityDiagram BuildElement(JSONObject jsonObject) {
		ActivityDiagram activityDiagram;
		String id = jsonObject.getString("id");
		String name = jsonObject.getString("name"); 
		if(name.length() == 0) {
			name = id;
		}
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));

		activityDiagram = new ActivityDiagram(id, name, definition, stereotypes);
		return activityDiagram;
	}

}
