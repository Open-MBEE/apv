package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class CallBehavior extends Action{
	private String behaviorId;
	private Activity callBehavior;
	
	public CallBehavior(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String behaviorId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.behaviorId = behaviorId;
	}
	
	public String getBehaviorId() {
		return behaviorId;
	}
	public void setBehaviorId(String behaviorId) {
		this.behaviorId = behaviorId;
	}

	public Activity getCallBehavior() {
		return callBehavior;
	}

	public void setCallBehavior(Activity callBehavior) {
		this.callBehavior = callBehavior;
	}
	
}
