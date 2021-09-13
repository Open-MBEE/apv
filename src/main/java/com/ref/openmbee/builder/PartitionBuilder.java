package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.Partition;

public class PartitionBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) {
		Partition newPartition;
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId = jsonObject.getString("activityId");
		
		String name = jsonObject.getString("name"); 
		if(name.length() == 0) {
			name = type+"_"+id;
		}
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));

		ArrayList<String> nodeIds = (jsonObject.isNull("nodeIds")?new ArrayList<String>():
			AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("nodeIds")));
		
		ArrayList<String> edgeIds = (jsonObject.isNull("edgeIds")?new ArrayList<String>():
			AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("edgeIds")));
		
		ArrayList<String> subpartitionIds = (jsonObject.isNull("subpartitionIds")?new ArrayList<String>():
			AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("subpartitionIds")));
		
		newPartition = new Partition(id, type, ownerId, activityId, name, stereotypes, 
				definition, nodeIds, edgeIds, subpartitionIds);
		return newPartition;
	}


}
