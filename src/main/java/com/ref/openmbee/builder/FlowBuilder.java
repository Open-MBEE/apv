package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.exceptions.ParsingException;
import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.ControlFlow;
import com.ref.openmbee.adapter.Flow;
import com.ref.openmbee.adapter.ObjectFlow;

public class FlowBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) throws ParsingException {
		Flow flow = null;
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId = jsonObject.getString("activityId");
		String sourceId = jsonObject.getString("sourceId");
		String targetId = jsonObject.getString("targetId");
		
		String name = jsonObject.getString("name"); 
		if(name.length() == 0) {
			name = type+"_"+id;
		}
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));
		
		String guard = "";
		if(!jsonObject.isNull("guard")) {
			JSONObject jsonGuard = jsonObject.getJSONObject("guard");
			if(!jsonGuard.isNull("body")) {
				ArrayList<String> guards = AdapterUtils.JSONArrayToArrayList(jsonGuard.getJSONArray("body"));
				guard = guards.get(0);
				for(int i = 1; i<guards.size(); i++) {
					guard+= guards.get(i);
				}
			}else {
				if(jsonGuard.getString("type").equals("LiteralBoolean")) {
					Boolean aux = jsonGuard.getBoolean("value");
					if(aux) {
						guard = "true";
					}else {
						guard = "false";
					}
				}else {
					guard = jsonGuard.getString("value");
				}
			}
		}
		if(type.equals("ControlFlow")) {
			flow = new ControlFlow(id, type, ownerId, activityId, sourceId, targetId, name, stereotypes, definition, guard);
			
		}else if(type.equals("ObjectFlow")) {

			// a parte null está sendo tratada em outro lugar
			flow = new ObjectFlow(id,type,ownerId, activityId,sourceId,targetId,null,null, name, stereotypes, definition, guard);

		}else {
			throw new ParsingException("something went wrong in the flow builder.");
		}
		return flow;
	}
	

	


}
