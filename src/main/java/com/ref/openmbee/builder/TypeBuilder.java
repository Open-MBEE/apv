package com.ref.openmbee.builder;

import org.json.JSONObject;

import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.Type;

public class TypeBuilder{

	public Type BuildElement(JSONObject jsonObject) {
		Type baseType = null;
		String id = jsonObject.getString("id");
		String name = AdapterUtils.nameResolver((jsonObject.isNull("name")?jsonObject.getString("type"):jsonObject.getString("name"))); 
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));
		String typeId = (jsonObject.isNull("typeId")?jsonObject.getString("type")+" "+name:jsonObject.getString("typeId"));
		
		
		baseType = new Type(id, name, definition, stereotypes,typeId);
		return baseType;
	}
	
	public Type buildElement(String id, String name, String definition, String[] stereotypes, String typeId) {
		return new Type(id, name, definition, stereotypes, typeId);
	}

}
