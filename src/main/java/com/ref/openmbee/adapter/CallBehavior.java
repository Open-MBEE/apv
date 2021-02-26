package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class CallBehavior extends Element{
	private String behaviorId;

	public CallBehavior(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, boolean isControlType, String behaviorId) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, isControlType);
		this.behaviorId = behaviorId;
	}

	
}
