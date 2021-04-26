package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.ControlFlow;
import com.ref.openmbee.adapter.Flow;
import com.ref.openmbee.adapter.ObjectFlow;
import com.ref.openmbee.adapter.Type;

public class FlowBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) {
		Flow flow = null;
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId = jsonObject.getString("activityId");
		String sourceId = jsonObject.getString("sourceId");
		String targetId = jsonObject.getString("targetId");
		
		String name = (jsonObject.isNull("name")?"":jsonObject.getString("name"));
		String[] stereotypes = null;
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));
		
		String guard = null;
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
			flow = new ControlFlow(targetId, type, ownerId, activityId, sourceId, targetId, name, stereotypes, definition, guard);
		}else if(type.equals("ObjectFlow")) {
			JSONObject aux = jsonObject.getJSONObject("weight");
			Object typeId = aux.get("typeId");
			
			String nameType = (aux.isNull("name")?"":aux.getString("name"));
			String[] stereotypesType = null;
			String definitionType = (aux.isNull("definition")?"":aux.getString("definition"));
			
			if (typeId instanceof String) {
				Type type2 = new Type(id,aux.getString("type"),ownerId,activityId, nameType, stereotypesType, definitionType);
				flow = new ObjectFlow(id, type, ownerId, activityId, sourceId, targetId, type2, (String) typeId, name, stereotypes, definition, guard);
			}else {
				Type type2 = new Type(id,aux.getString("type"),ownerId,activityId, nameType, stereotypesType, definitionType);
				flow = new ObjectFlow(id,type,ownerId, activityId,sourceId,targetId, type2, null, name, stereotypes, definition, guard);
			}
		}else {
			System.out.println("algo deu errado no flow builder");
		}
		return flow;
	}
	/* args[0] = id -> String
	 * args[1] = type -> String
	 * args[2] = ownerId -> String
	 * args[3] = activityId -> String
	 * args[4] = sourceId -> String
	 * args[5] = targetId -> String
	 * 
	 * objectFlow only
	 * args[6] = weightType/baseType -> Type
	 * args[7] = weightId/baseId -> String
	 * */
	
	/*@Override
	public ActivityElement BuildElement(Object... args) {
		Flow flow = null;
		if (args instanceof Object[]) {//se tiver algum parametro
			if(args[1].toString().equals("ControlFlow")) {
				flow = new ControlFlow(args[0].toString(), args[1].toString(), args[2].toString(),
						args[3].toString(), args[4].toString(),args[5].toString());
			}else if(args[1].toString().equals("ObjectFlow")) {
				flow = new ObjectFlow(args[0].toString(), args[1].toString(), args[2].toString(),
						args[3].toString(), args[4].toString(),args[5].toString(),(Type)args[6],args[7].toString());
			}else {
				System.out.println("Flow builder deu erro");
			}
		}
		return flow;
	}*/
	

	


}
