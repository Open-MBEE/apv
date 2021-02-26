package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class Action extends Element{
	private String body;

	public Action(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, boolean isControlType, String body) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, isControlType);
		this.body = body;
	}

	
	
}
