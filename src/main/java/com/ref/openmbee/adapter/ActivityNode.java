package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public abstract class ActivityNode extends ActivityElement implements IActivityNode{
	private ArrayList<String> outgoingsIds;
	private IFlow[] outgoings;
	private ArrayList<String> incomingIds;
	private IFlow[] incomings;
	
	public ActivityNode(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingIds,
			ArrayList<String> incomingIds, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, name, stereotypes, definition);
		this.outgoingsIds = outgoingIds;
		this.incomingIds = incomingIds;
	}


	public ArrayList<String> getOutgoingsIds() {
		return outgoingsIds;
	}


	public void setOutgoingsIds(ArrayList<String> outgoingsIds) {
		this.outgoingsIds = outgoingsIds;
	}


	public ArrayList<String> getIncomingIds() {
		return incomingIds;
	}


	public void setIncomingIds(ArrayList<String> incomingIds) {
		this.incomingIds = incomingIds;
	}

	@Override
	public IFlow[] getIncomings() {
		return this.incomings;
	}


	@Override
	public IFlow[] getOutgoings() {
		return this.outgoings;
	}


	
}
