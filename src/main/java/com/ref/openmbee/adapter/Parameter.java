package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IActivityParameterNode;

public class Parameter extends ObjectNode implements IActivityParameterNode{
	private boolean primitive;

	public Parameter(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingIds,
			ArrayList<String> incomingIds, Type type2, String typeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingIds, incomingIds, type2, typeId, name, stereotypes, definition);
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}

}
