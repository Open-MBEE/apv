package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IAction;

public class AcceptSignal extends Action implements IAction{
	private ArrayList<String> triggerIds;
	
	public AcceptSignal(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, ArrayList<String> triggerIds, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.triggerIds = triggerIds;
	}

	public ArrayList<String> getTriggerIds() {
		return triggerIds;
	}

	public void setTriggerIds(ArrayList<String> triggerIds) {
		this.triggerIds = triggerIds;
	}
	
}
