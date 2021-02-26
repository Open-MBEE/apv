package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class Pin extends Element{
	private String typeId;

	public Pin(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, boolean isControlType, String typeId) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, isControlType);
		this.typeId = typeId;
	}

	
	

}
