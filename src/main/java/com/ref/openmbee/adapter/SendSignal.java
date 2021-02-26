package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class SendSignal extends Element{
	private String signalId;

	public SendSignal(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, boolean isControlType, String signalId) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, isControlType);
		this.signalId = signalId;
	}


}
