package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.exceptions.ParsingException;
import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.ActivityNode;
import com.ref.openmbee.adapter.InputPin;
import com.ref.openmbee.adapter.ObjectNode;
import com.ref.openmbee.adapter.OutputPin;
import com.ref.openmbee.adapter.Parameter;

public class ObjectBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) throws ParsingException {
		ObjectNode newNode = null;
		
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId= "";
		if(!jsonObject.isNull("activityId")) {//pinos não tem isso
			activityId = jsonObject.getString("activityId");
		}
		ArrayList<String> outgoingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("outgoingIds"));
		ArrayList<String> incomingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("incomingIds"));
		
		String name = jsonObject.getString("name"); 
		if(name.length() == 0) {
			name = type+"_"+id;
		}
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));	
		
		/*
		 * parte de criação de um pino
		 * */
		if(type.equals("InputPin")) {//a parte de Type está sendo realizada em outro lugar por isso os null
			newNode = new InputPin(id, type, ownerId, activityId, outgoingIds, incomingIds, null, null, name, stereotypes, definition);
		}else if(type.equals("OutputPin")) {
			newNode = new OutputPin(id, type, ownerId, activityId, outgoingIds, incomingIds, null, null, name, stereotypes, definition);
		}else if(type.equals("ActivityParameterNode")) {
			newNode = new Parameter(id, type, ownerId, activityId, outgoingIds, incomingIds, null, null, name, stereotypes, definition);
		}
		else{
			throw new ParsingException("Something went wrong in the object builder.");
		}
		
		if(newNode instanceof Parameter && !jsonObject.isNull("typeId")) {
			((Parameter)newNode).setPrimitive(AdapterUtils.primitives.containsKey(jsonObject.getString("typeId")));
		}
		return newNode;
	}

	
	
}
