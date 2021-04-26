package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IAction;

public class SendSignal extends Action implements IAction{
	private String signalId;

	public SendSignal(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String signalId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.signalId = signalId;
	}

	public String getSignalId() {
		return signalId;
	}

	public void setSignalId(String signalId) {
		this.signalId = signalId;
	}

}
