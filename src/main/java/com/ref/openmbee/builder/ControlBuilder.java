package com.ref.openmbee.builder;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ref.openmbee.ActivityElementBuilder;
import com.ref.openmbee.AdapterUtils;
import com.ref.openmbee.adapter.ActivityElement;
import com.ref.openmbee.adapter.ControlNode;
import com.ref.openmbee.adapter.Decision;
import com.ref.openmbee.adapter.Final;
import com.ref.openmbee.adapter.FinalFlow;
import com.ref.openmbee.adapter.Fork;
import com.ref.openmbee.adapter.Initial;
import com.ref.openmbee.adapter.Join;
import com.ref.openmbee.adapter.Merge;

public class ControlBuilder implements ActivityElementBuilder{

	@Override
	public ActivityElement BuildElement(JSONObject jsonObject) {
		ControlNode newControl = null;
		String id = jsonObject.getString("id");
		String type = jsonObject.getString("type");
		String ownerId = jsonObject.getString("ownerId");
		String activityId = jsonObject.getString("activityId");
		ArrayList<String> outgoingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("outgoingIds"));
		ArrayList<String> incomingIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("incomingIds"));
		
		String decisionInputId;
		String decisionInputFlowId;
		
		String name = (jsonObject.isNull("name")?"":jsonObject.getString("name"));
		String[] stereotypes = null;
		if(!jsonObject.isNull("stereotypes")) {//TODO não testei 100% ainda
			stereotypes = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("stereotypes")).toArray(new String[0]);
		}
		String definition = (jsonObject.isNull("definition")?"":jsonObject.getString("definition"));
		
		switch(type) {
		case "DecisionNode":
			if (!jsonObject.isNull("decisionInputId") || !jsonObject.isNull("decisionInputFlowId")) {
				decisionInputId = jsonObject.getString("decisionInputId");
				decisionInputFlowId = jsonObject.getString("decisionInputFlowId");
				newControl = new Decision(id, type, ownerId, activityId, outgoingIds, incomingIds,
						decisionInputId,decisionInputFlowId, name, stereotypes, definition);
			} else {
				newControl = new Decision(id, type, ownerId, activityId, outgoingIds, incomingIds, "", "",
						name, stereotypes, definition);
			}
			break;
			
		case "ActivityFinalNode":
			newControl = new Final(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		case "FinalFlow":
			newControl = new FinalFlow(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		case "ForkNode":
			newControl = new Fork(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		case "InitialNode":
			newControl = new Initial(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		case "JoinNode":
			newControl = new Join(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		case "MergeNode":
			newControl = new Merge(id, type, ownerId, activityId, outgoingIds, incomingIds,
					name, stereotypes, definition);
			break;
			
		}
		return newControl;
	}


}
