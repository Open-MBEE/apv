package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.ActivityNode;
import com.ref.openmbee.adapter.InputPin;
import com.ref.openmbee.adapter.OutputPin;
import com.ref.openmbee.adapter.Parameter;
import com.ref.openmbee.adapter.Type;

public class ObjectBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject node) {
		ActivityNode newNode = null;
		
		String id = node.getString("id");
		String type = node.getString("type");
		String ownerId = node.getString("ownerId");
		String activityId= "";
		if(!node.isNull("activityId")) {//pinos não tem isso
			activityId = node.getString("activityId");
		}
		ArrayList<String> outgoingIds = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("outgoingIds"));
		ArrayList<String> incomingIds = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("incomingIds"));
		Type type2 = null;//TODO ver isso
		
		String name = (node.isNull("name")?"":node.getString("name"));
		String[] stereotypes = null;
		if(!node.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (node.isNull("definition")?"":node.getString("definition"));
		
		String typeId = "";
		if(!node.isNull("typeId")) {
			typeId = node.getString("typeId");
		}
		/*
		 * parte de criação de um pino
		 * */
		if(type.equals("InputPin")) {
			newNode = new InputPin(id, type, ownerId, activityId, outgoingIds, incomingIds, type2, typeId, name, stereotypes, definition);
		}else if(type.equals("OutputPin")) {
			newNode = new OutputPin(id, type, ownerId, activityId, outgoingIds, incomingIds, type2, typeId, name, stereotypes, definition);
		}else if(type.equals("ActivityParameterNode")) {
			newNode = new Parameter(id, type, ownerId, activityId, outgoingIds, incomingIds, type2, typeId, name, stereotypes, definition);
		}
		else{
			System.out.println("tem erro no createObject");
		}
		return newNode;
	}

	
	
}
