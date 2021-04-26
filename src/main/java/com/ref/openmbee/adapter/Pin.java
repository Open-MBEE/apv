package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IPin;

public class Pin extends ObjectNode implements IPin{
	private IAction owner;

	public Pin(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, Type type2, String typeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, type2, typeId, name, stereotypes, definition);
	}

	@Override
	public IAction getOwner() {
		return owner;
	}

	@Override
	public void setOwner(IAction action) {
		this.owner = action;
	}

	
	

}
