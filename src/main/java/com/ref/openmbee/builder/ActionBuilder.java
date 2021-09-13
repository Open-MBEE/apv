package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.AcceptSignal;
import com.ref.openmbee.adapter.Action;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.CallBehavior;
import com.ref.openmbee.adapter.OpaqueAction;
import com.ref.openmbee.adapter.ReadSelfAction;
import com.ref.openmbee.adapter.ReadStructuralFeatureAction;
import com.ref.openmbee.adapter.SendSignal;
import com.ref.openmbee.adapter.ValueSpecificationAction;

public class ActionBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) {
		Action newAction = null;
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId = jsonObject.getString("activityId");
		ArrayList<String> outgoingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("outgoingIds"));
		ArrayList<String> incomingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("incomingIds"));
		
		ArrayList<String> triggerIds = new ArrayList<>();
		String body = "";
		ArrayList<String> inputValueIds = new ArrayList<>();
		ArrayList<String> localPostconditionIds = new ArrayList<>();
		ArrayList<String> localPreconditionIds = new ArrayList<>();
		String valueType;
		String valueOwnerId;
		String valueInstanceId;
		String valueTypeId;
		String resultId;
		String nameExpression;
		String structuralFeatureId;
		String objectId;
		
		String name = jsonObject.getString("name"); 
		if(name.length() == 0) {
			name = type+"_"+id;
		}
		
		String[] stereotypes = new String[0];
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));
		
		/*if(outgoingIds.size() > 0 || incomingIds.size() > 0 ||
				!jsonObject.isNull("resultId") || !jsonObject.isNull("objectId") || 
				!jsonObject.isNull("resultIds") || !jsonObject.isNull("argumentIds")) {//TODO tentativa de tratamento de nós invisives*/
		switch(type) {
		case "AcceptEventAction":
			if(!jsonObject.isNull("triggerIds")) {
				triggerIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("triggerIds"));
				newAction = new AcceptSignal(id, type, ownerId, activityId, outgoingIds, incomingIds, triggerIds,
						name, stereotypes, definition);
			}
			break;
			
		case "SendSignalAction":
			if(!jsonObject.isNull("signalId")) {
				String signalId = jsonObject.getString("signalId");
				newAction = new SendSignal(activityId, type, ownerId, activityId, outgoingIds, incomingIds, signalId, 
						name, stereotypes, definition);
			}
			break;
			
		case "CallBehaviorAction":
			if(!jsonObject.isNull("behaviorId")) {
				String behaviorId = jsonObject.getString("behaviorId");
				newAction = new CallBehavior(activityId, type, ownerId, activityId, outgoingIds, incomingIds, behaviorId,
						name, stereotypes, definition);
			}
			break;
			
		case "OpaqueAction":
			body = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("body")).toString();
			inputValueIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("inputValueIds"));
			localPostconditionIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("localPostconditionIds"));
			localPreconditionIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("localPreconditionIds"));
			newAction = new OpaqueAction(id, type, ownerId, activityId, outgoingIds, incomingIds, inputValueIds, localPostconditionIds, localPreconditionIds,
					name, stereotypes, body);
			break;
			
		case "ValueSpecificationAction":
			JSONObject value = jsonObject.getJSONObject("value");
			boolean primitive = AdapterUtils.primitives.containsKey(value.get("id")) || value.getString("type").equals("LiteralBoolean");//não ta 100%
			
			//TODO ajeitar
			if (!value.isNull("type") && !value.isNull("ownerId")) { 
				if(!value.isNull("instanceId") || !value.isNull("typeId") || primitive) {
					valueType = value.getString("type");
					valueOwnerId = value.getString("ownerId");
					valueInstanceId = (value.isNull("instanceId")?"":value.getString("instanceId"));
					valueTypeId = (value.isNull("typeId")?"":value.getString("typeId"));
					newAction = new ValueSpecificationAction(id, type, ownerId, activityId, outgoingIds,
							incomingIds, valueType, valueOwnerId, valueInstanceId, valueTypeId,
							name, stereotypes, definition);
					if(primitive) {
						ArrayList<String> valueAux = new ArrayList<>();
						valueAux.add(value.get("value").toString());
						((ValueSpecificationAction)newAction).setValue(valueAux);
						((ValueSpecificationAction)newAction).setValueTypeId(value.getString("id"));
						if(AdapterUtils.primitives.containsKey(((ValueSpecificationAction)newAction).getValueTypeId())) {
							((ValueSpecificationAction)newAction).setValueName(AdapterUtils.primitives.get(((ValueSpecificationAction)newAction).getValueTypeId()));	
						}else if(value.getString("type").equals("LiteralBoolean")) {
							((ValueSpecificationAction)newAction).setValueName("LiteralBoolean");
						}
						
					}
				}
			}
			break;
			
		case "ReadSelfAction":
			resultId = (jsonObject.isNull("inputValueIds")?"":jsonObject.getString("resultId"));
			nameExpression = (jsonObject.isNull("nameExpression")?"":jsonObject.getString("nameExpression"));
			newAction = new ReadSelfAction(id, type, ownerId, activityId, outgoingIds, incomingIds,
					nameExpression, resultId, localPostconditionIds, localPreconditionIds,
					name, stereotypes, definition);
			break;
			
		case "ReadStructuralFeatureAction":
			structuralFeatureId = jsonObject.getString("structuralFeatureId");
			objectId = jsonObject.getString("objectId");
			resultId = jsonObject.getString("resultId");
			
			newAction = new ReadStructuralFeatureAction(id, type, ownerId, activityId, outgoingIds, incomingIds,
					structuralFeatureId, objectId, resultId, name, stereotypes, definition);
			break;
			
		}
		//}
		return newAction;
	}
	
}
